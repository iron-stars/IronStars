package com.xekr.ironstars.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractPressurePlateBlock extends BasePressurePlateBlock {

    protected AbstractPressurePlateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void playOnSound(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.playSound(null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    protected void playOffSound(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.playSound(null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.5F);
    }

    protected boolean entityOnPlate(Level pLevel, BlockPos pPos) {
        return entityOnPlate(pPos, (aabb -> pLevel.getEntities(null, aabb)));
    }

    protected <T extends Entity> boolean entityOnPlate(Level pLevel, BlockPos pPos, Class<T> entityType) {
        return entityOnPlate(pPos, (aabb -> pLevel.getEntitiesOfClass(entityType, aabb)));
    }

    protected <T extends Entity> boolean entityOnPlate(Level pLevel, BlockPos pPos, @Nonnull Class<T> entityType, Predicate<T> predicate) {
        return entityOnPlate(pPos, (aabb -> pLevel.getEntitiesOfClass(entityType, aabb, predicate)));
    }

    private boolean entityOnPlate(BlockPos pPos, Function<AABB, List<? extends Entity>> getList) {
        List<? extends Entity> list = getList.apply(TOUCH_AABB.move(pPos));
        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void checkPressed(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState, boolean currentPressed, boolean nowPressed, BooleanProperty property) {
        if (currentPressed != nowPressed) {
            BlockState blockstate = pState.setValue(property, nowPressed);
            pLevel.setBlock(pPos, blockstate, 2);
            this.updateNeighbours(pLevel, pPos);
            pLevel.setBlocksDirty(pPos, pState, blockstate);
        }

        if (!nowPressed && currentPressed) {
            this.playOffSound(pLevel, pPos);
            pLevel.gameEvent(pEntity, GameEvent.BLOCK_UNPRESS, pPos);
        } else if (nowPressed && !currentPressed) {
            this.playOnSound(pLevel, pPos);
            pLevel.gameEvent(pEntity, GameEvent.BLOCK_PRESS, pPos);
        }

        if (nowPressed) {
            pLevel.scheduleTick(new BlockPos(pPos), this, this.getPressedTime());
        }
    }

}
