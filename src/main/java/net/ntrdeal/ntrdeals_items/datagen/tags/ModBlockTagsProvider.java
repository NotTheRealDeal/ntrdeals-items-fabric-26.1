package net.ntrdeal.ntrdeals_items.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.LUNARITE_ORE,
                ModBlocks.COSMOLITE_ORE,
                ModBlocks.RAW_LUNARITE_BLOCK,
                ModBlocks.RAW_COSMOLITE_BLOCK,
                ModBlocks.LUNARITE_BLOCK,
                ModBlocks.COSMOLITE_BLOCK,
                ModBlocks.DRIED_CHORUS_FLOWER
        );

        this.valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL).add(
                ModBlocks.LUNARITE_ORE,
                ModBlocks.RAW_LUNARITE_BLOCK,
                ModBlocks.LUNARITE_BLOCK
        );

        this.valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                ModBlocks.COSMOLITE_ORE,
                ModBlocks.RAW_COSMOLITE_BLOCK,
                ModBlocks.COSMOLITE_BLOCK
        );

        this.valueLookupBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).add(
                ModBlocks.COSMOLITE_ORE,
                ModBlocks.RAW_COSMOLITE_BLOCK,
                ModBlocks.COSMOLITE_BLOCK
        );
    }
}
