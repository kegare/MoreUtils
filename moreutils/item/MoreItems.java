package moreutils.item;

import moreutils.item.ItemTorchHolder.HolderType;
import moreutils.recipe.RecipeHolderEject;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.collect.Lists;

public class MoreItems
{
	public static final ItemPotionHolder potion_holder = new ItemPotionHolder();
	public static final ItemTorchHolder iron_torch_holder = new ItemTorchHolder(HolderType.IRON);
	public static final ItemTorchHolder gold_torch_holder = new ItemTorchHolder(HolderType.GOLD);
	public static final ItemTorchHolder diamond_torch_holder = new ItemTorchHolder(HolderType.DIAMOND);
	public static final ItemArrowHolder iron_arrow_holder = new ItemArrowHolder(HolderType.IRON);
	public static final ItemArrowHolder gold_arrow_holder = new ItemArrowHolder(HolderType.GOLD);
	public static final ItemArrowHolder diamond_arrow_holder = new ItemArrowHolder(HolderType.DIAMOND);
	public static final ItemSpanner spanner = new ItemSpanner();

	public static void registerItems()
	{
		GameRegistry.registerItem(potion_holder, "potion_holder");
		GameRegistry.registerItem(iron_torch_holder, "iron_torch_holder");
		GameRegistry.registerItem(gold_torch_holder, "gold_torch_holder");
		GameRegistry.registerItem(diamond_torch_holder, "diamond_torch_holder");
		GameRegistry.registerItem(iron_arrow_holder, "iron_arrow_holder");
		GameRegistry.registerItem(gold_arrow_holder, "gold_arrow_holder");
		GameRegistry.registerItem(diamond_arrow_holder, "diamond_arrow_holder");
		GameRegistry.registerItem(spanner, "spanner");
	}

	public static void registerRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(potion_holder),
			"SLS", "BBB",
			'S', Items.string,
			'L', Items.leather,
			'B', Items.glass_bottle
		);

		ItemStack stack = new ItemStack(iron_torch_holder);
		stack.setItemDamage(stack.getMaxDamage() - 3);

		GameRegistry.addRecipe(new ShapedOreRecipe(stack,
			"ITL", "I I", "III",
			'I', "ingotIron",
			'L', Items.leather,
			'T', Blocks.torch
		));

		stack = new ItemStack(gold_torch_holder);
		stack.setItemDamage(stack.getMaxDamage() - 3);

		GameRegistry.addRecipe(new ShapedOreRecipe(stack,
			"ITL", "I I", "IRI",
			'I', "ingotGold",
			'L', Items.leather,
			'R', Items.blaze_rod,
			'T', Blocks.torch
		));

		stack = new ItemStack(diamond_torch_holder);
		stack.setItemDamage(stack.getMaxDamage() - 3);

		GameRegistry.addRecipe(new ShapedOreRecipe(stack,
			"ITL", "I I", "ISI",
			'I', "gemDiamond",
			'L', Items.leather,
			'S', Items.nether_star,
			'T', Blocks.torch
		));

		stack = new ItemStack(iron_arrow_holder);
		stack.setItemDamage(stack.getMaxDamage() - 3);

		GameRegistry.addRecipe(new ShapedOreRecipe(stack,
			"IAL", "I I", "III",
			'I', "ingotIron",
			'L', Items.leather,
			'A', Items.arrow
		));

		stack = new ItemStack(gold_arrow_holder);
		stack.setItemDamage(stack.getMaxDamage() - 3);

		GameRegistry.addRecipe(new ShapedOreRecipe(stack,
			"IAL", "I I", "IRI",
			'I', "ingotGold",
			'L', Items.leather,
			'R', Items.blaze_rod,
			'A', Items.arrow
		));

		stack = new ItemStack(diamond_arrow_holder);
		stack.setItemDamage(stack.getMaxDamage() - 3);

		GameRegistry.addRecipe(new ShapedOreRecipe(stack,
			"IAL", "I I", "ISI",
			'I', "gemDiamond",
			'L', Items.leather,
			'S', Items.nether_star,
			'A', Items.arrow
		));

		GameRegistry.addRecipe(new ShapedOreRecipe(spanner,
			"I  ", " I ", "  I",
			'I', "ingotIron"
		));

		GameRegistry.addRecipe(new RecipeHolderEject(new ItemStack(Blocks.torch), Lists.newArrayList(new ItemStack(iron_torch_holder, 1, OreDictionary.WILDCARD_VALUE))));
		GameRegistry.addRecipe(new RecipeHolderEject(new ItemStack(Blocks.torch), Lists.newArrayList(new ItemStack(gold_torch_holder, 1, OreDictionary.WILDCARD_VALUE))));
		GameRegistry.addRecipe(new RecipeHolderEject(new ItemStack(Blocks.torch), Lists.newArrayList(new ItemStack(diamond_torch_holder, 1, OreDictionary.WILDCARD_VALUE))));
		GameRegistry.addRecipe(new RecipeHolderEject(new ItemStack(Items.arrow), Lists.newArrayList(new ItemStack(iron_arrow_holder, 1, OreDictionary.WILDCARD_VALUE))));
		GameRegistry.addRecipe(new RecipeHolderEject(new ItemStack(Items.arrow), Lists.newArrayList(new ItemStack(gold_arrow_holder, 1, OreDictionary.WILDCARD_VALUE))));
		GameRegistry.addRecipe(new RecipeHolderEject(new ItemStack(Items.arrow), Lists.newArrayList(new ItemStack(diamond_arrow_holder, 1, OreDictionary.WILDCARD_VALUE))));
	}

	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		ModelLoader.setCustomModelResourceLocation(potion_holder, 0, new ModelResourceLocation("moreutils:potion_holder", "inventory"));
		ModelLoader.setCustomModelResourceLocation(iron_torch_holder, 0, new ModelResourceLocation("moreutils:iron_torch_holder", "inventory"));
		ModelLoader.setCustomModelResourceLocation(gold_torch_holder, 0, new ModelResourceLocation("moreutils:gold_torch_holder", "inventory"));
		ModelLoader.setCustomModelResourceLocation(diamond_torch_holder, 0, new ModelResourceLocation("moreutils:diamond_torch_holder", "inventory"));
		ModelLoader.setCustomModelResourceLocation(iron_arrow_holder, 0, new ModelResourceLocation("moreutils:iron_arrow_holder", "inventory"));
		ModelLoader.setCustomModelResourceLocation(gold_arrow_holder, 0, new ModelResourceLocation("moreutils:gold_arrow_holder", "inventory"));
		ModelLoader.setCustomModelResourceLocation(diamond_arrow_holder, 0, new ModelResourceLocation("moreutils:diamond_arrow_holder", "inventory"));
		ModelLoader.setCustomModelResourceLocation(spanner, 0, new ModelResourceLocation("moreutils:spanner", "inventory"));
	}
}