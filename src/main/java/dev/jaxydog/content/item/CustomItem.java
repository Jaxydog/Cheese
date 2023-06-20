package dev.jaxydog.content.item;

import dev.jaxydog.Cheese;
import dev.jaxydog.utility.LootModifier;
import dev.jaxydog.utility.register.Registerable;
import java.util.LinkedList;
import java.util.List;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class CustomItem extends Item implements Registerable.Main {

	private final String RAW_ID;
	private final List<LootModifier> LOOT_MODIFIERS = new LinkedList<>();

	public CustomItem(String rawId, Settings settings, LootModifier... lootModifiers) {
		super(settings);
		this.RAW_ID = rawId;
		this.LOOT_MODIFIERS.addAll(List.of(lootModifiers));
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		var key = stack.getItem().getTranslationKey(stack) + ".tooltip_";
		var index = 0;

		while (I18n.hasTranslation(key + index)) {
			tooltip.add(Text.translatable((key + index).formatted(Formatting.GRAY)));
			index += 1;
		}

		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public String getRawId() {
		return this.RAW_ID;
	}

	@Override
	public void registerMain() {
		Main.super.registerMain();
		Registry.register(Registries.ITEM, this.getId(), this);
		ItemGroupEvents
			.modifyEntriesEvent(Registries.ITEM_GROUP.getKey(Cheese.ITEM_GROUP).get())
			.register(e -> e.add(this));

		this.LOOT_MODIFIERS.forEach(LootModifier::registerMain);
	}
}
