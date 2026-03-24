package net.ntrdeal.ntrdeals_items.item.component;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.config.InfusibleEntry;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<TrimSwapperComponent> TRIM_SWAPPER = register("trim_swapper", builder -> builder.persistent(TrimSwapperComponent.CODEC).networkSynchronized(TrimSwapperComponent.STREAM_CODEC).ignoreSwapAnimation().cacheEncoding());
    public static final DataComponentType<InfuseAttributes> INFUSE_ATTRIBUTES = register("infuse_attributes", builder -> builder.persistent(InfuseAttributes.CODEC).networkSynchronized(InfuseAttributes.STREAM_CODEC).ignoreSwapAnimation().cacheEncoding());
    public static final DataComponentType<InfusibleEntry> INFUSIBLE = register("infusible", builder -> builder.persistent(InfusibleEntry.CODEC).networkSynchronized(InfusibleEntry.STREAM_CODEC).cacheEncoding());

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, NTRDealsItems.id(name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register() {
        InfuseAttributes.registerTooltip();
        TrimSwapperComponent.registerTooltip();
    }
}
