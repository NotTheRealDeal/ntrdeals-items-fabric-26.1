package net.ntrdeal.ntrdeals_items.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.ntrdeal.ntrdeals_items.config.InfusibleConfig;
import net.ntrdeal.ntrdeals_items.config.InfusibleEntry;
import net.ntrdeal.ntrdeals_items.config.InfusionConfig;
import net.ntrdeal.ntrdeals_items.config.InfusionEntry;
import net.ntrdeal.ntrdeals_items.item.component.InfuseAttributes;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import org.jetbrains.annotations.Nullable;

public class Functions {
    public static boolean refreshInfusion(ItemStack stack) {
        if (stack.isEmpty()) return false;

        InfuseAttributes attributes = null;
        if (InfusionConfig.getEntry(stack.get(DataComponents.TRIM)) instanceof InfusionEntry entry) {
            attributes = InfuseAttributes.of(entry.getAttributes(stack));
        }
        stack.set(ModDataComponents.INFUSE_ATTRIBUTES, attributes);
        return attributes != null;
    }

    @Nullable
    public static InfusibleEntry isInfusible(ItemStack stack) {
        if (stack.get(ModDataComponents.INFUSIBLE) instanceof InfusibleEntry entry) {
            return entry;
        } else if (InfusibleConfig.getEntry(stack.getItem()) instanceof InfusibleEntry entry) {
            return entry;
        } else {
            return null;
        }
    }
}
