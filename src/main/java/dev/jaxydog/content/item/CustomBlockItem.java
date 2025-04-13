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

package dev.jaxydog.content.item;

import dev.jaxydog.Cheese;
import dev.jaxydog.lodestone.api.CommonLoaded;
import dev.jaxydog.utility.LootModifier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CustomBlockItem extends BlockItem implements CommonLoaded {

    private final String path;
    private final List<LootModifier> lootModifiers = new LinkedList<>();
    private final List<Text> tooltipText = new ObjectArrayList<>();

    public CustomBlockItem(String path, Block block, Settings settings, LootModifier... lootModifiers) {
        super(
            block,
            settings.useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Cheese.newId(path)))
        );

        this.path = path;
        this.lootModifiers.addAll(List.of(lootModifiers));

        final String key = "%s.tooltip_".formatted(this.getTranslationKey());
        int index = 0;

        while (I18n.hasTranslation(key + index)) {
            this.tooltipText.add(Text.translatable(key + index).setStyle(CustomItem.STYLE));

            index += 1;
        }
    }

    @Override
    public ItemStack getDefaultStack() {
        final ItemStack stack = super.getDefaultStack();

        stack.applyComponentsFrom(ComponentMap.builder()
            .add(DataComponentTypes.LORE, new LoreComponent(this.tooltipText))
            .build());

        return stack;
    }

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId(this.path);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void loadCommon() {
        Registry.register(Registries.ITEM, this.getLoaderId(), this);
        ItemGroupEvents.modifyEntriesEvent(Registries.ITEM_GROUP.getKey(Cheese.ITEM_GROUP).get())
            .register(e -> e.add(this));

        this.lootModifiers.forEach(LootModifier::loadCommon);
    }

}
