package net.ntrdeal.ntrdeals_items.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.tags.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.valueLookupBuilder(ItemTags.HEAD_ARMOR).add(ModItems.COSMOLITE_HELMET);
        this.valueLookupBuilder(ItemTags.CHEST_ARMOR).add(ModItems.COSMOLITE_CHESTPLATE);
        this.valueLookupBuilder(ItemTags.LEG_ARMOR).add(ModItems.COSMOLITE_LEGGINGS);
        this.valueLookupBuilder(ItemTags.FOOT_ARMOR).add(ModItems.COSMOLITE_BOOTS);

        this.valueLookupBuilder(ModItemTags.LUNARITE_ARMOR).add(ModItems.LUNARITE_HELMET, ModItems.LUNARITE_CHESTPLATE, ModItems.LUNARITE_LEGGINGS, ModItems.LUNARITE_BOOTS);
        this.valueLookupBuilder(ModItemTags.COSMOLITE_ARMOR).add(ModItems.COSMOLITE_HELMET, ModItems.COSMOLITE_CHESTPLATE, ModItems.COSMOLITE_LEGGINGS, ModItems.COSMOLITE_BOOTS);

        this.valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR).addTag(ModItemTags.LUNARITE_ARMOR);
        this.valueLookupBuilder(ConventionalItemTags.ARMORS).addTag(ModItemTags.LUNARITE_ARMOR);

        this.valueLookupBuilder(ModItemTags.REPAIRS_LUNARITE_ARMOR).add(ModItems.LUNARITE_INGOT);
        this.valueLookupBuilder(ModItemTags.REPAIRS_COSMOLITE_ARMOR).add(ModItems.COSMOLITE_INGOT);
    }
}
