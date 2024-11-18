package net.echo.waning_bloom.world.gen.feature;

import net.echo.waning_bloom.TheWaningBloom;
import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {





        ModVegetationConfiguredFeatures.bootstrap(featureRegisterable);
    }





    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        BlockPredicate blockPredicate;
        if (!validGround.isEmpty()) {
            blockPredicate = BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), validGround));
        } else {
            blockPredicate = BlockPredicate.IS_AIR;
        }

        return blockPredicate;
    }

    public static RandomPatchFeatureConfig createRandomPatchFeatureConfig(int tries, RegistryEntry<PlacedFeature> feature) {
        return new RandomPatchFeatureConfig(tries, 7, 3, feature);
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RandomPatchFeatureConfig createRandomPatchFeatureConfig(
            F feature, FC config, List<Block> predicateBlocks, int tries
    ) {
        return createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(feature, config, createBlockPredicate(predicateBlocks)));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RandomPatchFeatureConfig createRandomPatchFeatureConfig(
            F feature, FC config, List<Block> predicateBlocks
    ) {
        return createRandomPatchFeatureConfig(feature, config, predicateBlocks, 96);
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RandomPatchFeatureConfig createRandomPatchFeatureConfig(F feature, FC config) {
        return createRandomPatchFeatureConfig(feature, config, List.of(), 96);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TheWaningBloom.MOD_ID, id));
    }

    public static void register(
            Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, Feature<DefaultFeatureConfig> feature
    ) {
        register(registerable, key, feature, FeatureConfig.DEFAULT);
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config
    ) {
        registerable.register(key, new ConfiguredFeature<>(feature, config));
    }


}
