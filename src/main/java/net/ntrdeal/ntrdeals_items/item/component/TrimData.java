package net.ntrdeal.ntrdeals_items.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record TrimData(Optional<Holder<TrimMaterial>> material, Optional<Holder<TrimPattern>> pattern) {
    public static final TrimData DEFAULT = new TrimData(Optional.empty(), Optional.empty());

    public static final Codec<TrimData> CODEC = RecordCodecBuilder.create(data -> data.group(
            TrimMaterial.CODEC.optionalFieldOf("material").forGetter(TrimData::material),
            TrimPattern.CODEC.optionalFieldOf("pattern").forGetter(TrimData::pattern)
    ).apply(data, TrimData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, TrimData> STREAM_CODEC = StreamCodec.composite(
            TrimMaterial.STREAM_CODEC.apply(ByteBufCodecs::optional),
            TrimData::material,
            TrimPattern.STREAM_CODEC.apply(ByteBufCodecs::optional),
            TrimData::pattern,
            TrimData::new
    );

    public static TrimData of(@Nullable ArmorTrim trim) {
        if (trim != null) return new TrimData(Optional.of(trim.material()), Optional.of(trim.pattern()));
        else return DEFAULT;
    }

    public boolean isComplete(){
        return this.material.isPresent() == this.pattern.isPresent();
    }

    public boolean isEmpty(){
        return this.material.isEmpty() && this.pattern.isEmpty();
    }

    @Nullable
    public ArmorTrim getTrim(){
        if (this.material.isPresent() && this.pattern.isPresent()) return new ArmorTrim(this.material.get(), this.pattern.get());
        else return null;
    }
}
