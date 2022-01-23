package com.xekr.ironstars;

import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import com.xekr.ironstars.registry.AllDimensions;
import net.minecraft.core.MappedRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.Optional;

@SuppressWarnings("JavadocReference")
public class ASMHooks {

    /**
     * 注入点:
     * {@linkplain net.minecraft.world.level.levelgen.WorldGenSettings#WorldGenSettings(long, boolean, boolean, MappedRegistry, Optional) WorldGenSettings}<br>
     * 在构造函数设置seed值前
     */
    public static long seed(long seed) {
        AllDimensions.seed = seed;
        return seed;
    }

    /**
     * 注入点:
     * {@linkplain net.minecraft.world.level.storage.LevelStorageSource#readWorldGenSettings(Dynamic, DataFixer, int) readWorldGenSettings}<br>
     * 在构造函数设置seed值前
     */
    public static Dynamic<Tag> seed(Dynamic<Tag> seed) {
        AllDimensions.seed = ((CompoundTag) seed.getValue()).getLong("seed");
        return seed;
    }

}
