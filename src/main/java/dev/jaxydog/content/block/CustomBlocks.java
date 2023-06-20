package dev.jaxydog.content.block;

import dev.jaxydog.utility.register.AutoRegister;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

@AutoRegister
public class CustomBlocks {

	public static final CustomBlock CHEESE_BLOCK = new CustomBlock(
		"cheese_block",
		Settings.copy(Blocks.SPONGE).mapColor(MapColor.PALE_YELLOW).hardness(1.0f).sounds(BlockSoundGroup.SLIME)
	);

	public static final TomatoesBlock TOMATOES = new TomatoesBlock(
		Settings.copy(Blocks.BEETROOTS).breakInstantly().noCollision().sounds(BlockSoundGroup.CROP).ticksRandomly()
	);
}
