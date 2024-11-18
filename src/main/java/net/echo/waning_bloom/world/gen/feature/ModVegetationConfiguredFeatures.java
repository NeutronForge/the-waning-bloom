package net.echo.waning_bloom.world.gen.feature;

import net.echo.waning_bloom.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ModVegetationConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_PALE_PUMPKIN = ModConfiguredFeatures.of("patch_pale_pumpkin");





    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ModConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        ModConfiguredFeatures.register(
                featureRegisterable,
                PATCH_PALE_PUMPKIN,
                Feature.RANDOM_PATCH,
                ModConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PALE_PUMPKIN)), List.of(Blocks.GRASS_BLOCK)
                )
        );





    }
}
