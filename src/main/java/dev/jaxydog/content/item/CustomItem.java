package dev.jaxydog.content.item;

import dev.jaxydog.Cheese;
import dev.jaxydog.lodestone.api.CommonLoaded;
import dev.jaxydog.utility.LootModifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipType;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CustomItem extends Item implements CommonLoaded {

    private final String path;
    private final List<LootModifier> lootModifiers = new LinkedList<>();

    public CustomItem(String path, Settings settings, LootModifier... lootModifiers) {
        super(settings);
        this.path = path;
        this.lootModifiers.addAll(List.of(lootModifiers));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        var key = stack.getItem().getTranslationKey(stack) + ".tooltip_";
        var index = 0;

        while (I18n.hasTranslation(key + index)) {
            tooltip.add(Text.translatable((key + index).formatted(Formatting.GRAY)));
            index += 1;
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public Identifier getLoaderId() {
        return Cheese.newId(this.path);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void loadCommon() {
        Registry.register(Registries.ITEM, this.getLoaderId(), this);
        ItemGroupEvents.modifyEntriesEvent(Registries.ITEM_GROUP.getKey(Cheese.ITEM_GROUP).get())
            .register(e -> e.add(this));

        this.lootModifiers.forEach(LootModifier::loadCommon);
    }

}
