package com.xekr.ironstars.world;

import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

public interface Context {
	int nextRandom(int p_76516_);

	ImprovedNoise getBiomeNoise();
}