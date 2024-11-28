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
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CustomItem extends Item implements CommonLoaded {

    private final String path;
    private final List<LootModifier> lootModifiers = new LinkedList<>();

    public CustomItem(String path, Settings settings, LootModifier... lootModifiers) {
        super(settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, Cheese.newId(path))));
        this.path = path;
        this.lootModifiers.addAll(List.of(lootModifiers));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        var key = stack.getItem().getTranslationKey() + ".tooltip_";
        var index = 0;

        while (I18n.hasTranslation(key + index)) {
            tooltip.add(Text.translatable((key + index).formatted(Formatting.GRAY)));
            index += 1;
        }

        super.appendTooltip(stack, context, tooltip, type);
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
