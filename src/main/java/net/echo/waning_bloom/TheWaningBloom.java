package net.echo.waning_bloom;

import net.echo.waning_bloom.block.ModBlocks;
import net.echo.waning_bloom.datagen.ModWorldGenerator;
import net.echo.waning_bloom.item.ModItems;
import net.echo.waning_bloom.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheWaningBloom implements ModInitializer {
	public static final String MOD_ID = "waning_bloom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModWorldGeneration.generateModWorldGen();
	}
}