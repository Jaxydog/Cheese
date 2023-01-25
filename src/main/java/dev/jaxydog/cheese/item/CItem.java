package dev.jaxydog.cheese.item;

import dev.jaxydog.cheese.Cheese;
import dev.jaxydog.cheese.util.LootModifier;
import dev.jaxydog.cheese.util.Registerable;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/** Item wrapper class to provide for additional mod functionality */
public class CItem extends Item implements Registerable {

	/** The mod's default item group */
	public static final ItemGroup ITEM_GROUP = FabricItemGroup
		.builder(Cheese.id("default"))
		.icon(() -> CItems.CHEESE.getDefaultStack())
		.build();

	/** The item's name, used as the path in the item identifier */
	protected final String _NAME;

	/** The item's configuration settings */
	@Nullable
	protected final Config _CONFIG;

	/** Creates a new item */
	public CItem(String name, Settings settings, Config config) {
		super(settings);
		this._NAME = name;
		this._CONFIG = config;
	}

	/** Creates a new item without additional configuration */
	public CItem(String name, Settings settings) {
		this(name, settings, null);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		Config config;

		if ((config = this._CONFIG) != null && config.isTooltipEnabled()) {
			var key = stack.getItem().getTranslationKey() + ".tooltip_";
			var i = -1;

			while (I18n.hasTranslation(key + ++i)) {
				tooltip.add(Text.translatable(key + i).formatted(Formatting.GRAY));
			}
		}

		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		Config config;
		Supplier<ItemStack> remainder;

		if ((config = this._CONFIG) != null && (remainder = config.get__consumeRemainder()) != null) {
			if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
				((PlayerEntity) user).getInventory().insertStack(remainder.get());
			}
		}

		return super.finishUsing(stack, world, user);
	}

	@Override
	public Identifier getId() {
		return Cheese.id(this._NAME);
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		Config config;

		if ((config = this._CONFIG) != null && stack.getItem().isFood()) {
			return config.is__drink() ? UseAction.DRINK : UseAction.EAT;
		} else {
			return super.getUseAction(stack);
		}
	}

	@Override
	public void register() {
		Registry.register(Registries.ITEM, this.getId(), this);
		ItemGroupEvents.modifyEntriesEvent(CItem.ITEM_GROUP).register(content -> content.add(this));

		Config config;

		if ((config = this._CONFIG) != null) {
			config.getLootModifiers().forEach(modifier -> modifier.register(this));
		}
	}

	/**
	 * Provides settings that allow configuration of modded item properties.
	 * You can think of it as my version of `Item.Settings` in a way.
	 */
	public static class Config {

		/** Whether item tooltips are enabled */
		private boolean __enableTooltip = false;
		/** Whether the item is a drink; only applies to foods */
		private boolean __drink = false;
		/** A list of loot modifiers to be registered with the item */
		private List<LootModifier> __lootModifiers = new LinkedList<>();

		/** An item stack given to the player when the item is consumed */
		@Nullable
		private Supplier<ItemStack> __consumeRemainder = null;

		/** Enables item tooltips */
		public Config withTooltip() {
			this.__enableTooltip = true;
			return this;
		}

		/** Marks the item as a drink */
		public Config drink() {
			this.__drink = true;
			return this;
		}

		/** Adds the given loot modifiers to the item */
		public Config modifyLoot(LootModifier... modifiers) {
			this.__lootModifiers.addAll(List.of(modifiers));

			return this;
		}

		/** Sets the stack given to the player when the item is consumed */
		public Config withConsumeRemainder(Supplier<ItemStack> consumeRemainder) {
			this.__consumeRemainder = consumeRemainder;
			return this;
		}

		/** Whether item tooltips are enabled; defaults to `false` */
		public boolean isTooltipEnabled() {
			return this.__enableTooltip;
		}

		/** Whether the item is a drink; defaults to `false` */
		public boolean is__drink() {
			return this.__drink;
		}

		public List<LootModifier> getLootModifiers() {
			return this.__lootModifiers;
		}

		/** Returns an item stack given to the player when the item is consumed */
		public @Nullable Supplier<ItemStack> get__consumeRemainder() {
			return this.__consumeRemainder;
		}
	}
}
