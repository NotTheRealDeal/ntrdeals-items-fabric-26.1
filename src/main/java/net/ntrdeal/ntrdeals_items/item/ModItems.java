package net.ntrdeal.ntrdeals_items.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.custom.TrimSwapperItem;
import net.ntrdeal.ntrdeals_items.item.equipment.ModArmorMaterials;

import java.util.function.Function;

public class ModItems {
    public static final Item RAW_LUNARITE = register("raw_lunarite", properties -> new Item(lunariteDefault(properties)));
    public static final Item RAW_COSMOLITE = register("raw_cosmolite", properties -> new Item(cosmoliteDefault(properties)));

    public static final Item LUNARITE_INGOT = register("lunarite_ingot", properties -> new Item(lunariteDefault(properties)));
    public static final Item COSMOLITE_INGOT = register("cosmolite_ingot", properties -> new Item(cosmoliteDefault(properties)));

    public static final Item LUNARITE_HELMET = register("lunarite_helmet", properties -> new Item(lunariteDefault(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.HELMET))));
    public static final Item LUNARITE_CHESTPLATE = register("lunarite_chestplate", properties -> new Item(lunariteDefault(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.CHESTPLATE))));
    public static final Item LUNARITE_LEGGINGS = register("lunarite_leggings", properties -> new Item(lunariteDefault(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.LEGGINGS))));
    public static final Item LUNARITE_BOOTS = register("lunarite_boots", properties -> new Item(lunariteDefault(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.BOOTS))));

    public static final Item COSMOLITE_HELMET = register("cosmolite_helmet", properties -> new Item(cosmoliteDefault(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.HELMET))));
    public static final Item COSMOLITE_CHESTPLATE = register("cosmolite_chestplate", properties -> new Item(cosmoliteDefault(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.CHESTPLATE))));
    public static final Item COSMOLITE_LEGGINGS = register("cosmolite_leggings", properties -> new Item(cosmoliteDefault(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.LEGGINGS))));
    public static final Item COSMOLITE_BOOTS = register("cosmolite_boots", properties -> new Item(cosmoliteDefault(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.BOOTS))));

    public static final TrimSwapperItem TRIM_SWAPPER = register("trim_swapper", properties -> new TrimSwapperItem(cosmoliteDefault(properties.stacksTo(1))));

    public static <I extends Item> I register(String name, Function<Item.Properties, I> function) {
        ResourceKey<Item> key = resourceKey(name);
        return Registry.register(BuiltInRegistries.ITEM, key, function.apply(new Item.Properties().setId(key)));
    }

    public static Item.Properties lunariteDefault(Item.Properties properties) {
        return properties.component(DataComponents.ENCHANTABLE, null);
    }

    public static Item.Properties cosmoliteDefault(Item.Properties properties) {
        return properties.fireResistant().rarity(Rarity.EPIC);
    }

    private static ResourceKey<Item> resourceKey(String path) {
        return ResourceKey.create(Registries.ITEM, NTRDealsItems.id(path));
    }

    public static void register() {
        ModCreativeTabs.register();
        ModDataComponents.register();
    }
}
