package net.echo.waning_bloom.item;

import net.echo.waning_bloom.TheWaningBloom;
import net.echo.waning_bloom.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {


    public static void registerNaturalToVanillaItemGroups() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Blocks.JACK_O_LANTERN, ModBlocks.PALE_PUMPKIN);
            content.addAfter(ModBlocks.PALE_PUMPKIN, ModBlocks.CARVED_PALE_PUMPKIN);
            content.addAfter(ModBlocks.CARVED_PALE_PUMPKIN, ModBlocks.PALE_JACK_O_LANTERN);

        });

    }



    public static <T extends Item> T registerItem(String name, Function<Item.Settings, T> factory) {
        return registerItem(Identifier.of(TheWaningBloom.MOD_ID, name), factory);
    }

    public static <T extends Item> T registerItem(Identifier identifier, Function<Item.Settings, T> factory) {
        return Registry.register(
                Registries.ITEM,
                identifier,
                factory.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier)))
        );
    }

    public static void registerModItems() {
        TheWaningBloom.LOGGER.info("Registering mod items for " + TheWaningBloom.MOD_ID);

        registerNaturalToVanillaItemGroups();

    }

}
