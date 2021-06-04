package com.xekr.ironstars;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreateTab extends ItemGroup {
    public static final ItemGroup ITON_STAR = new CreateTab();
    public CreateTab() {
        super(IronStars.ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(AllItems.chip);
    }
}
