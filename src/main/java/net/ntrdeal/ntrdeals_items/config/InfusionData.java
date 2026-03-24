package net.ntrdeal.ntrdeals_items.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record InfusionData(double value, InfusionEntry.TYPE type) {
    public static final Codec<InfusionData> CODEC = RecordCodecBuilder.create(data -> data.group(
            Codec.DOUBLE.fieldOf("value").forGetter(InfusionData::value),
            InfusionEntry.TYPE.CODEC.fieldOf("type").forGetter(InfusionData::type)
    ).apply(data, InfusionData::new));
}
