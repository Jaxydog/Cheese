package dev.jaxydog.cheese.block;

import dev.jaxydog.cheese.Cheese;
import dev.jaxydog.cheese.item.CItems;
import dev.jaxydog.cheese.util.ClientRegisterable;
import dev.jaxydog.cheese.util.Registerable;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

public class TomatoesBlock extends CropBlock implements Registerable, ClientRegisterable {

	/** The maximum age of the tomato crop */
	public static final int TOMATOES_MAX_AGE = 3;
	/** The tomato crop's age property */
	public static final IntProperty AGE = Properties.AGE_3;
	/** The tomato crop's voxel shape */
	private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[] {
		Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
		Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
		Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
		Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
	};

	/** Creates a new tomato crop block */
	public TomatoesBlock(Settings settings) {
		super(settings);
	}

	@Override
	public IntProperty getAgeProperty() {
		return TomatoesBlock.AGE;
	}

	@Override
	public Identifier getId() {
		return Cheese.id("tomatoes");
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
	protected ItemConvertible getSeedsItem() {
		return CItems.TOMATO;
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
	public void register() {
		Registry.register(Registries.BLOCK, this.getId(), this);
	}

	@Override
	public void clientRegister() {
		BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
	}
}
