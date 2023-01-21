package dev.jaxydog.cheese.item;

import dev.jaxydog.cheese.Cheese;
import dev.jaxydog.cheese.util.Registerable;
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

		if ((config = this._CONFIG) != null && (remainder = config.getConsumeRemainder()) != null) {
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
	public void register() {
		Registry.register(Registries.ITEM, this.getId(), this);
		ItemGroupEvents.modifyEntriesEvent(CItem.ITEM_GROUP).register(content -> content.add(this));
	}

	/**
	 * Provides settings that allow configuration of modded item properties.
	 * You can think of it as my version of `Item.Settings` in a way.
	 */
	public static class Config {

		/** Whether item tooltips are enabled */
		private boolean enableTooltip = false;

		/** An item stack given to the player when the item is consumed */
		@Nullable
		private Supplier<ItemStack> consumeRemainder = null;

		/** Enables item tooltips */
		public Config withTooltip() {
			this.enableTooltip = true;
			return this;
		}

		/** Sets the stack given to the player when the item is consumed */
		public Config withConsumeRemainder(Supplier<ItemStack> consumeRemainder) {
			this.consumeRemainder = consumeRemainder;
			return this;
		}

		/** Whether item tooltips are enabled; defaults to `false` */
		public boolean isTooltipEnabled() {
			return this.enableTooltip;
		}

		/** Returns an item stack given to the player when the item is consumed */
		public @Nullable Supplier<ItemStack> getConsumeRemainder() {
			return this.consumeRemainder;
		}
	}
}
