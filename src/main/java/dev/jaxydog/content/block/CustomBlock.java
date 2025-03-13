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
import dev.jaxydog.lodestone.api.CommonLoaded;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class CustomBlock
    extends Block
    implements CommonLoaded
{

    private final String path;

    public CustomBlock(String path, Settings settings) {
        super(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Cheese.newId(path))));
        this.path = path;
    }

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId(this.path);
    }

    @Override
    public void loadCommon() {
        Registry.register(Registries.BLOCK, this.getLoaderId(), this);
    }

}
