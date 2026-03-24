package net.ntrdeal.ntrdeals_items.config;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import org.jspecify.annotations.Nullable;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class InfusionConfig implements ResourceManagerReloadListener {
    public static final Map<ResourceKey<TrimMaterial>, InfusionEntry> ENTRIES = new HashMap<>();

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        ENTRIES.clear();

        manager.listResources(ModConfig.INFUSION_KEY.getPath(), path -> path.getPath().endsWith(".json")).forEach((_, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.open(), StandardCharsets.UTF_8)) {
                InfusionConfiguration.CODEC.parse(JsonOps.INSTANCE, JsonParser.parseReader(reader)).result().ifPresent(
                        infusion -> ENTRIES.put(infusion.material(), new InfusionEntry(infusion.attributes()))
                );
            } catch (Exception ignore){}
        });
    }

    @Nullable
    public static InfusionEntry getEntry(@Nullable ArmorTrim trim) {
        if (trim != null && trim.material().unwrapKey().isPresent()) return ENTRIES.get(trim.material().unwrapKey().get());
        else return null;
    }
}
