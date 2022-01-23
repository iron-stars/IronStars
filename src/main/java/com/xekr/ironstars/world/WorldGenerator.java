package com.xekr.ironstars.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.IronStarsUtil;
import com.xekr.ironstars.registry.AllBiomes;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.RegistryWriteOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlider;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.Supplier;

public class WorldGenerator extends RegistryWriteOps<JsonElement> implements DataProvider {
    private final DataGenerator generator;

    private HashCache cache;
    private final HashSet<Object> SerializeCache = new HashSet<>();

    public WorldGenerator(DataGenerator generator) {
        super(JsonOps.INSTANCE, new RegistryAccess.RegistryHolder());
        this.generator = generator;
    }

    private void generate() {
        // TODO: 雕刻器还没写
//        ConfiguredWorldCarvers.registerConfigurations(this.registryAccess.registryOrThrow(Registry.CONFIGURED_CARVER_REGISTRY));

        Map<ResourceLocation, Biome> biomes = this.getBiomes();
        biomes.forEach((rl, biome) -> this.registryAccess.registry(Registry.BIOME_REGISTRY).ifPresent(reg -> Registry.register(reg, rl, biome)));
        biomes.forEach((rl, biome) -> this.serialize(Registry.BIOME_REGISTRY, rl, biome, Biome.DIRECT_CODEC));

        this.getDimensions().forEach((rl, dimension) -> this.serialize(Registry.LEVEL_STEM_REGISTRY, rl, dimension, LevelStem.CODEC));
    }

    public <T> void serialize(ResourceKey<? extends Registry<T>> resourceType, ResourceLocation resourceLocation, T resource, Encoder<T> encoder) {
        if (SerializeCache.contains(resource)) {
            IronStars.LOGGER.debug("Please do not serialize '" + resourceLocation + "' repeatedly!");
            return;
        }
        SerializeCache.add(resource);
        Optional<JsonElement> output = this.withEncoder(encoder).apply(resource).setLifecycle(Lifecycle.experimental())
                .resultOrPartial(error -> IronStars.LOGGER.error("Not serialized object " + resourceLocation + " with " + error));
        if (output.isPresent()) {
            try {
                Path path = this.generator.getOutputFolder().resolve("data").resolve(resourceLocation.getNamespace())
                        .resolve(resourceType.location().getPath()).resolve(resourceLocation.getPath() + ".json");
                save(resourceType, cache, output.get(), path);
            } catch (IOException e) {
                IronStars.LOGGER.error("Could not save '" + resourceLocation + "' (Resource Type '" + resourceType.location() + "')", e);
            }
        }
    }

    private void save(ResourceKey<?> key, HashCache cache, JsonElement dynamic, Path pathIn) throws IOException {
        if (key == Registry.LEVEL_STEM_REGISTRY) dynamic.getAsJsonObject().get("generator").getAsJsonObject()
                .get("wrapped_generator").getAsJsonObject().get("biome_source").getAsJsonObject().remove("seed");
        String jsonString = IronStarsUtil.GSON.toJson(dynamic);
        String result = SHA1.hashUnencodedChars(jsonString).toString();
        if (!Objects.equals(cache.getHash(pathIn), result) || !Files.exists(pathIn)) {
            Files.createDirectories(pathIn.getParent());
            try (BufferedWriter bufferedwriter = Files.newBufferedWriter(pathIn)) {
                bufferedwriter.write(jsonString);
            }
        }
        cache.putNew(pathIn, result);
    }

    @SuppressWarnings({"rawtypes"})
    protected static <E, T extends Registry<E>> Optional<ResourceLocation> getLocationFromRegistry(Registry registry, ResourceKey<T> key, E element) {
        T t = getRegistry(registry, key);
        return t != null ? getLocationFromRegistry(t, element) : Optional.empty();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static <T> T getRegistry(Registry registry, ResourceKey<T> key) {
        return (T) registry.get(key);
    }

    protected static <E, T extends Registry<E>> Optional<ResourceLocation> getLocationFromRegistry(T registry, E element) {
        return registry.getResourceKey(element).map(ResourceKey::location);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected <E> DataResult<JsonElement> encode(E pElement, JsonElement pPrefix, ResourceKey<? extends Registry<E>> pRegistryKey, Codec<E> pElementCodec) {
        Optional<ResourceLocation> key = Optional.empty();
        if (pElement instanceof IForgeRegistryEntry)
            key = Optional.ofNullable(((IForgeRegistryEntry<?>) pElement).getRegistryName());
        if (key.isEmpty()) {
            try {
                key = getLocationFromRegistry(this.registryAccess.registryOrThrow(pRegistryKey), pElement);
            } catch (Throwable ignored) {}
        }
        if (key.isEmpty()) key = getLocationFromRegistry(BuiltinRegistries.REGISTRY, pRegistryKey, pElement);
        if (key.isEmpty()) key = getLocationFromRegistry(Registry.REGISTRY, pRegistryKey, pElement);
        if (key.isEmpty() && pElement instanceof IForgeRegistryEntry entry) {
            ResourceLocation location = entry.getRegistryName();
            if (location == null) {
                IForgeRegistry forgeRegistry = RegistryManager.ACTIVE.getRegistry(pRegistryKey.location());
                key = Optional.ofNullable(forgeRegistry.getKey(entry));
            }else key = Optional.of(location);
        }
        if (key.isPresent()) {
            ResourceLocation location = key.get();
            if (IronStars.ID.equals(location.getNamespace()))
                this.serialize(pRegistryKey, location, pElement, pElementCodec);
            return ResourceLocation.CODEC.encode(key.get(), this.delegate, pPrefix);
        }
        return pElementCodec.encode(pElement, this, pPrefix);
    }

    @Override
    public void run(HashCache pCache) {
        this.cache = pCache;
        generate();
    }

    @Override
    public String getName() {
        return "Moon World";
    }

    @SuppressWarnings("UnusedReturnValue")
    protected <T> T getOrCreateInRegistry(ResourceKey<? extends Registry<? extends T>> registryKey, ResourceKey<? extends Registry<T>> resourceKey, ResourceLocation pLocation, Supplier<T> resourceCreator) {
        Registry<T> registry = this.registryAccess.registryOrThrow(registryKey);
        ResourceKey<T> resourceKey1 = ResourceKey.create(resourceKey, pLocation);
        T resourceSaved = getRegistry(registry, resourceKey1);
        return resourceSaved != null ? resourceSaved : Registry.register(registry, resourceKey1.location(), resourceCreator.get());
    }

    /*
     * NoiseGeneratorSettings
     * structureSettings    StructureSettings
     * noiseSettings        NoiseSettings
     * defaultBlock         BlockState
     * defaultFluid         BlockState
     * rules                SurfaceRules.RuleSource
     * seaLevel             int
     * disableMobGeneration boolean
     * aquifersEnabled      boolean
     * noiseCavesEnabled    boolean
     * oreVeinsEnabled      boolean
     * noodleCavesEnabled   boolean
    */
    private Map<ResourceLocation, LevelStem> getDimensions() {
        NoiseGeneratorSettings dimensionSettings = new NoiseGeneratorSettings(
                new StructureSettings(Optional.empty(), ImmutableMap.of()),
                NoiseSettings.create(
                        -32,
                        256,
                        new NoiseSamplingSettings(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D),
                        new NoiseSlider(-10, 3, 0),
                        new NoiseSlider(15, 3, 0),
                        1,
                        2,
                        true,
                        true,
                        false,
                        new TerrainShaper(CubicSpline.constant(0.0F), CubicSpline.constant(0.0F), CubicSpline.constant(0.0F))
                ),
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                tfSurface(),
                0,
                false,
                false,
                false,
                false,
                false,
                false
        );

        this.getOrCreateInRegistry(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, IronStars.id("moon_noise_config"), () -> dimensionSettings);


        NoiseBasedChunkGenerator chunkGen = new NoiseBasedChunkGenerator(RegistryAccess.builtin().registryOrThrow(Registry.NOISE_REGISTRY), new BiomeProvider(0L, new MappedRegistry<>(Registry.BIOME_REGISTRY, Lifecycle.experimental())), 0L, () -> dimensionSettings);

        final DimensionType dimensionType = DimensionType.create(
                OptionalLong.of(13000L),
                true,
                false,
                false,
                true,
                0.125D,
                false,
                false,
                true,
                true,
                false,
                -32,
                32+256,
                32+256,
                new ResourceLocation("infiniburn_overworld"),
                IronStars.id("renderer"),
                0f
        );

        this.getOrCreateInRegistry(Registry.DIMENSION_TYPE_REGISTRY, Registry.DIMENSION_TYPE_REGISTRY, IronStars.id("moon_type"), () -> dimensionType);

        return ImmutableMap.of(
                IronStars.id("twilightforest"), new LevelStem(() -> dimensionType, new ModChunkGenerator(chunkGen, true))
        );
    }

    public static SurfaceRules.RuleSource tfSurface() {

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }

    private Map<ResourceLocation, Biome> getBiomes() {

        BiomeSpecialEffects.Builder biomeAmbience = new BiomeSpecialEffects.Builder()
                .fogColor(0xC0FFD8)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .skyColor(0x20224A)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);

        MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();

        spawnInfo.creatureGenerationProbability(0.1f);

        spawnInfo.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnInfo.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        spawnInfo.addSpawn(MobCategory. MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 10, 4, 4));
        spawnInfo.addSpawn(MobCategory. MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 10, 4, 4));
        spawnInfo.addSpawn(MobCategory. MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 10, 4, 4));
        spawnInfo.addSpawn(MobCategory. MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 1, 4, 4));
        spawnInfo.addSpawn(MobCategory. MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 10, 4, 4));
        spawnInfo.addSpawn(MobCategory. MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 1, 4));

        spawnInfo.addSpawn(MobCategory. MONSTER, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 1, 2));

        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder();
        Biome biome = new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(biomeAmbience.build())
                .mobSpawnSettings(spawnInfo.build())
                .generationSettings(biomeGenerationSettings.build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build();

        ImmutableMap.Builder<ResourceLocation, Biome> builder = ImmutableMap.builder();
        return builder.put(AllBiomes.MOON.location(), biome).build();
    }

}
