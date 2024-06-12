package dev.jaxydog;

import dev.jaxydog.lodestone.Lodestone;
import dev.jaxydog.lodestone.api.ClientLoaded;
import net.fabricmc.api.ClientModInitializer;

public class CheeseClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Cheese.BLOCKS.register(ClientLoaded.class);
        Cheese.ITEMS.register(ClientLoaded.class);

        Lodestone.load(ClientLoaded.class, Cheese.MOD_ID);
    }

}
