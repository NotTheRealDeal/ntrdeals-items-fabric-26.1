package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Blocks;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        this.add(ModBlocks.LUNARITE_ORE, this.createOreDrop(ModBlocks.LUNARITE_ORE, ModItems.RAW_LUNARITE));
        this.add(ModBlocks.COSMOLITE_ORE, this.createOreDrop(ModBlocks.COSMOLITE_ORE, ModItems.RAW_COSMOLITE));
        this.dropSelf(ModBlocks.RAW_LUNARITE_BLOCK);
        this.dropSelf(ModBlocks.RAW_COSMOLITE_BLOCK);
        this.dropSelf(ModBlocks.LUNARITE_BLOCK);
        this.dropSelf(ModBlocks.COSMOLITE_BLOCK);
        this.add(ModBlocks.DRIED_CHORUS_FLOWER, block -> this.createSingleItemTableWithSilkTouch(block, Blocks.CHORUS_FLOWER));
    }
}
