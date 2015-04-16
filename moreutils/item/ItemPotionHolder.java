package moreutils.item;

import moreutils.core.MoreUtils;
import moreutils.inventory.InventoryPotionHolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemPotionHolder extends Item
{
	public ItemPotionHolder()
	{
		this.setUnlocalizedName("potionHolder");
		this.setMaxStackSize(1);
		this.setCreativeTab(MoreUtils.tabMoreUtils);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (!player.isSneaking())
		{
			InventoryPotionHolder inventory = new InventoryPotionHolder(itemstack);
			ItemStack first = inventory.getFirstPotion();

			if (first != null)
			{
				if (!ItemPotion.isSplash(first.getItemDamage()))
				{
					player.setItemInUse(itemstack, first.getItem().getMaxItemUseDuration(first));
				}
				else
				{
					inventory.throwPotion(world, player);
				}
			}
		}
		else if (!world.isRemote)
		{
			player.openGui(MoreUtils.instance, 0, world, 0, 0, 0);
		}

		return itemstack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		return false;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		new InventoryPotionHolder(stack).drinkPotion(worldIn, playerIn);

		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.DRINK;
	}
}