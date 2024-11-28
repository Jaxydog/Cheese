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
import dev.jaxydog.content.block.CustomBlocks;
import dev.jaxydog.lodestone.api.AutoLoader;
import dev.jaxydog.utility.LootModifier;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item.Settings;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

@SuppressWarnings("unused")
public final class CustomItems extends AutoLoader {

    public static final BottleItem BEEF_GRAVY = new BottleItem("beef_gravy",
        new Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.7f).build())
            .rarity(Rarity.COMMON)
    );

    public static final CustomItem CHEESE = new CustomItem("cheese",
        new Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(1.125f).snack().build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESE_AND_CRACKERS = new CustomItem("cheese_and_crackers",
        new Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(1.25f).snack().build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomBlockItem CHEESE_BLOCK = new CustomBlockItem("cheese_block",
        CustomBlocks.CHEESE_BLOCK,
        new Settings().rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESE_CLOTH = new CustomItem("cheese_cloth",
        new Settings().rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESE_CURDS = new CustomItem("cheese_curds",
        new Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.9f).snack().build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESE_FRIES = new CustomItem("cheese_fries",
        new Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.85f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESE_PIZZA = new CustomItem("cheese_pizza",
        new Settings().food(new FoodComponent.Builder().nutrition(8).saturationModifier(0.825f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESE_STEAK = new CustomItem("cheese_steak",
        new Settings().food(new FoodComponent.Builder().nutrition(15).saturationModifier(0.75f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESY_BAKED_POTATO = new CustomItem("cheesy_baked_potato",
        new Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(0.875f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CHEESY_DINO_NUGGET = new CustomItem("cheesy_dino_nugget",
        new Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.75f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem CRACKERS = new CustomItem("crackers",
        new Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).build())
            .rarity(Rarity.COMMON)
    );

    public static final CustomItem DINO_NUGGET = new CustomItem("dino_nugget",
        new Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.65f).build())
            .rarity(Rarity.COMMON)
    );

    public static final CustomItem DOUGH = new CustomItem("dough", new Settings().rarity(Rarity.COMMON));

    public static final CustomItem FRIES = new CustomItem("fries",
        new Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.65f).snack().build())
            .rarity(Rarity.COMMON)
    );

    public static final CustomItem GRILLED_CHEESE = new CustomItem("grilled_cheese",
        new Settings().food(new FoodComponent.Builder().nutrition(8).saturationModifier(0.625f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomItem HONEYED_DINO_NUGGET = new CustomItem("honeyed_dino_nugget",
        new Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.75f).build())
            .rarity(Rarity.COMMON)
    );

    public static final CustomItem MACARONI = new CustomItem("macaroni",
        new Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.5f).snack().build())
            .rarity(Rarity.COMMON)
    );

    public static final BowlItem MACARONI_AND_CHEESE = new BowlItem("macaroni_and_cheese",
        new Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(1.25f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final BowlItem POUTINE = new BowlItem("poutine",
        new Settings().food(new FoodComponent.Builder().nutrition(8).saturationModifier(0.95f).build())
            .rarity(Rarity.UNCOMMON)
    );

    public static final CustomAliasedBlockItem TOMATO = new CustomAliasedBlockItem("tomato",
        CustomBlocks.TOMATOES,
        new Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(1.25f).build())
            .rarity(Rarity.COMMON),
        new LootModifier(EntityType.ZOMBIE.getLootTableId(),
            () -> CustomItems.TOMATO,
            BinomialLootNumberProvider.create(1, 0.01f)
        ),
        new LootModifier(LootTables.VILLAGE_PLAINS_CHEST,
            () -> CustomItems.TOMATO,
            UniformLootNumberProvider.create(0, 16.0f)
        ),
        new LootModifier(LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
            () -> CustomItems.TOMATO,
            UniformLootNumberProvider.create(0, 16.0f)
        )
    );

    public static final BottleItem TOMATO_SAUCE = new BottleItem("tomato_sauce",
        new Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(1.125f).build())
            .rarity(Rarity.COMMON)
    );

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId("items");
    }

}
