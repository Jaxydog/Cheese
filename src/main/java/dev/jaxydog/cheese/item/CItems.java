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

	/** Cheese cloth item */
	public static final CItem CHEESE_CLOTH = new CItem(
		"cheese_cloth",
		new Settings().rarity(Rarity.UNCOMMON),
		new Config().withTooltip()
	);

	/** Cheese cloth item */
	public static final CItem CHEESE_PIZZA = new CItem(
		"cheese_pizza",
		new Settings()
			.food(new FoodComponent.Builder().hunger(8).saturationModifier(0.75f).build())
			.rarity(Rarity.UNCOMMON),
		new Config().withTooltip()
	);

	/** Cheese steak item */
	public static final CItem CHEESE_STEAK = new CItem(
		"cheese_steak",
		new Settings()
			.food(new FoodComponent.Builder().hunger(15).meat().saturationModifier(0.75f).build())
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

	/** Cheesy dino nugget item */
	public static final CItem CHEESY_DINO_NUGGET = new CItem(
		"cheesy_dino_nugget",
		new Settings()
			.food(new FoodComponent.Builder().hunger(4).meat().saturationModifier(0.75f).build())
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

	/** Dino nugget item */
	public static final CItem DINO_NUGGET = new CItem(
		"dino_nugget",
		new Settings()
			.food(new FoodComponent.Builder().hunger(3).meat().saturationModifier(0.65f).build())
			.rarity(Rarity.COMMON),
		new Config().withTooltip()
	);

	/** Dough item */
	public static final CItem DOUGH = new CItem(
		"dough",
		new Settings().rarity(Rarity.COMMON),
		new Config().withTooltip()
	);

	/** Grilled cheese item */
	public static final CItem GRILLED_CHEESE = new CItem(
		"grilled_cheese",
		new Settings()
			.food(new FoodComponent.Builder().hunger(8).saturationModifier(0.625f).build())
			.rarity(Rarity.UNCOMMON),
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

	/** Tomato item */
	public static final CAliasedBlockItem TOMATO = new CAliasedBlockItem(
		"tomato",
		CBlocks.TOMATOES,
		new Settings()
			.food(new FoodComponent.Builder().hunger(1).saturationModifier(1.25f).build())
			.rarity(Rarity.COMMON)
	);

	/** Tomato sauce item */
	public static final CItem TOMATO_SAUCE = new CItem(
		"tomato_sauce",
		new Settings()
			.food(new FoodComponent.Builder().hunger(2).saturationModifier(1.125f).build())
			.rarity(Rarity.COMMON),
		new Config().drink().withConsumeRemainder(() -> Items.GLASS_BOTTLE.getDefaultStack())
	);

	/** Registers all of the mod's items at once */
	public static void register() {
		CItems.CHEESE.register();
		CItems.CHEESE_AND_CRACKERS.register();
		CItems.CHEESE_BLOCK.register();
		CItems.CHEESE_CLOTH.register();
		CItems.CHEESE_PIZZA.register();
		CItems.CHEESE_STEAK.register();
		CItems.CHEESY_BAKED_POTATO.register();
		CItems.CHEESY_DINO_NUGGET.register();
		CItems.CRACKERS.register();
		CItems.DINO_NUGGET.register();
		CItems.DOUGH.register();
		CItems.GRILLED_CHEESE.register();
		CItems.MACARONI.register();
		CItems.MACARONI_AND_CHEESE.register();
		CItems.TOMATO.register();
		CItems.TOMATO_SAUCE.register();
	}
}
