package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.item.equipment.ModEquipmentAssets;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        generator.createTrivialCube(ModBlocks.LUNARITE_ORE);
        generator.createTrivialCube(ModBlocks.COSMOLITE_ORE);
        generator.createTrivialCube(ModBlocks.RAW_LUNARITE_BLOCK);
        generator.createTrivialCube(ModBlocks.RAW_COSMOLITE_BLOCK);
        generator.createTrivialCube(ModBlocks.LUNARITE_BLOCK);
        generator.createTrivialCube(ModBlocks.COSMOLITE_BLOCK);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generator) {
        generator.generateFlatItem(ModItems.RAW_LUNARITE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.RAW_COSMOLITE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.LUNARITE_INGOT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.COSMOLITE_INGOT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.TRIM_SWAPPER, ModelTemplates.FLAT_ITEM);

        generator.generateTrimmableItem(ModItems.LUNARITE_HELMET, ModEquipmentAssets.LUNARITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generator.generateTrimmableItem(ModItems.LUNARITE_CHESTPLATE, ModEquipmentAssets.LUNARITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generator.generateTrimmableItem(ModItems.LUNARITE_LEGGINGS, ModEquipmentAssets.LUNARITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generator.generateTrimmableItem(ModItems.LUNARITE_BOOTS, ModEquipmentAssets.LUNARITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        generator.generateTrimmableItem(ModItems.COSMOLITE_HELMET, ModEquipmentAssets.COSMOLITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generator.generateTrimmableItem(ModItems.COSMOLITE_CHESTPLATE, ModEquipmentAssets.COSMOLITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generator.generateTrimmableItem(ModItems.COSMOLITE_LEGGINGS, ModEquipmentAssets.COSMOLITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generator.generateTrimmableItem(ModItems.COSMOLITE_BOOTS, ModEquipmentAssets.COSMOLITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
    }

//    public final void generateBundleLikeModel(Item bundle, ItemModelGenerators generator) {
//        ItemModel.Unbaked closedModel = ItemModelUtils.plainModel(generator.createFlatItemModel(Items.BUNDLE, ModelTemplates.FLAT_ITEM));
//        Identifier openBackCover = generator.generateBundleCoverModel(Items.BUNDLE, ModelTemplates.BUNDLE_OPEN_BACK_INVENTORY, "_open_back");
//        Identifier openFrontCover = generator.generateBundleCoverModel(Items.BUNDLE, ModelTemplates.BUNDLE_OPEN_FRONT_INVENTORY, "_open_front");
//        ItemModel.Unbaked openModel = ItemModelUtils.composite(
//                ItemModelUtils.plainModel(openBackCover), new BundleLikeSpecialRenderer.Unbaked(), ItemModelUtils.plainModel(openFrontCover)
//        );
//        ItemModel.Unbaked inGuiModel = ItemModelUtils.conditional(new BundleLikeHasIndexed(), openModel, closedModel);
//        generator.itemModelOutput.accept(bundle, ItemModelUtils.select(new DisplayContext(), closedModel, ItemModelUtils.when(ItemDisplayContext.GUI, inGuiModel)));
//    }
}
