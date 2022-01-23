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
import com.xekr.ironstars.registry.AllBlocks;
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
import net.minecraft.world.level.levelgen.VerticalAnchor;
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
    protected <T> T getOrCreateInRegistry(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation pLocation, Supplier<T> resourceCreator) {
        Registry<T> registry = this.registryAccess.registryOrThrow(registryKey);
        ResourceKey<T> resourceKey1 = ResourceKey.create(registryKey, pLocation);
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
                        0,
                        256,
                        new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D),
                        new NoiseSlider(-10, 3, 0),
                        new NoiseSlider(15, 3, 0),
                        1,
                        1,
                        false,
                        false,
                        false,
                        new TerrainShaper(CubicSpline.constant(0.0F), CubicSpline.constant(0.0F), CubicSpline.constant(0.0F))
                ),
                AllBlocks.MOON_ROCK.get().defaultBlockState(),
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

        this.getOrCreateInRegistry(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, IronStars.id("moon_noise_config"), () -> dimensionSettings);


        NoiseBasedChunkGenerator chunkGen = new NoiseBasedChunkGenerator(RegistryAccess.builtin().registryOrThrow(Registry.NOISE_REGISTRY), new BiomeProvider(0L, new MappedRegistry<>(Registry.BIOME_REGISTRY, Lifecycle.experimental())), 0L, () -> dimensionSettings);

        final DimensionType dimensionType = DimensionType.create(
                OptionalLong.empty(),
                true,
                false,
                false,
                false,
                1.0D,
                false,
                true,
                false,
                true,
                false,
                0,
                256,
                256,
                new ResourceLocation("infiniburn_overworld"),
                DimensionType.END_EFFECTS,
                0f
        );

        this.getOrCreateInRegistry(Registry.DIMENSION_TYPE_REGISTRY, IronStars.id("moon_type"), () -> dimensionType);

        return ImmutableMap.of(
                IronStars.id("moon"), new LevelStem(() -> dimensionType, new ModChunkGenerator(chunkGen, true))
        );
    }

    public static SurfaceRules.RuleSource tfSurface() {
        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())));
        SurfaceRules.RuleSource surfacerules$rulesource9 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), SurfaceRules.state(AllBlocks.MOON_SOIL.get().defaultBlockState()));
        builder.add(surfacerules$rulesource9);
        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}
