package dev.jaxydog.content.block;

import dev.jaxydog.Cheese;
import dev.jaxydog.lodestone.api.CommonLoaded;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CustomBlock extends Block implements CommonLoaded {

    private final String path;

    public CustomBlock(String path, Settings settings) {
        super(settings);
        this.path = path;
    }

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId(this.path);
    }

    @Override
    public void loadCommon() {
        Registry.register(Registries.BLOCK, this.getLoaderId(), this);
    }

}
