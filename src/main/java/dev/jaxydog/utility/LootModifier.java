/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * Copyright © 2024 Jaxydog
 *
 * This file is part of Cheese.
 *
 * Cheese is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Cheese is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with Cheese. If not, see <https://www.gnu.org/licenses/>.
 */

package dev.jaxydog.utility;

import dev.jaxydog.Cheese;
import dev.jaxydog.lodestone.api.CommonLoaded;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
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
        LootTableEvents.MODIFY.register((key, tableBuilder, source, lookup) -> {
            if (!key.equals(this.tableId) || !source.isBuiltin()) return;

            for (final LootNumberProvider provider : this.providers) {
                final Builder<? extends Builder<?>> entry = ItemEntry.builder(this.item.get());
                final LootPool.Builder builder = LootPool.builder().rolls(provider).with(entry);

                tableBuilder.pool(builder);
            }
        });
    }

}
