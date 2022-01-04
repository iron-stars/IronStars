package com.xekr.ironstars;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreateTab extends CreativeModeTab {
    public static CreateTab MAIN;
    public CreateTab(String name) {
        super(IronStars.ID+":"+name);
        MAIN = this;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(AllItems.chip);
    }
}
