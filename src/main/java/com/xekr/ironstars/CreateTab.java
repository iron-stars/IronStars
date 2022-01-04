package com.xekr.ironstars;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreateTab extends CreativeModeTab {
    public static CreateTab MAIN;
    public CreateTab(String name) {
        super(IronStars.ID+":"+name);
        MAIN = this;
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(AllItems.chip);
    }
}
