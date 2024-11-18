package net.echo.waning_bloom.datagen;

import net.echo.waning_bloom.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.StonecuttingRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static RegistryEntryLookup<Item> itemLookup;

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromItem(ItemConvertible item) {
        return RecipeGenerator.conditionsFromPredicates(ItemPredicate.Builder.create().items(itemLookup, item));
    }


    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        itemLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);
        return new RecipeGenerator(wrapperLookup, recipeExporter) {

            public static String hasItem(ItemConvertible item) {
                return "has_" + getItemPath(item);
            }

            public static String getItemPath(ItemConvertible item) {
                return Registries.ITEM.getId(item.asItem()).getPath();
            }

            public static String convertBetween(ItemConvertible to, ItemConvertible from) {
                return getItemPath(to) + "_from_" + getItemPath(from);
            }

            public static void offerCuttingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input, int count) {
                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItem(input), category, output, count).criterion(RecipeGenerator.hasItem(input), ModRecipeProvider.conditionsFromItem(input)).offerTo(exporter, RecipeGenerator.convertBetween(output, input) + "_stonecutting");
            }



            @Override
            public void generate() {


                this.createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_JACK_O_LANTERN)
                        .input('A', ModBlocks.CARVED_PALE_PUMPKIN)
                        .input('B', Blocks.TORCH)
                        .pattern("A")
                        .pattern("B")
                        .criterion("has_carved_pale_pumpkin", this.conditionsFromItem(ModBlocks.CARVED_PALE_PUMPKIN))
                        .offerTo(this.exporter);



            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
