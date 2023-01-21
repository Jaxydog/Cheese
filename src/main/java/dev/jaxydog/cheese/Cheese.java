package dev.jaxydog.cheese;

import dev.jaxydog.cheese.block.CBlocks;
import dev.jaxydog.cheese.item.CItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Main mod entrypoint */
public class Cheese implements ModInitializer {

	/** Mod identifier, used as the identifier namespace */
	public static final String MOD_ID = "cheese";
	/** Mod logger */
	public static final Logger LOGGER = LoggerFactory.getLogger(Cheese.MOD_ID);

	@Override
	public void onInitialize() {
		CBlocks.register();
		CItems.register();

		Cheese.LOGGER.info("Cheese has loaded :)");
	}

	/** Creates a new identifier using the mod's namespace and the given path */
	public static Identifier id(String path) {
		return new Identifier(Cheese.MOD_ID, path);
	}
}
