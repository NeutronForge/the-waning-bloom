package net.echo.waning_bloom.datagen;

import net.echo.waning_bloom.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {
    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {


        translationBuilder.add(ModBlocks.PALE_PUMPKIN, "Pale Pumpkin");
        translationBuilder.add(ModBlocks.CARVED_PALE_PUMPKIN, "Carved Pale Pumpkin");
        translationBuilder.add(ModBlocks.PALE_JACK_O_LANTERN, "Pale Jack o'Lantern");
    }
}
