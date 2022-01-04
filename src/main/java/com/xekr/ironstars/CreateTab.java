package com.xekr.ironstars;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreateTab extends CreativeModeTab {
    public static final CreativeModeTab IRON_STAR = new CreateTab();
    public CreateTab() {
        super(IronStars.ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(AllItems.chip);
    }
}