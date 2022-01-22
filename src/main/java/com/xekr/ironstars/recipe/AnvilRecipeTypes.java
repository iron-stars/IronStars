package com.xekr.ironstars.recipe;

import com.xekr.ironstars.IronStars;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AnvilRecipeTypes {

public static final MyRecipeType<IAnvilFlatteningRecipe> ANVIL_FLATTENING_TYPE = registerType(IAnvilFlatteningRecipe.TYPE_ID);
public static final RecipeSerializer<RecipeAnvilFlattening> ANVIL_FLATTENING_RECIPE_SERIALIZER = new RecipeAnvilFlattening.Serializer();

public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, IronStars.ID);

public static final RegistryObject<RecipeSerializer<?>> ANVIL_FLATTENING_SERIALIZER = RECIPE_SERIALIZERS.register("anvil_flattening", () -> ANVIL_FLATTENING_RECIPE_SERIALIZER);

private static class MyRecipeType<T extends Recipe<?>> implements RecipeType<T> {
	@Override
	public String toString() {
		return Registry.RECIPE_TYPE.getKey(this).toString();
	}
}

private static <T extends Recipe<?>> MyRecipeType<T> registerType(ResourceLocation recipeTypeId) {
	return Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new MyRecipeType<>());
}

public static void init() {
	RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
}
}
