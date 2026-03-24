package net.ntrdeal.ntrdeals_items.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public interface ModItemTags {
    TagKey<Item> COSMOLITE_ARMOR = bind("cosmolite_armor");
    TagKey<Item> LUNARITE_ARMOR = bind("lunarite_armor");
    TagKey<Item> REPAIRS_LUNARITE_ARMOR = bind("repairs_lunarite_armor");
    TagKey<Item> REPAIRS_COSMOLITE_ARMOR = bind("repairs_cosmolite_armor");

    static TagKey<Item> bind(final String name) {
        return TagKey.create(Registries.ITEM, NTRDealsItems.id(name));
    }
}
