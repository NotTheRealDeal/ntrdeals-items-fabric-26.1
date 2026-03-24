package net.ntrdeal.ntrdeals_items.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.ntrdeal.ntrdeals_items.entity.ModAttributes;
import net.ntrdeal.realapi.tags.RealAttributeTags;

import java.util.concurrent.CompletableFuture;

public class ModAttributeTagsProvider extends FabricTagsProvider.FabricIntrinsicHolderTagsProvider<Attribute> {
    public ModAttributeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, Registries.ATTRIBUTE, provider, attribute ->  BuiltInRegistries.ATTRIBUTE.getResourceKey(attribute).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.valueLookupBuilder(RealAttributeTags.DIMENSIONS_REFRESHER).add(ModAttributes.CLAMPED_SCALE.value());
    }
}
