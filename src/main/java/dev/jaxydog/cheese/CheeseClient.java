package dev.jaxydog.cheese;

import dev.jaxydog.cheese.block.CBlocks;
import net.fabricmc.api.ClientModInitializer;

public class CheeseClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		CBlocks.clientRegister();
	}
}
