package com.xekr.ironstars;

import net.minecraft.world.item.ItemStack;

public class CreativeModeTab extends net.minecraft.world.item.CreativeModeTab {
    public static final net.minecraft.world.item.CreativeModeTab IRON_STAR = new CreativeModeTab();
    public CreativeModeTab() {
        super(IronStars.ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(AllItems.chip);
    }
}