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
import dev.jaxydog.content.item.CustomItems;
import dev.jaxydog.lodestone.api.ClientLoaded;
import dev.jaxydog.lodestone.api.CommonLoaded;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TomatoesBlock
    extends CropBlock
    implements ClientLoaded, CommonLoaded
{

    public static final int TOMATOES_MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[] {
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(
        0.0,
        0.0,
        0.0,
        16.0,
        6.0,
        16.0
    ), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0), Block.createCuboidShape(
        0.0,
        0.0,
        0.0,
        16.0,
        11.0,
        16.0
    ),
    };

    public TomatoesBlock(Settings settings) {
        super(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Cheese.newId("tomatoes"))));
    }

    @Override
    public IntProperty getAgeProperty() {
        return TomatoesBlock.AGE;
    }

    @Override
    public int getMaxAge() {
        return TomatoesBlock.TOMATOES_MAX_AGE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return TomatoesBlock.AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId("tomatoes");
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return CustomItems.TOMATO;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(3) != 0) {
            super.randomTick(state, world, pos, random);
        }
    }

    @Override
    protected int getGrowthAmount(World world) {
        return super.getGrowthAmount(world) / 3;
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(TomatoesBlock.AGE);
    }

    @Override
    public void loadCommon() {
        Registry.register(Registries.BLOCK, this.getLoaderId(), this);
    }

    @Override
    public void loadClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
    }

    // spiders 🕷️ 🕸️

}
