package moreutils.handler;

import java.util.Random;

import moreutils.item.ItemArrowHolder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MoreEventHooks
{
	protected static final Random eventRand = new Random();

	@SubscribeEvent
	public void onArrowLoose(ArrowLooseEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack bow = event.bow;

		if (bow != null && bow.getItem() == Items.bow && ItemArrowHolder.hasArrowHolder(player.inventory))
		{
			World world = player.worldObj;
			float f = event.charge / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (f < 0.1D)
			{
				return;
			}

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			EntityArrow entity = new EntityArrow(world, player, f * 2.0F);

			if (f == 1.0F)
			{
				entity.setIsCritical(true);
			}

			int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, bow);

			if (i > 0)
			{
				entity.setDamage(entity.getDamage() + i * 0.5D + 0.5D);
			}

			i = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, bow);

			if (i > 0)
			{
				entity.setKnockbackStrength(i);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, bow) > 0)
			{
				entity.setFire(100);
			}

			bow.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (eventRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (!player.capabilities.isCreativeMode)
			{
				ItemStack[] items = player.inventory.mainInventory;

				for (int j = 0; j < items.length; ++j)
				{
					if (items[j] != null && items[j].getItem() instanceof ItemArrowHolder)
					{
						items[j].damageItem(1, player);
					}
				}
			}

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entity);
			}

			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onArrowNock(ArrowNockEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack result = event.result;

		if (result != null && result.getItem() == Items.bow && ItemArrowHolder.hasArrowHolder(player.inventory))
		{
			player.setItemInUse(result, result.getMaxItemUseDuration());
		}
	}
}