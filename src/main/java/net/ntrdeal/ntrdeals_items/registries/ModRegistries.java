package net.ntrdeal.ntrdeals_items.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.config.InfusibleConfiguration;
import net.ntrdeal.ntrdeals_items.config.InfusionConfiguration;

public interface ModRegistries {
    ResourceKey<Registry<InfusionConfiguration>> INFUSION = createRegistryKey("infusion");
    ResourceKey<Registry<InfusibleConfiguration>> INFUSIBLE = createRegistryKey("infusible");

    private static <T> ResourceKey<Registry<T>> createRegistryKey(final String name) {
        return ResourceKey.createRegistryKey(NTRDealsItems.id(name));
    }
}