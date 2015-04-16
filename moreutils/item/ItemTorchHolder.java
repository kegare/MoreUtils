package moreutils.item;

import java.util.List;
import java.util.Locale;

import moreutils.core.MoreUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTorchHolder extends Item
{
	public enum HolderType
	{
		IRON(256),
		GOLD(1024),
		DIAMOND(4096);

		private final int capacity;

		private HolderType(int capacity)
		{
			this.capacity = capacity;
		}

		public int getCapacity()
		{
			return capacity;
		}
	}

	private final HolderType holderType;

	public ItemTorchHolder(HolderType type)
	{
		this.holderType = type;
		this.setUnlocalizedName("torchHolder");
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
		boolean result = false;

		if (itemstack.getItemDamage() < itemstack.getMaxDamage() - 2)
		{
			if (Item.getItemFromBlock(Blocks.torch).onItemUse(new ItemStack(Blocks.torch, 1), player, world, pos, side, hitX, hitY, hitZ))
			{
				itemstack.damageItem(1, player);
				result = true;
			}
		}

		if (player.isSneaking() && !result)
		{
			rechargeTorches(itemstack, player);
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (player.isSneaking())
		{
			rechargeTorches(itemstack, player);
		}

		return itemstack;
	}

	public void rechargeTorches(ItemStack itemstack, EntityPlayer player)
	{
		InventoryPlayer inventory = player.inventory;

		if (itemstack != null && itemstack.getItem() instanceof ItemTorchHolder && itemstack.getItemDamage() != 0 && !player.capabilities.isCreativeMode)
		{
			int count = 0;

			for (int i = 0; i < inventory.getSizeInventory(); i++)
			{
				if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItem() == Item.getItemFromBlock(Blocks.torch))
				{
					count += inventory.getStackInSlot(i).stackSize;
				}
			}

			while (itemstack.getItemDamage() != 0 && count != 0)
			{
				itemstack.setItemDamage(itemstack.getItemDamage() - 1);
				inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.torch));

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
		list.add(I18n.format("item.torchHolder.rest") + ": " + (itemstack.getMaxDamage() - itemstack.getItemDamage() - 2));
	}
}