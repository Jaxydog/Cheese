package dev.jaxydog.utility;

import dev.jaxydog.utility.register.Registerable;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class LootModifier implements Registerable.Main {

    private final Identifier TABLE_ID;
    private final Supplier<Item> ITEM;
    private final List<LootNumberProvider> PROVIDERS = new LinkedList<>();

    public LootModifier(Identifier tableId, Supplier<Item> item, LootNumberProvider... providers) {
        this.TABLE_ID = tableId;
        this.ITEM = item;
        this.PROVIDERS.addAll(List.of(providers));
    }

    @Override
    public String getRawId() {
        throw new UnsupportedOperationException("The 'LootModifier' class does not have its own identifier");
    }

    @Override
    public void registerMain() {
        Main.super.registerMain();

        LootTableEvents.MODIFY.register((resource, loot, id, table, source) -> {
            if (!id.equals(this.TABLE_ID) || !source.isBuiltin()) return;

            for (var provider : this.PROVIDERS) {
                var entry = ItemEntry.builder(this.ITEM.get());
                var builder = LootPool.builder().rolls(provider).with(entry);

                table.pool(builder.build());
            }
        });
    }

}
