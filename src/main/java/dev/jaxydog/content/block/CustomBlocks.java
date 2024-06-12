package dev.jaxydog.content.block;

import dev.jaxydog.Cheese;
import dev.jaxydog.lodestone.api.AutoLoader;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class CustomBlocks extends AutoLoader {

    public static final CustomBlock CHEESE_BLOCK = new CustomBlock(
        "cheese_block",
        Settings.copy(Blocks.SPONGE).mapColor(MapColor.PALE_YELLOW).hardness(1.0f).sounds(BlockSoundGroup.SLIME)
    );

    public static final TomatoesBlock TOMATOES = new TomatoesBlock(Settings.copy(Blocks.POTATOES)
        .breakInstantly()
        .noCollision()
        .sounds(BlockSoundGroup.CROP)
        .ticksRandomly());

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId("blocks");
    }

}
