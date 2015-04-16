package moreutils.item;

import moreutils.core.MoreUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.world.World;

public class ItemSpanner extends Item
{
	public ItemSpanner()
	{
		this.setUnlocalizedName("spanner");
		this.setMaxStackSize(1);
		this.setMaxDamage(256);
		this.setCreativeTab(MoreUtils.tabMoreUtils);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (player.isSneaking())
		{
			if (block instanceof BlockStairs)
			{
				EnumHalf half = (EnumHalf)state.getValue(BlockStairs.HALF);

				if (world.setBlockState(pos, state.withProperty(BlockStairs.HALF, half == EnumHalf.TOP ? EnumHalf.BOTTOM : EnumHalf.TOP), 2))
				{
					return onBlockPlaced(stack, player, world, pos, block);
				}
			}
		}

		if (block.rotateBlock(world, pos, side.getOpposite()))
		{
			return onBlockPlaced(stack, player, world, pos, block);
		}

		if (block instanceof BlockLog)
		{
			EnumAxis axis = (EnumAxis)state.getValue(BlockLog.LOG_AXIS);

			switch (axis)
			{
				case X:
					axis = EnumAxis.Z;
					break;
				case Y:
					axis = EnumAxis.X;
					break;
				case Z:
					axis = EnumAxis.Y;
					break;
				default:
					axis = EnumAxis.X;
			}

			if (world.setBlockState(pos, state.withProperty(BlockLog.LOG_AXIS, axis), 2))
			{
				return onBlockPlaced(stack, player, world, pos, block);
			}
		}

		if (block instanceof BlockRotatedPillar)
		{
			Axis axis = (Axis)state.getValue(BlockRotatedPillar.AXIS);

			switch (axis)
			{
				case X:
					axis = Axis.Z;
					break;
				case Y:
					axis = Axis.X;
					break;
				case Z:
					axis = Axis.Y;
					break;
				default:
					axis = Axis.X;
			}

			if (world.setBlockState(pos, state.withProperty(BlockRotatedPillar.AXIS, axis), 2))
			{
				return onBlockPlaced(stack, player, world, pos, block);
			}
		}

		if (block instanceof BlockSlab)
		{
			EnumBlockHalf half = (EnumBlockHalf)state.getValue(BlockSlab.HALF);

			if (world.setBlockState(pos, state.withProperty(BlockSlab.HALF, half == EnumBlockHalf.TOP ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP), 2))
			{
				return onBlockPlaced(stack, player, world, pos, block);
			}
		}

		return false;
	}

	private boolean onBlockPlaced(ItemStack stack, EntityPlayer player, World world, BlockPos pos, Block block)
	{
		world.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F);

		stack.damageItem(1, player);

		player.swingItem();

		return !world.isRemote;
	}
}