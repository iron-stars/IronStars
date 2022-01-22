package com.xekr.ironstars.recipe;

import com.xekr.ironstars.IronStars;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IAnvilFlatteningRecipe extends Recipe<RecipeWrapper> {
ResourceLocation TYPE_ID = new ResourceLocation(IronStars.ID, "anvil_flattening");

@Nonnull
@Override
default RecipeType<?> getType() {
	return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
}
}
