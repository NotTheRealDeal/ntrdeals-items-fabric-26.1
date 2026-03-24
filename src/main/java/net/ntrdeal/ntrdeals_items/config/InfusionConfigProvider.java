package net.ntrdeal.ntrdeals_items.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class InfusionConfigProvider implements DataProvider {
    private final FabricPackOutput output;
    private final PackOutput.PathProvider infusionsPathProvider;
    private final PackOutput.PathProvider infusiblesPathProvider;
    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public InfusionConfigProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this.output = output;
        this.infusionsPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, ModConfig.INFUSION_KEY.getPath());
        this.infusiblesPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, ModConfig.INFUSIBLE_KEY.getPath());
        this.registryLookup = registryLookup;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return this.registryLookup.thenCompose(lookup -> {
            final Set<ResourceKey<TrimMaterial>> materials = new HashSet<>();
            final Set<InfusionConfiguration> infusions = new HashSet<>();
            final Map<String, InfusibleConfiguration> infusibles = new HashMap<>();
            this.generateInfusions(lookup, infusions::add);
            this.generateInfusibles(lookup, infusibles::put);

            RegistryOps<JsonElement> ops = lookup.createSerializationContext(JsonOps.INSTANCE);
            final List<CompletableFuture<?>> configs = new ArrayList<>();

            infusions.forEach(configuration -> {
                if (materials.add(configuration.material())) {
                    JsonObject infusionJson = InfusionConfiguration.CODEC.encodeStart(ops, configuration).getOrThrow(IllegalStateException::new).getAsJsonObject();
                    if (this.getInfusionPath(configuration) instanceof Path path) configs.add(DataProvider.saveStable(cache, infusionJson, path));
                }
            });

            infusibles.forEach((name, configuration) -> {
                JsonObject infusibleJson = InfusibleConfiguration.CODEC.encodeStart(ops, configuration).getOrThrow(IllegalStateException::new).getAsJsonObject();
                if (this.getInfusiblePath(name) instanceof Path path) configs.add(DataProvider.saveStable(cache, infusibleJson, path));
            });

            return CompletableFuture.allOf(configs.toArray(CompletableFuture[]::new));
        });
    }

    private Path getInfusionPath(InfusionConfiguration configuration) {
        return this.infusionsPathProvider.json(Identifier.fromNamespaceAndPath(this.output.getModId(), configuration.material().identifier().getPath()));
    }

    private Path getInfusiblePath(String path) {
        return this.infusiblesPathProvider.json(Identifier.fromNamespaceAndPath(this.output.getModId(), path));
    }

    public void generateInfusions(HolderLookup.Provider provider, Consumer<InfusionConfiguration> consumer) {}
    public void generateInfusibles(HolderLookup.Provider provider, BiConsumer<String, InfusibleConfiguration> consumer) {}

    @Override
    public String getName() {
        return "Infusion Config";
    }
}
