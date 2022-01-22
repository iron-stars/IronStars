package com.xekr.ironstars.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

public class RecipeAnvilFlattening implements IAnvilFlatteningRecipe {

private final ResourceLocation id;
private final ItemStack output;
private final NonNullList<Ingredient> inputs;

public RecipeAnvilFlattening(ResourceLocation id, ItemStack output, Ingredient... inputs) {
	this.id = id;
	this.output = output;
	this.inputs = NonNullList.of(Ingredient.EMPTY, inputs);
}

@Override
public boolean matches(RecipeWrapper inv, Level worldIn) {
	List<Ingredient> ingredientsMissing = new ArrayList<>(inputs);

	for (int i = 0; i < inv.getContainerSize(); i++) {
		ItemStack input = inv.getItem(i);
		if (input.isEmpty()) {
			break;
		}

		int count = input.getCount();
		while (count > 0) {

			int stackIndex = -1;
			for (int j = 0; j < ingredientsMissing.size(); j++) {
				Ingredient ingr = ingredientsMissing.get(j);
				if (ingr.test(input)) {
					stackIndex = j;
					break;
				}
			}

			if (stackIndex != -1) {
				ingredientsMissing.remove(stackIndex);
				count--;
			} else {
				return false;
			}
		}
	}

	return ingredientsMissing.isEmpty();
}

@Override
public ItemStack assemble(RecipeWrapper p_77572_1_) {
	return null;
}

@Override
public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
	return true;
}

@Override
public ItemStack getResultItem() {
	return this.output;
}

@Override
public NonNullList<Ingredient> getIngredients() {
	return this.inputs;
}

@Override
public ResourceLocation getId() {
	return this.id;
}

@Override
public RecipeSerializer<?> getSerializer() {
	return AnvilRecipeTypes.ANVIL_FLATTENING_SERIALIZER.get();
}

public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<RecipeAnvilFlattening> {

	@Override
	public RecipeAnvilFlattening fromJson(ResourceLocation recipeId, JsonObject json) {
		ItemStack ouput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
		JsonArray ingrs = GsonHelper.getAsJsonArray(json, "ingredients");
		List<Ingredient> inputs = new ArrayList<>();
		for (JsonElement e : ingrs) {
			inputs.add(Ingredient.fromJson(e));
		}
		return new RecipeAnvilFlattening(recipeId, ouput, inputs.toArray(new Ingredient[0]));
	}

	@Override
	public RecipeAnvilFlattening fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buf) {
		Ingredient[] inputs = new Ingredient[buf.readVarInt()];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = Ingredient.fromNetwork(buf);
		}
		ItemStack output = buf.readItem();
		return new RecipeAnvilFlattening(recipeId, output, inputs);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buf, RecipeAnvilFlattening recipe) {
		buf.writeVarInt(recipe.getIngredients().size());
		for (Ingredient input : recipe.getIngredients()) {
			input.toNetwork(buf);
		}
		buf.writeItemStack(recipe.getResultItem(), false);
	}
}
}
