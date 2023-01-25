package dev.jaxydog.cheese.item;

import dev.jaxydog.cheese.Cheese;
import dev.jaxydog.cheese.item.CItem.Config;
import dev.jaxydog.cheese.util.Registerable;
import java.util.List;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/** Block item wrapper class to provide for additional mod functionality */
public class CBlockItem extends BlockItem implements Registerable {

	/** The item's name, used as the path in the item identifier */
	protected final String _NAME;

	/** The item's configuration settings */
	@Nullable
	protected final Config _CONFIG;

	/** Creates a new item */
	public CBlockItem(String name, Block block, Settings settings, Config config) {
		super(block, settings);
		this._NAME = name;
		this._CONFIG = config;
	}

	/** Creates a new item without additional configuration */
	public CBlockItem(String name, Block block, Settings settings) {
		this(name, block, settings, null);
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
	public Identifier getId() {
		return Cheese.id(this._NAME);
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
}
