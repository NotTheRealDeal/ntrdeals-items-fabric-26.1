package net.ntrdeal.ntrdeals_items.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;

public class ModCreativeTabs {
    public static final CreativeModeTab NTRDEALS_ITEMS_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB, NTRDealsItems.id("ntrdeals_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_COSMOLITE))
                    .title(Component.translatable("itemGroup.ntrdeals_items.ntrdeals_items"))
                    .displayItems((_, output) -> {
                        output.accept(ModItems.RAW_LUNARITE);
                        output.accept(ModItems.LUNARITE_INGOT);
                        output.accept(ModBlocks.LUNARITE_ORE);
                        output.accept(ModBlocks.RAW_LUNARITE_BLOCK);
                        output.accept(ModBlocks.LUNARITE_BLOCK);
                        output.accept(ModItems.LUNARITE_HELMET);
                        output.accept(ModItems.LUNARITE_CHESTPLATE);
                        output.accept(ModItems.LUNARITE_LEGGINGS);
                        output.accept(ModItems.LUNARITE_BOOTS);

                        output.accept(ModItems.RAW_COSMOLITE);
                        output.accept(ModItems.COSMOLITE_INGOT);
                        output.accept(ModBlocks.COSMOLITE_ORE);
                        output.accept(ModBlocks.RAW_COSMOLITE_BLOCK);
                        output.accept(ModBlocks.COSMOLITE_BLOCK);
                        output.accept(ModItems.COSMOLITE_HELMET);
                        output.accept(ModItems.COSMOLITE_CHESTPLATE);
                        output.accept(ModItems.COSMOLITE_LEGGINGS);
                        output.accept(ModItems.COSMOLITE_BOOTS);

                        output.accept(ModItems.TRIM_SWAPPER);
                        output.accept(ModBlocks.DRIED_CHORUS_FLOWER);
                    })
                    .build()
    );

    public static void register() {}
}
