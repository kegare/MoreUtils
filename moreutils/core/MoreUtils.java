package moreutils.core;

import moreutils.handler.MoreEventHooks;
import moreutils.handler.MoreGuiHandler;
import moreutils.item.MoreItems;
import moreutils.recipe.RecipeHolderEject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

@Mod(modid = "moreutils", acceptedMinecraftVersions = "[1.8,)")
public class MoreUtils
{
	@Instance("moreutils")
	public static MoreUtils instance;

	public static final CreativeTabs tabMoreUtils = new CreativeTabMoreUtils();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		RecipeSorter.register("moreutils:holder.eject", RecipeHolderEject.class, Category.SHAPELESS, "after:minecraft:shapeless");

		MoreItems.registerItems();

		if (event.getSide().isClient())
		{
			MoreItems.registerModels();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MoreItems.registerRecipes();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new MoreGuiHandler());

		MinecraftForge.EVENT_BUS.register(new MoreEventHooks());
	}
}