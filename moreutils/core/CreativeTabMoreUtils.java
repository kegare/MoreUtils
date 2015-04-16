package moreutils.core;

import moreutils.item.MoreItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabMoreUtils extends CreativeTabs
{
	public CreativeTabMoreUtils()
	{
		super("moreutils");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTabLabel()
	{
		return "MoreUtils";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTranslatedTabLabel()
	{
		return getTabLabel();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getTabIconItem()
	{
		return MoreItems.potion_holder;
	}
}