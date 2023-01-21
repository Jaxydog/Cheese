package dev.jaxydog.cheese.item;

import dev.jaxydog.cheese.block.CBlocks;
import dev.jaxydog.cheese.item.CItem.Config;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.Items;
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

	/** Cheese and crackers item */
	public static final CItem CHEESE_AND_CRACKERS = new CItem(
		"cheese_and_crackers",
		new Settings()
			.food(new FoodComponent.Builder().hunger(2).saturationModifier(1.25f).snack().build())
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

	/** Cheese steak item */
	public static final CItem CHEESE_STEAK = new CItem(
		"cheese_steak",
		new Settings()
			.food(new FoodComponent.Builder().hunger(15).saturationModifier(0.75f).build())
			.rarity(Rarity.UNCOMMON),
		new Config().withTooltip()
	);

	/** Cheesy baked potato item */
	public static final CItem CHEESY_BAKED_POTATO = new CItem(
		"cheesy_baked_potato",
		new Settings()
			.food(new FoodComponent.Builder().hunger(6).saturationModifier(0.875f).build())
			.rarity(Rarity.UNCOMMON),
		new Config().withTooltip()
	);

	/** Crackers item */
	public static final CItem CRACKERS = new CItem(
		"crackers",
		new Settings()
			.food(new FoodComponent.Builder().hunger(3).saturationModifier(0.5f).build())
			.rarity(Rarity.COMMON),
		new Config().withTooltip()
	);

	/** Macaroni item */
	public static final CItem MACARONI = new CItem(
		"macaroni",
		new Settings()
			.food(new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).snack().build())
			.rarity(Rarity.COMMON),
		new Config().withTooltip()
	);

	/** Macaroni and cheese item */
	public static final CItem MACARONI_AND_CHEESE = new CItem(
		"macaroni_and_cheese",
		new Settings()
			.food(new FoodComponent.Builder().hunger(6).saturationModifier(1.25f).build())
			.rarity(Rarity.UNCOMMON),
		new Config().withConsumeRemainder(() -> Items.BOWL.getDefaultStack()).withTooltip()
	);

	/** Registers all of the mod's items at once */
	public static void register() {
		CItems.CHEESE.register();
		CItems.CHEESE_AND_CRACKERS.register();
		CItems.CHEESE_BLOCK.register();
		CItems.CHEESE_STEAK.register();
		CItems.CHEESY_BAKED_POTATO.register();
		CItems.CRACKERS.register();
		CItems.MACARONI.register();
		CItems.MACARONI_AND_CHEESE.register();
	}
}
