package dev.jaxydog.cheese.item;

import dev.jaxydog.cheese.block.CBlocks;
import dev.jaxydog.cheese.item.CItem.Config;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Rarity;

/** Contains all of the mod's item definitions */
public class CItems {

	/** Cheese item */
	public static final CItem CHEESE = new CItem(
		"cheese",
		new Settings()
			.food(new FoodComponent.Builder().hunger(1).saturationModifier(1.125f).snack().build())
			.rarity(Rarity.UNCOMMON),
		new Config().withTooltip()
	);

	/** Cheese block item */
	public static final CBlockItem CHEESE_BLOCK = new CBlockItem(
		"cheese_block",
		CBlocks.CHEESE_BLOCK,
		new Settings().rarity(Rarity.UNCOMMON),
		new Config().withTooltip()
	);

	/** Registers all of the mod's items at once */
	public static void register() {
		CItems.CHEESE.register();
		CItems.CHEESE_BLOCK.register();
	}
}
