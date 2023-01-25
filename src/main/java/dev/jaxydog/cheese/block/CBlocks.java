package dev.jaxydog.cheese.block;

import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

/** Contains all of the mod's block definitions */
public class CBlocks {

	/** Cheese block */
	public static final CBlock CHEESE_BLOCK = new CBlock(
		"cheese_block",
		Settings.of(Material.SPONGE, MapColor.PALE_YELLOW).hardness(1.0f).sounds(BlockSoundGroup.SLIME)
	);

	/** Tomatoes crop block */
	public static final TomatoesBlock TOMATOES = new TomatoesBlock(
		Settings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.CROP).ticksRandomly()
	);

	/** Registers all of the mod's blocks at once */
	public static void register() {
		CBlocks.CHEESE_BLOCK.register();
		CBlocks.TOMATOES.register();
	}

	/** Registers all of the mod's blocks on the client at once */
	public static void clientRegister() {
		CBlocks.TOMATOES.clientRegister();
	}
}
