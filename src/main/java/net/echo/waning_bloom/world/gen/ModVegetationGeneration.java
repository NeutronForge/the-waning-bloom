package net.echo.waning_bloom.world.gen;

import net.echo.waning_bloom.world.gen.feature.ModVegetationPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModVegetationGeneration {
    public static void generateVegetation() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PALE_GARDEN), GenerationStep.Feature.VEGETAL_DECORATION, ModVegetationPlacedFeatures.PATCH_PALE_PUMPKIN);
    }
}
