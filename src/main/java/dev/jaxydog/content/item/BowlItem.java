package dev.jaxydog.content.item;

import dev.jaxydog.utility.LootModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BowlItem extends CustomItem {

	public BowlItem(String rawId, Settings settings, LootModifier... lootModifiers) {
		super(rawId, settings, lootModifiers);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
			((PlayerEntity) user).getInventory().insertStack(Items.BOWL.getDefaultStack());
		}

		return super.finishUsing(stack, world, user);
	}
}
