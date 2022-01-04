package com.xekr.ironstars;

import com.xekr.ironstars.items.CommonItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class AllItems {
    //Items
    public static final CommonItem chip = new CommonItem("chip");
    public static final CommonItem circuit_board = new CommonItem("circuit_board");
    public static final CommonItem copper_wire = new CommonItem("copper_wire");
    public static final CommonItem hardened_resin = new CommonItem("hardened_resin");
    public static final CommonItem magnet = new CommonItem("magnet");
    public static final CommonItem resin = new CommonItem("resin");
    public static final CommonItem silicon = new CommonItem("silicon");
    public static final CommonItem steel_ingot = new CommonItem("steel_ingot");
    public static final CommonItem sticky_resin = new CommonItem("sticky_resin");
    public static final CommonItem titanium_alloy_ingot = new CommonItem("titanium_alloy_ingot");
    public static final CommonItem titanium_ingot = new CommonItem("titanium_ingot");

    //BlockItems
    public static final BlockItem coil = new BlockItem(AllBlocks.coil,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem electric_wire = new BlockItem(AllBlocks.electricWire,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem magnet_block = new BlockItem(AllBlocks.magnetBlock,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem moon_dust = new BlockItem(AllBlocks.moonDust,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem moon_rock = new BlockItem(AllBlocks.moonRock,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem moon_soil = new BlockItem(AllBlocks.moonSoil,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem motor = new BlockItem(AllBlocks.motor,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem motor_lay = new BlockItem(AllBlocks.motorLay,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem silicon_block = new BlockItem(AllBlocks.siliconBlock,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem steel_block = new BlockItem(AllBlocks.steelBlock,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem steel_tiles = new BlockItem(AllBlocks.steelTiles,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem titanium_alloy_block = new BlockItem(AllBlocks.titaniumAlloyBlock,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem titanium_block = new BlockItem(AllBlocks.titaniumBlock,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem titanium_ore = new BlockItem(AllBlocks.titaniumOre,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem turbine = new BlockItem(AllBlocks.turbine,new Item.Properties().tab(CreateTab.MAIN));
    public static final BlockItem windmill = new BlockItem(AllBlocks.windmill,new Item.Properties().tab(CreateTab.MAIN));

}
