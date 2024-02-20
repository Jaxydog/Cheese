package dev.jaxydog.content.block;

import dev.jaxydog.utility.register.Registerable;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class CustomBlock extends Block implements Registerable.Main {

    private final String RAW_ID;

    public CustomBlock(String rawId, Settings settings) {
        super(settings);
        this.RAW_ID = rawId;
    }

    @Override
    public String getRawId() {
        return this.RAW_ID;
    }

    @Override
    public void registerMain() {
        Main.super.registerMain();
        Registry.register(Registries.BLOCK, this.getId(), this);
    }

}
