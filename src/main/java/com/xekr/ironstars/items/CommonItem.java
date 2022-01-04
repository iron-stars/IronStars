package com.xekr.ironstars.items;

import com.xekr.ironstars.CreateTab;
import net.minecraft.world.item.Item;

public class CommonItem extends Item {
    public CommonItem(String registryName) {
        super(new Properties().tab(CreateTab.MAIN));
        this.setRegistryName(registryName);
    }
}
