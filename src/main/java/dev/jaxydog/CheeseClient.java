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

package dev.jaxydog;

import dev.jaxydog.lodestone.Lodestone;
import dev.jaxydog.lodestone.api.ClientLoaded;
import net.fabricmc.api.ClientModInitializer;

public class CheeseClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Cheese.BLOCKS.register(ClientLoaded.class);
        Cheese.ITEMS.register(ClientLoaded.class);

        Lodestone.load(ClientLoaded.class, Cheese.MOD_ID);
    }

}
