package com.xekr.ironstars.efficiency;

import com.xekr.ironstars.IronStarsUtil;
import com.xekr.ironstars.blocks.entity.AbstractEFFBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class EFFNetwork {
    private static final Map<UUID, EFFNetwork> NETWORK = new HashMap<>();

    private final UUID id;
    private int genEfficiency;
    private int restEfficiency;
    private final Map<BlockPos, Integer> sources = new HashMap<>();
    private final Map<BlockPos, Integer> members = new LinkedHashMap<>();

    private EFFNetwork(BlockPos pos, int efficiency) {
        this.id = Mth.createInsecureUUID(IronStarsUtil.RANDOM);
        this.appendMachine(pos, efficiency, true);
    }

    public static EFFNetwork create(AbstractEFFBlockEntity blockEntity) {
        if (blockEntity.hasNetwork() || !blockEntity.isSourceMachine()) return null;
        return create(blockEntity.getBlockPos(), blockEntity.getMachineEfficiency());
    }

    public static EFFNetwork create(BlockPos pos, int efficiency) {
        EFFNetwork network = new EFFNetwork(pos, efficiency);
        NETWORK.put(network.id, network);
        return network;
    }

    public boolean appendMachine(AbstractEFFBlockEntity blockEntity) {
        return this.appendMachine(blockEntity.getBlockPos(), blockEntity.getMachineEfficiency(), blockEntity.isSourceMachine());
    }

    public boolean appendMachine(BlockPos pos, int efficiency, boolean isSourceMachine) {
        if (this.sources.containsKey(pos) || this.members.containsKey(pos)) return false;
        if (isSourceMachine) {
            this.sources.put(pos, efficiency);
            this.genEfficiency += efficiency;
            this.restEfficiency += efficiency;
        }else {
            this.members.put(pos, efficiency);
            this.restEfficiency -= efficiency; //TODO 根据剩余功率修改输出功率
        }
        return true;
    }

    public boolean removeMachine(BlockPos pos) {
        if (this.sources.containsKey(pos)) {
            int efficiency = this.sources.get(pos);
            this.genEfficiency -= efficiency;
            this.restEfficiency -= efficiency;
            this.sources.remove(pos);
            return true;
        }else if (this.members.containsKey(pos)) {
            int efficiency = this.members.get(pos);
            this.restEfficiency += efficiency;
            this.members.remove(pos);
            return true;
        }else {
            return false;
        }
    }

    public int getGenEfficiency() {
        return genEfficiency;
    }

    public int getRestEfficiency() {
        return restEfficiency;
    }

    public int getOutput(BlockPos pos) {
        return this.members.getOrDefault(pos, 0);
    }
}
