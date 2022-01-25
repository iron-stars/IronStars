package com.xekr.ironstars.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xekr.ironstars.registry.AllDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MoonChunkGenerator extends ChunkGenerator {
    public static final Codec<MoonChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ChunkGenerator.CODEC.fieldOf("wrapped_generator").forGetter(o -> o.delegate)
    ).apply(instance, instance.stable(MoonChunkGenerator::new)));

    public final ChunkGenerator delegate;

    public MoonChunkGenerator(ChunkGenerator delegate, boolean owSeed) {
        this(owSeed ? delegate.withSeed(AllDimensions.seed) : delegate);
    }

    private MoonChunkGenerator(ChunkGenerator delegate) {
        super(delegate.getBiomeSource(), delegate.getBiomeSource(), delegate.getSettings(), delegate instanceof NoiseBasedChunkGenerator noiseGen ? noiseGen.seed : delegate.strongholdSeed);
        this.delegate = delegate;
    }

    @Override
    public void createStructures(RegistryAccess pRegistryAccess, StructureFeatureManager pStructureFeatureManager, ChunkAccess pChunk, StructureManager pStructureManager, long pSeed) {
        this.delegate.createStructures(pRegistryAccess, pStructureFeatureManager, pChunk, pStructureManager, pSeed);
    }

    @Override
    public void createReferences(WorldGenLevel pLevel, StructureFeatureManager pStructureFeatureManager, ChunkAccess pChunk) {
        this.delegate.createReferences(pLevel, pStructureFeatureManager, pChunk);
    }

    @Override
    public int getFirstFreeHeight(int pX, int pZ, Heightmap.Types pType, LevelHeightAccessor pLevel) {
        return this.delegate.getFirstFreeHeight(pX, pZ, pType, pLevel);
    }

    @Override
    public int getFirstOccupiedHeight(int pX, int pZ, Heightmap.Types pType, LevelHeightAccessor pLevel) {
        return this.delegate.getFirstOccupiedHeight(pX, pZ, pType, pLevel);
    }

    @Override
    public boolean hasStronghold(ChunkPos pPos) {
        return this.delegate.hasStronghold(pPos);
    }

    @Override
    public WeightedRandomList<MobSpawnSettings.SpawnerData> getMobsAt(Biome pBiome, StructureFeatureManager pStructureFeatureManager, MobCategory pCategory, BlockPos pPos) {
        return this.delegate.getMobsAt(pBiome, pStructureFeatureManager, pCategory, pPos);
    }

    @Override
    public BiomeSource getBiomeSource() {
        return this.delegate.getBiomeSource();
    }

    @Override
    public int getSpawnHeight(LevelHeightAccessor pLevel) {
        return this.delegate.getSpawnHeight(pLevel);
    }

    @Override
    public StructureSettings getSettings() {
        return this.delegate.getSettings();
    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel pLevel, ChunkAccess pChunk, StructureFeatureManager pStructureFeatureManager) {
        this.delegate.applyBiomeDecoration(pLevel, pChunk, pStructureFeatureManager);
    }

    @Nullable
    @Override
    public BlockPos findNearestMapFeature(ServerLevel pLevel, StructureFeature<?> pStructure, BlockPos pPos, int pSearchRadius, boolean pSkipKnownStructures) {
        return this.delegate.findNearestMapFeature(pLevel, pStructure, pPos, pSearchRadius, pSkipKnownStructures);
    }

    @Override
    public CompletableFuture<ChunkAccess> createBiomes(Registry<Biome> p_196743_, Executor p_196744_, Blender p_196745_, StructureFeatureManager p_196746_, ChunkAccess p_196747_) {
        return this.delegate.createBiomes(p_196743_, p_196744_, p_196745_, p_196746_, p_196747_);
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long pSeed) {
        return new MoonChunkGenerator(this.delegate.withSeed(pSeed));
    }

    @Override
    public Climate.Sampler climateSampler() {
        return this.delegate.climateSampler();
    }

    @Override
    public void applyCarvers(WorldGenRegion pLevel, long pSeed, BiomeManager pBiomeManager, StructureFeatureManager pStructureFeatureManager, ChunkAccess pChunk, GenerationStep.Carving pStep) {
        this.delegate.applyCarvers(pLevel, pSeed, pBiomeManager, pStructureFeatureManager, pChunk, pStep);
    }

    @Override
    public void buildSurface(WorldGenRegion pLevel, StructureFeatureManager pStructureFeatureManager, ChunkAccess pChunk) {
        this.delegate.buildSurface(pLevel, pStructureFeatureManager, pChunk);
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion pLevel) {
        this.delegate.spawnOriginalMobs(pLevel);
    }

    @Override
    public int getGenDepth() {
        return this.delegate.getGenDepth();
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor p_187748_, Blender p_187749_, StructureFeatureManager p_187750_, ChunkAccess p_187751_) {
        return this.delegate.fillFromNoise(p_187748_, p_187749_, p_187750_, p_187751_);
    }

    @Override
    public int getSeaLevel() {
        return this.delegate.getSeaLevel();
    }

    @Override
    public int getMinY() {
        return this.delegate.getMinY();
    }

    @Override
    public int getBaseHeight(int pX, int pZ, Heightmap.Types pType, LevelHeightAccessor pLevel) {
        return this.delegate.getBaseHeight(pX, pZ, pType, pLevel);
    }

    @Override
    public NoiseColumn getBaseColumn(int pX, int pZ, LevelHeightAccessor pLevel) {
        return this.delegate.getBaseColumn(pX, pZ, pLevel);
    }
}
