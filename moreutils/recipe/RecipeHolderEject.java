package moreutils.recipe;

import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;

public class RecipeHolderEject extends ShapelessRecipes
{
	public RecipeHolderEject(ItemStack itemstack, List list)
	{
		super(itemstack, list);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting)
	{
		for (int i = 0; i < crafting.getSizeInventory(); ++i)
		{
			ItemStack item = crafting.getStackInSlot(i);

			if (item != null && item.getItem() == ((ItemStack)recipeItems.get(0)).getItem() && item.getItemDamage() < item.getMaxDamage() - 2)
			{
				return getRecipeOutput().copy();
			}
		}

		return null;
	}
}