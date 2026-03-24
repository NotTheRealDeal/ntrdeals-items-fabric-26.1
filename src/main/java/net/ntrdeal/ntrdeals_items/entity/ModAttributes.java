package net.ntrdeal.ntrdeals_items.entity;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModAttributes {

    public static final Holder<Attribute> PASSIVE_REGENERATION = register("passive_regeneration", new RangedAttribute(
            "attribute.ntrdeals_items.passive_regeneration", 0d, 0d, 1024d)
            .setSyncable(true).setSentiment(Attribute.Sentiment.POSITIVE));

    public static final Holder<Attribute> CLAMPED_SCALE = register("clamped_scale", new RangedAttribute(
            Attributes.SCALE.value().getDescriptionId(), 0d, -0.75d, 0d)
            .setSyncable(true).setSentiment(Attribute.Sentiment.NEUTRAL));

    public static final Holder<Attribute> SANITY = register("sanity", new RangedAttribute(
            "attribute.ntrdeals_items.sanity", 0d, -1024d, 1024d)
            .setSyncable(true).setSentiment(Attribute.Sentiment.POSITIVE));

    public static Holder<Attribute> register(String name, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, NTRDealsItems.id(name), attribute);
    }

    public static void register() {
    }
}
