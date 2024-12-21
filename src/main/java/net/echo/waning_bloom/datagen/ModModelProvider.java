package net.echo.waning_bloom.datagen;

import net.echo.waning_bloom.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.data.TexturedModel;
import net.minecraft.item.Item;


public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);

    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {



        blockStateModelGenerator.registerSingleton(ModBlocks.PALE_PUMPKIN, TexturedModel.CUBE_COLUMN);
        TextureMap palePumpkinTextureMap = TextureMap.sideEnd(ModBlocks.PALE_PUMPKIN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.CARVED_PALE_PUMPKIN, palePumpkinTextureMap);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.PALE_JACK_O_LANTERN, palePumpkinTextureMap);



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {



    }


}
