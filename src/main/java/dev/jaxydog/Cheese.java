package dev.jaxydog;

import dev.jaxydog.content.item.CustomItems;
import dev.jaxydog.utility.register.AutoRegisterImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cheese implements ModInitializer {

	public static final String MOD_ID = "cheese";
	public static final Logger LOGGER = LoggerFactory.getLogger(Cheese.MOD_ID);
	public static final ItemGroup ITEM_GROUP = FabricItemGroup
		.builder()
		.icon(() -> CustomItems.CHEESE.getDefaultStack())
		.displayName(Text.translatable(Cheese.newId("default").toTranslationKey("itemGroup")))
		.build();

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM_GROUP, Cheese.newId("default"), Cheese.ITEM_GROUP);

		AutoRegisterImpl.runMain();

		Cheese.LOGGER.info("Cheese has loaded :)");
	}

	public static final Identifier newId(String path) {
		return Identifier.of(Cheese.MOD_ID, path);
	}
}
