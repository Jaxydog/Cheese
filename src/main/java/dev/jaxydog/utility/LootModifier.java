package dev.jaxydog.utility;

import dev.jaxydog.Cheese;
import dev.jaxydog.lodestone.api.CommonLoaded;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry.Builder;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class LootModifier implements CommonLoaded {

    private final RegistryKey<?> tableId;
    private final Supplier<Item> item;
    private final List<LootNumberProvider> providers = new LinkedList<>();

    public LootModifier(RegistryKey<?> tableId, Supplier<Item> item, LootNumberProvider... providers) {
        this.tableId = tableId;
        this.item = item;
        this.providers.addAll(List.of(providers));
    }

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId(this.tableId.getValue().getPath());
    }

    @Override
    public void loadCommon() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (!key.equals(this.tableId) || !source.isBuiltin()) return;

            for (final LootNumberProvider provider : this.providers) {
                final Builder<? extends Builder<?>> entry = ItemEntry.builder(this.item.get());
                final LootPool.Builder builder = LootPool.builder().rolls(provider).with(entry);

                tableBuilder.pool(builder);
            }
        });
    }

}
