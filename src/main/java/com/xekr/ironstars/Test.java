package com.xekr.ironstars;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.WorldData;

import java.util.Map;

public class Test {
    public static void run(WorldData data) {
        for (Map.Entry<ResourceKey<LevelStem>, LevelStem> entry : data.worldGenSettings().dimensions().entrySet()) {
            LevelStem stem = entry.getValue();
            System.out.println(stem.generator());
        }

    }
}
