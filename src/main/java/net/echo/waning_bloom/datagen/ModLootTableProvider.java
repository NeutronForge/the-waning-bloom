package net.echo.waning_bloom.datagen;

import net.echo.waning_bloom.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        this.addDrop(ModBlocks.PALE_PUMPKIN);
        this.addDrop(ModBlocks.CARVED_PALE_PUMPKIN);
        this.addDrop(ModBlocks.PALE_JACK_O_LANTERN);


    }
}
