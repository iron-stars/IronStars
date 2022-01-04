package com.xekr.ironstars.items;

import com.xekr.ironstars.CreativeModeTab;
import net.minecraft.world.item.Item;

public class CommonItem extends Item {
    public CommonItem(String registryName) {
        super(new Properties().tab(CreativeModeTab.IRON_STAR));
        this.setRegistryName(registryName);
    }
}
