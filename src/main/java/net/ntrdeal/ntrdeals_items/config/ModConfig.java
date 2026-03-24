package net.ntrdeal.ntrdeals_items.config;

import net.fabricmc.fabric.api.resource.v1.DataResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys;
import net.minecraft.resources.Identifier;
import net.ntrdeal.ntrdeals_items.registries.ModRegistries;

public class ModConfig {
    public static final Identifier INFUSION_KEY = ModRegistries.INFUSION.identifier();
    public static final Identifier INFUSIBLE_KEY = ModRegistries.INFUSIBLE.identifier();

    public static void register() {
        DataResourceLoader.get().registerReloadListener(INFUSION_KEY, new InfusionConfig());
        DataResourceLoader.get().registerReloadListener(INFUSIBLE_KEY, new InfusibleConfig());
    }
}
