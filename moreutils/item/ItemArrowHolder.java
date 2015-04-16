package moreutils.item;

import java.util.List;
import java.util.Locale;

import moreutils.core.MoreUtils;
import moreutils.item.ItemTorchHolder.HolderType;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArrowHolder extends Item
{
	private final HolderType holderType;

	public ItemArrowHolder(HolderType type)
	{
		this.holderType = type;
		this.setUnlocalizedName("arrowHolder");
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setContainerItem(this);
		this.setCreativeTab(MoreUtils.tabMoreUtils);
	}

	public HolderType getHolderType()
	{
		return holderType;
	}

	@Override
	public String getUnlocalizedName()
	{
		return super.getUnlocalizedName() + "." + holderType.name().toLowerCase(Locale.ENGLISH);
	}

	@Override
	public int getMaxDamage()
	{
		return holderType.getCapacity() + 2;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (player.isSneaking())
		{
			rechargeArrows(itemstack, player);
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (player.isSneaking())
		{
			rechargeArrows(itemstack, player);
		}

		return itemstack;
	}

	public void rechargeArrows(ItemStack itemstack, EntityPlayer player)
	{
		InventoryPlayer inventory = player.inventory;

		if (itemstack != null && itemstack.getItem() instanceof ItemArrowHolder && itemstack.getItemDamage() != 0 && !player.capabilities.isCreativeMode)
		{
			int count = 0;

			for (int i = 0; i < inventory.getSizeInventory(); i++)
			{
				if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItem() == Items.arrow)
				{
					count += inventory.getStackInSlot(i).stackSize;
				}
			}

			while (itemstack.getItemDamage() != 0 && count != 0)
			{
				itemstack.setItemDamage(itemstack.getItemDamage() - 1);
				inventory.consumeInventoryItem(Items.arrow);

				--count;
			}

			player.worldObj.playSoundAtEntity(player, "random.pop", 1.0F, 0.35F);
		}
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemstack)
	{
		if (!hasContainerItem(itemstack))
		{
			return null;
		}

		itemstack.setItemDamage(itemstack.getItemDamage() + 1);

		return itemstack;
	}

	@Override
	public boolean isDamaged(ItemStack itemstack)
	{
		return false;
	}

	@Override
	public boolean showDurabilityBar(ItemStack itemstack)
	{
		return itemstack.getItemDamage() > 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean advanced)
	{
		list.add(I18n.format("item.arrowHolder.rest") + ": " + (itemstack.getMaxDamage() - itemstack.getItemDamage() - 2));
	}

	public static boolean hasArrowHolder(IInventory inventory)
	{
		for (int i = 0; i < inventory.getSizeInventory(); ++i)
		{
			ItemStack stack = inventory.getStackInSlot(i);

			if (stack != null && stack.getItem() instanceof ItemArrowHolder)
			{
				return true;
			}
		}

		return false;
	}
}