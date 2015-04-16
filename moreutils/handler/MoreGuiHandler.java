package moreutils.handler;

import moreutils.client.gui.GuiPotionHolder;
import moreutils.inventory.ContainerPotionHolder;
import moreutils.inventory.InventoryPotionHolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoreGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				return new ContainerPotionHolder(player.inventory, new InventoryPotionHolder(player.getCurrentEquippedItem()));
			default:
				return null;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				return new GuiPotionHolder(player.inventory, new InventoryPotionHolder(player.getCurrentEquippedItem()));
			default:
				return null;
		}
	}
}