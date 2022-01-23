package com.xekr.ironstars.world;

import com.google.common.collect.ImmutableList;
import com.xekr.ironstars.registry.AllBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

/**
 * Applies the twilight forest biomes to the map
 *
 * @author Ben
 */
public enum GenLayerTFBiomes implements AreaTransformer0 {
	INSTANCE;
	private static final int RARE_BIOME_CHANCE = 15;

	protected static final List<ResourceKey<Biome>> commonBiomes = ImmutableList.of(Biomes.DESERT);
	protected static final List<ResourceKey<Biome>> rareBiomes = ImmutableList.of();

	private Registry<Biome> registry;

	public GenLayerTFBiomes setup(Registry<Biome> registry) {
		this.registry = registry;
		return this;
	}

	GenLayerTFBiomes() {

	}

	@Override
	public int applyPixel(Context iNoiseRandom, int x, int y) {
		//return 0; //getRandomBiome(iNoiseRandom, commonBiomes));

		if (iNoiseRandom.nextRandom(RARE_BIOME_CHANCE) == 0) {
			// make specialBiomes biome
			return getRandomBiome(iNoiseRandom, rareBiomes);
		} else {
			// make common biome
			return getRandomBiome(iNoiseRandom, commonBiomes);
		}
	}

	private int getRandomBiome(Context random, List<ResourceKey<Biome>> biomes) {
		return BiomeProvider.getBiomeId(biomes.get(random.nextRandom(biomes.size())), registry);
	}
}
