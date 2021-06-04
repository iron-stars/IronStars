package com.xekr.ironstars.items;

import com.xekr.ironstars.CreateTab;
import net.minecraft.item.Item;

public class CommonItem extends Item {
    public CommonItem(String registryName) {
        super(new Properties().group(CreateTab.ITON_STAR));
        this.setRegistryName(registryName);
    }
}
