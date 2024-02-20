package dev.jaxydog;

import dev.jaxydog.utility.register.AutoRegisterImpl;
import net.fabricmc.api.ClientModInitializer;

public class CheeseClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AutoRegisterImpl.runClient();
    }

}
