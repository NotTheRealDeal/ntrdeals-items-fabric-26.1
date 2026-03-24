package net.ntrdeal.ntrdeals_items.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> LUNARITE_GEODE = resourceKey("lunarite_geode");
    public static final ResourceKey<PlacedFeature> COSMOLITE_GEODE = resourceKey("cosmolite_geode");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, LUNARITE_GEODE, features.getOrThrow(ModConfiguredFeatures.LUNARITE_GEODE),
                RarityFilter.onAverageOnceEvery(85),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(64), VerticalAnchor.aboveBottom(94))
        );

        register(context, COSMOLITE_GEODE, features.getOrThrow(ModConfiguredFeatures.COSMOLITE_GEODE),
                RarityFilter.onAverageOnceEvery(45),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(14), VerticalAnchor.aboveBottom(44))
        );
    }

    public static ResourceKey<PlacedFeature> resourceKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, NTRDealsItems.id(name));
    }

    public static void register(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> feature,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(feature, modifiers));
    }

    public static void register(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> feature,
            PlacementModifier... modifiers
    ) {
        register(context, key, feature, List.of(modifiers));
    }
}
