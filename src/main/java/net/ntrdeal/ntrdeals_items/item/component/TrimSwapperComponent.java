package net.ntrdeal.ntrdeals_items.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public record TrimSwapperComponent(TrimData head, TrimData chest, TrimData legs, TrimData feet) implements TooltipProvider {
    public static final TrimSwapperComponent DEFAULT = new TrimSwapperComponent(TrimData.DEFAULT, TrimData.DEFAULT, TrimData.DEFAULT, TrimData.DEFAULT);

    private static final Component UPGRADE_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.upgrade"))).withStyle(ChatFormatting.GRAY);
    private static final Component HEAD_TEXT = Component.translatable("item.modifiers." + EquipmentSlot.HEAD.getSerializedName()).withStyle(ChatFormatting.GRAY);
    private static final Component CHEST_TEXT = Component.translatable("item.modifiers." + EquipmentSlot.CHEST.getSerializedName()).withStyle(ChatFormatting.GRAY);
    private static final Component LEGS_TEXT = Component.translatable("item.modifiers." + EquipmentSlot.LEGS.getSerializedName()).withStyle(ChatFormatting.GRAY);
    private static final Component FEET_TEXT = Component.translatable("item.modifiers." + EquipmentSlot.FEET.getSerializedName()).withStyle(ChatFormatting.GRAY);

    public static final Codec<TrimSwapperComponent> CODEC = RecordCodecBuilder.create(component -> component.group(
            TrimData.CODEC.optionalFieldOf("head", null).forGetter(TrimSwapperComponent::head),
            TrimData.CODEC.optionalFieldOf("chest", null).forGetter(TrimSwapperComponent::chest),
            TrimData.CODEC.optionalFieldOf("legs", null).forGetter(TrimSwapperComponent::legs),
            TrimData.CODEC.optionalFieldOf("feet", null).forGetter(TrimSwapperComponent::feet)
    ).apply(component, TrimSwapperComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, TrimSwapperComponent> STREAM_CODEC = StreamCodec.composite(
            TrimData.STREAM_CODEC,
            TrimSwapperComponent::head,
            TrimData.STREAM_CODEC,
            TrimSwapperComponent::chest,
            TrimData.STREAM_CODEC,
            TrimSwapperComponent::legs,
            TrimData.STREAM_CODEC,
            TrimSwapperComponent::feet,
            TrimSwapperComponent::new
    );

    public Map<EquipmentSlot, TrimData> getMap() {
        Map<EquipmentSlot, TrimData> map = new HashMap<>();
        map.put(EquipmentSlot.HEAD, this.head);
        map.put(EquipmentSlot.CHEST, this.chest);
        map.put(EquipmentSlot.LEGS, this.legs);
        map.put(EquipmentSlot.FEET, this.feet);
        return map;
    }

    public boolean isEmpty() {
        return this.head.isEmpty() && this.chest.isEmpty() && this.legs.isEmpty() && this.feet.isEmpty();
    }

    public static TrimSwapperComponent of(Map<EquipmentSlot, TrimData> map) {
        return new TrimSwapperComponent(
                map.getOrDefault(EquipmentSlot.HEAD, TrimData.DEFAULT),
                map.getOrDefault(EquipmentSlot.CHEST, TrimData.DEFAULT),
                map.getOrDefault(EquipmentSlot.LEGS, TrimData.DEFAULT),
                map.getOrDefault(EquipmentSlot.FEET, TrimData.DEFAULT)
        );
    }

    public static void registerTooltip() {
        ItemComponentTooltipProviderRegistry.addAfter(DataComponents.TRIM, ModDataComponents.TRIM_SWAPPER);
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        tooltip.accept(UPGRADE_TEXT);
        appendData(tooltip, this.head, HEAD_TEXT);
        appendData(tooltip, this.chest, CHEST_TEXT);
        appendData(tooltip, this.legs, LEGS_TEXT);
        appendData(tooltip, this.feet, FEET_TEXT);
    }

    public static void appendData(Consumer<Component> tooltip, TrimData data, Component translatable){
        if (data.isComplete() && data.pattern().isPresent() && data.material().isPresent()){
            tooltip.accept(CommonComponents.space().append(translatable));
            tooltip.accept(CommonComponents.space().append(CommonComponents.space().append(data.pattern().get().value().copyWithStyle(data.material().get()))));
            tooltip.accept(CommonComponents.space().append(CommonComponents.space().append(data.material().get().value().description())));
        } else if (!data.isComplete()){
            tooltip.accept(CommonComponents.space().append(translatable));
            tooltip.accept(CommonComponents.space().append(CommonComponents.space().append(Component.translatable("tooltip.ntrdeals_items.error.incomplete"))));
        }
    }
}
