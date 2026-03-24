package net.ntrdeal.ntrdeals_items.item.equipment;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public interface ModEquipmentAssets {
    ResourceKey<EquipmentAsset> LUNARITE = createId("lunarite");
    ResourceKey<EquipmentAsset> COSMOLITE = createId("cosmolite");

    static ResourceKey<EquipmentAsset> createId(final String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, NTRDealsItems.id(name));
    }
}
