package com.xekr.ironstars.recipe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnvilFlatteningCraftingManager {

public static void craft(Level world, BlockPos pos) {
	if (world.isClientSide()) {
		return;
	}

	List<ItemEntity> itemEntityList = world.getEntitiesOfClass(ItemEntity.class, new AABB(pos));
	NonNullList<ItemStack> itemStacks = NonNullList.create();

	for (ItemEntity ie : itemEntityList) {
		itemStacks.add(ie.getItem());
	}


	RecipeWrapper inv = new RecipeWrapper(new ItemStackHandler(itemStacks));

	Optional<IAnvilFlatteningRecipe> recipeOptional = world.getRecipeManager().getRecipeFor(AnvilRecipeTypes.ANVIL_FLATTENING_TYPE, inv, world);

	if (!recipeOptional.isPresent()) {
		// No recipe for inputs
		return;
	}

	IAnvilFlatteningRecipe recipe = recipeOptional.get();

	NonNullList<Ingredient> ingredients = recipe.getIngredients();
	List<Ingredient> ingredientsMissing = new ArrayList<>(ingredients);
	for (ItemEntity ie : itemEntityList) {
		ItemStack item = ie.getItem();
		for (int i = 0; i < ingredientsMissing.size(); i++) {
			Ingredient ingr = ingredientsMissing.get(i);
			if (ingr.test(item)) {
				item.setCount(0);
				ingredientsMissing.remove(i);
				break;
			}
		}
	}

	world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), recipe.getResultItem()));
}
}
