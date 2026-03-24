package net.ntrdeal.ntrdeals_items.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.Map;

public record InfusionConfiguration(ResourceKey<TrimMaterial> material, Map<Holder<Attribute>, InfusionData> attributes) {
    public static final Codec<InfusionConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceKey.codec(Registries.TRIM_MATERIAL).fieldOf("material").forGetter(InfusionConfiguration::material),
            Codec.unboundedMap(Attribute.CODEC, InfusionData.CODEC).fieldOf("attributes").forGetter(InfusionConfiguration::attributes)
    ).apply(instance, InfusionConfiguration::new));
}
