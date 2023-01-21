package dev.jaxydog.cheese.block;

import dev.jaxydog.cheese.Cheese;
import dev.jaxydog.cheese.util.Registerable;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/** Block wrapper class to provide for additional mod functionality */
public class CBlock extends Block implements Registerable {

	/** The block's name, used as the path in the block identifier */
	protected final String _NAME;

	/** Creates a new block */
	public CBlock(String name, Settings settings) {
		super(settings);
		this._NAME = name;
	}

	@Override
	public Identifier getId() {
		return Cheese.id(this._NAME);
	}

	@Override
	public void register() {
		Registry.register(Registries.BLOCK, this.getId(), this);
	}
}
