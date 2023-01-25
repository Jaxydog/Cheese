package dev.jaxydog.cheese.util;

import java.util.LinkedList;
import java.util.List;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.util.Identifier;

/** Allows an item to modify an existing loot table */
public class LootModifier implements Registerable.With<ItemConvertible> {

	/** The identifier of the loot table to modify */
	private final Identifier __TABLE_ID;
	/** A list of loot number providers */
	private List<LootNumberProvider> __providers = new LinkedList<>();

	/** Creates a new loot modifier */
	public LootModifier(Identifier tableId, LootNumberProvider... providers) {
		this.__TABLE_ID = tableId;
		this.__providers.addAll(List.of(providers));
	}

	@Override
	public Identifier getId() {
		return this.__TABLE_ID;
	}

	@Override
	public void register(ItemConvertible item) {
		LootTableEvents.MODIFY.register((resource, loot, id, table, source) -> {
			if (!source.isBuiltin() || !id.equals(this.getId())) {
				return;
			}

			for (var provider : this.getProviders()) {
				var entry = ItemEntry.builder(item);
				var builder = LootPool.builder().rolls(provider).with(entry);

				table.pool(builder.build());
			}
		});
	}

	/** Returns the number of rolls for the drop */
	public List<LootNumberProvider> getProviders() {
		return this.__providers;
	}
}
