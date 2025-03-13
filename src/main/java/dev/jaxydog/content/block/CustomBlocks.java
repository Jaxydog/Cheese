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

package dev.jaxydog.content.block;

import dev.jaxydog.Cheese;
import dev.jaxydog.lodestone.api.AutoLoader;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class CustomBlocks
    extends AutoLoader
{

    public static final CustomBlock CHEESE_BLOCK = new CustomBlock(
        "cheese_block",
        Settings.copy(Blocks.SPONGE).mapColor(MapColor.PALE_YELLOW).hardness(1.0f).sounds(BlockSoundGroup.SLIME)
    );

    public static final TomatoesBlock TOMATOES = new TomatoesBlock(Settings
        .copy(Blocks.POTATOES)
        .breakInstantly()
        .noCollision()
        .sounds(BlockSoundGroup.CROP)
        .ticksRandomly());

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId("blocks");
    }

}
