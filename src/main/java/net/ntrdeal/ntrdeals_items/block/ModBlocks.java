package net.ntrdeal.ntrdeals_items.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.item.ModItems;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModBlocks {

    public static final DropExperienceBlock LUNARITE_ORE = register("lunarite_ore", properties -> new DropExperienceBlock(
            UniformInt.of(5, 7), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE), ModItems::lunariteDefault);

    public static final DropExperienceBlock COSMOLITE_ORE = register("cosmolite_ore", properties -> new DropExperienceBlock(
            UniformInt.of(5, 7), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN), ModItems::cosmoliteDefault);

    public static final Block RAW_LUNARITE_BLOCK = register("raw_lunarite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE), ModItems::lunariteDefault);
    public static final Block RAW_COSMOLITE_BLOCK = register("raw_cosmolite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).mapColor(MapColor.COLOR_BLUE), ModItems::cosmoliteDefault);

    public static final Block LUNARITE_BLOCK = register("lunarite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE), ModItems::lunariteDefault);
    public static final Block COSMOLITE_BLOCK = register("cosmolite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).mapColor(MapColor.COLOR_BLUE), ModItems::cosmoliteDefault);

    public static final Block DRIED_CHORUS_FLOWER = register("dried_chorus_flower", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).noOcclusion(), properties -> properties);

    public static <B extends Block> B register(final String name, final Function<BlockBehaviour.Properties, B> factory) {
        return register(name, factory, BlockBehaviour.Properties.of());
    }

    public static <B extends Block> B register(final String name, final Function<BlockBehaviour.Properties, B> factory, final BlockBehaviour.Properties properties) {
        ResourceKey<Block> id = resourceKey(name);
        return Registry.register(BuiltInRegistries.BLOCK, id, factory.apply(properties.setId(id)));
    }

    public static <B extends Block> B register(final String name, final Function<BlockBehaviour.Properties, B> factory, final UnaryOperator<Item.Properties> propertiesFactory) {
        return register(name, factory, BlockBehaviour.Properties.of(), propertiesFactory);
    }

    public static <B extends Block> B register(final String name, final Function<BlockBehaviour.Properties, B> factory, final BlockBehaviour.Properties properties, final UnaryOperator<Item.Properties> propertiesFactory) {
        ResourceKey<Block> id = resourceKey(name);
        B block = factory.apply(properties.setId(id));
        ModItems.register(name, itemProperties -> new BlockItem(block, propertiesFactory.apply(itemProperties)));
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    private static ResourceKey<Block> resourceKey(String path) {
        return ResourceKey.create(Registries.BLOCK, NTRDealsItems.id(path));
    }

    public static void register() {}
}
