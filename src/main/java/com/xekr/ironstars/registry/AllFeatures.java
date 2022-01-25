package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class AllFeatures {
    private static final RuleTest MOON_ORE_REPLACEABLES = new BlockMatchTest(AllBlocks.MOON_ROCK);
    private static final List<OreConfiguration.TargetBlockState> ORE_TITANIUM_TARGET_LIST = List.of(OreConfiguration.target(MOON_ORE_REPLACEABLES, AllBlocks.TITANIUM_ORE.defaultBlockState()));

    public static final ConfiguredFeature<OreConfiguration, ?> TITANIUM_ORE = registerConfiguredFeature(IronStars.id("ore_titanium"), Feature.ORE.configured(new OreConfiguration(ORE_TITANIUM_TARGET_LIST, 10)));
    public static final PlacedFeature PLACED_TITANIUM_ORE = registerPlacedFeature(IronStars.id("ore_titanium"), TITANIUM_ORE.placed(List.of(CountPlacement.of(16), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(112)))));


    private static <T extends ConfiguredFeature<?, ?>> T registerConfiguredFeature(ResourceLocation id, T feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, feature);
    }

    private static PlacedFeature registerPlacedFeature(ResourceLocation id, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, id, feature);
    }

    public static void register() {}
}
