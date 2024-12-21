package net.echo.waning_bloom.block;

import net.echo.waning_bloom.TheWaningBloom;
import net.echo.waning_bloom.block.custom.CarvedPalePumpkinBlock;
import net.echo.waning_bloom.block.custom.DormantCreakingHeartBlock;
import net.echo.waning_bloom.block.custom.PalePumpkinBlock;
import net.echo.waning_bloom.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.function.Function;

public class ModBlocks {

    public static final Block DORMANT_CREAKING_HEART = registerBlockWithItem(
            "dormant_creaking_heart",
            DormantCreakingHeartBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).strength(8.0F).sounds(BlockSoundGroup.CREAKING_HEART)).getLeft();


    public static final Block PALE_PUMPKIN = registerBlockWithItem("pale_pumpkin", PalePumpkinBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)).getLeft();
    public static final Block CARVED_PALE_PUMPKIN = registerBlockWithItem("carved_pale_pumpkin", CarvedPalePumpkinBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)).getLeft();
    public static final Block PALE_JACK_O_LANTERN = registerBlockWithItem("pale_jack_o_lantern", CarvedPalePumpkinBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance(state -> 10).pistonBehavior(PistonBehavior.DESTROY)).getLeft();




    // ----------------- //

    public static <T extends Block> Pair<T, BlockItem> registerBlockWithItem(String name, Function<AbstractBlock.Settings, T> factory) {
        return registerBlockWithItem(name, factory, AbstractBlock.Settings.create());
    }
    public static <T extends Block> Pair<T, BlockItem> registerBlockWithItem(String name, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        return registerBlockWithItem(Identifier.of(TheWaningBloom.MOD_ID, name), factory, settings);
    }
    public static <T extends Block> Pair<T, BlockItem> registerBlockWithItem(Identifier identifier, Function<AbstractBlock.Settings, T> factory) {
        return registerBlockWithItem(identifier, factory, AbstractBlock.Settings.create());
    }
    public static <T extends Block> Pair<T, BlockItem> registerBlockWithItem(Identifier identifier, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        T block = registerBlock(identifier, factory, settings);

        return new Pair<>(block, ModItems.registerItem(identifier, (itemSettings) -> new BlockItem(block, itemSettings.useBlockPrefixedTranslationKey())));
    }


    public static <T extends Block> T registerBlock(String name, Function<AbstractBlock.Settings, T> factory) {
        return registerBlock(name, factory, AbstractBlock.Settings.create());
    }
    public static <T extends Block> T registerBlock(String name, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        return registerBlock(Identifier.of(TheWaningBloom.MOD_ID, name), factory, settings);
    }
    public static <T extends Block> T registerBlock(Identifier identifier, Function<AbstractBlock.Settings, T> factory) {
        return registerBlock(identifier, factory, AbstractBlock.Settings.create());
    }
    public static <T extends Block> T registerBlock(Identifier identifier, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        return Registry.register(
                Registries.BLOCK,
                identifier,
                factory.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, identifier)))
        );
    }


    public static void registerModBlocks() {
        TheWaningBloom.LOGGER.info("Registering ModBlocks for " + TheWaningBloom.MOD_ID);
    }
}
