package net.ntrdeal.ntrdeals_items.item.component;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Consumer;

public record InfuseAttributes(ItemAttributeModifiers attributes) implements TooltipProvider {
    public static final InfuseAttributes EMPTY = new InfuseAttributes(ItemAttributeModifiers.EMPTY);

    public static final Codec<InfuseAttributes> CODEC = ItemAttributeModifiers.CODEC.xmap(InfuseAttributes::new, InfuseAttributes::attributes);
    public static final StreamCodec<RegistryFriendlyByteBuf, InfuseAttributes> STREAM_CODEC = ItemAttributeModifiers.STREAM_CODEC.map(InfuseAttributes::new, InfuseAttributes::attributes);

    @Nullable
    public static InfuseAttributes of(@Nullable ItemAttributeModifiers attributes) {
        return attributes != null ? new InfuseAttributes(attributes) : null;
    }

    public static void registerTooltip() {
        ItemComponentTooltipProviderRegistry.addAfter(DataComponents.TRIM, ModDataComponents.INFUSE_ATTRIBUTES);
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter getter) {
        ArmorTrim trim = getter.get(DataComponents.TRIM);
        Component startingText = trim != null ? CommonComponents.space().append(CommonComponents.space()) : CommonComponents.space();
        for (EquipmentSlotGroup slot : EquipmentSlotGroup.values()) {
            this.attributes.forEach(slot, (attribute, modifier) -> this.appendInfusionModifierTooltip(
                    consumer,
                    attribute,
                    modifier,
                    startingText,
                    trim
                    )
            );
        }
    }

    @Unique
    private void appendInfusionModifierTooltip(Consumer<Component> consumer, Holder<Attribute> attribute, AttributeModifier modifier, Component startingText, ArmorTrim trim) {
        double amount = modifier.amount();

        double displayAmount;
        if (modifier.operation().equals(AttributeModifier.Operation.ADD_MULTIPLIED_BASE) || modifier.operation().equals(AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)) {
            displayAmount = amount * 100.0;
        } else if (attribute == Attributes.KNOCKBACK_RESISTANCE) {
            displayAmount = amount * 10.0;
        } else {
            displayAmount = amount;
        }

        if (amount > 0.0) {
            consumer.accept(startingText.copy().append(
                    Component.translatable(
                                    "attribute.modifier.plus." + modifier.operation().id(),
                                    ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount),
                                    Component.translatable(attribute.value().getDescriptionId()))
                            )
                    .withStyle(trim != null ? trim.material().value().description().getStyle() : Style.EMPTY.applyFormats(attribute.value().getStyle(true))));
        } else if (amount < 0.0) {
            consumer.accept(startingText.copy().append(
                    Component.translatable(
                                    "attribute.modifier.take." + modifier.operation().id(),
                                    ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(-displayAmount),
                                    Component.translatable(attribute.value().getDescriptionId())
                            )
                            .withStyle(trim != null ? trim.material().value().description().getStyle() : Style.EMPTY.applyFormats(attribute.value().getStyle(false)))));
        }
    }
}
