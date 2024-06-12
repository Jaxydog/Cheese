package dev.jaxydog;

import dev.jaxydog.content.block.CustomBlocks;
import dev.jaxydog.content.item.CustomItems;
import dev.jaxydog.lodestone.Lodestone;
import dev.jaxydog.lodestone.api.CommonLoaded;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final CustomBlocks BLOCKS = new CustomBlocks();
    public static final CustomItems ITEMS = new CustomItems();
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
        .icon(CustomItems.CHEESE::getDefaultStack)
        .displayName(Text.translatable(newId("default").toTranslationKey("itemGroup")))
        .build();

    public static Identifier newId(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, newId("default"), ITEM_GROUP);

        BLOCKS.register(CommonLoaded.class);
        ITEMS.register(CommonLoaded.class);

        Lodestone.load(CommonLoaded.class, MOD_ID);

        Cheese.LOGGER.info("Cheese has loaded! Thanks for playing :3");
    }

}
