package net.ntrdeal.ntrdeals_items;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.ntrdeal.ntrdeals_items.datagen.*;
import net.ntrdeal.ntrdeals_items.datagen.language.ModEnglishProvider;
import net.ntrdeal.ntrdeals_items.datagen.tags.ModAttributeTagsProvider;
import net.ntrdeal.ntrdeals_items.datagen.tags.ModBlockTagsProvider;
import net.ntrdeal.ntrdeals_items.datagen.tags.ModItemTagsProvider;
import net.ntrdeal.ntrdeals_items.world.ModConfiguredFeatures;
import net.ntrdeal.ntrdeals_items.world.ModPlacedFeatures;
import org.jspecify.annotations.NonNull;

public class NTRDealsItemsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModAttributeTagsProvider::new);
		pack.addProvider(ModInfusionProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRegistryProvider::new);

		pack.addProvider(ModEnglishProvider::new);
		pack.addProvider(ModModelProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder builder) {
		builder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		builder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
