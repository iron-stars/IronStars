package com.xekr.ironstars.efficiency;

import com.xekr.ironstars.IronStarsUtil;
import com.xekr.ironstars.blocks.entity.EFFMachine;
import com.xekr.ironstars.state.NetworkState;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class EFFNetwork {
    private static final NetworkState NETWORK_STATE = new NetworkState();
    private static final Map<UUID, EFFNetwork> NETWORK = new HashMap<>();
    private static boolean frozenRegisterByUUID = false;

    private final UUID id;
    private int genEfficiency;
    private int restEfficiency;
    private final Map<EFFMachine, Integer> sources = new HashMap<>();
    private final Map<EFFMachine, Integer> members = new LinkedHashMap<>();

    private EFFNetwork() {
        this(Mth.createInsecureUUID(IronStarsUtil.RANDOM));
    }

    private EFFNetwork(UUID uuid) {
        this.id = uuid;
        NETWORK.put(this.id, this);
    }

    public static EFFNetwork create(EFFMachine machine) {
        if (machine.hasNetwork() || !machine.isSourceMachine()) return null;
        EFFNetwork network = new EFFNetwork();
        network.appendMachine(machine);
        NETWORK_STATE.markDirty();
        return network;
    }

    public static EFFNetwork create(UUID uuid) {
        if (frozenRegisterByUUID) throw new IllegalStateException("Creating network by uuid is already frozen");
        return new EFFNetwork(uuid);
    }

    public static void freeze() {
        frozenRegisterByUUID = true;
    }

    public static ListTag serialize() {
        ListTag listTag = new ListTag();
        for (Map.Entry<UUID, EFFNetwork> uuidNetworkEntry : NETWORK.entrySet()) {
            listTag.add(StringTag.valueOf(uuidNetworkEntry.getKey().toString()));
        }
        return listTag;
    }

    public static void merge(EFFNetwork networkA, EFFNetwork networkB) {
        networkA.sources.putAll(networkB.sources);
        networkA.members.putAll(networkB.members);
        for (Map.Entry<EFFMachine, Integer> machineIntEntry : networkB.sources.entrySet()) {
            machineIntEntry.getKey().setNetwork(networkA);
        }
        for (Map.Entry<EFFMachine, Integer> machineIntEntry : networkB.members.entrySet()) {
            machineIntEntry.getKey().setNetwork(networkA);
        }
        networkB.recycle();
        NETWORK_STATE.markDirty();
    }

    public void appendMachine(EFFMachine machine) {
        if (this.sources.containsKey(machine) || this.members.containsKey(machine)) return;
        int efficiency = machine.getMachineEfficiency();
        machine.setNetwork(this);
        if (machine.isSourceMachine()) {
            this.sources.put(machine, efficiency);
            this.genEfficiency += efficiency;
            this.restEfficiency += efficiency;
        }else {
            this.members.put(machine, efficiency);
            this.restEfficiency -= efficiency; //TODO 根据剩余功率修改输出功率
        }
        NETWORK_STATE.markDirty();
    }

    @Nullable
    public void removeMachine(EFFMachine machine) {
        machine.setNetwork(null);
        if (this.sources.containsKey(machine)) {
            int efficiency = this.sources.get(machine);
            this.genEfficiency -= efficiency;
            this.restEfficiency -= efficiency;
            this.sources.remove(machine);
        }else if (this.members.containsKey(machine)) {
            int efficiency = this.members.get(machine);
            this.restEfficiency += efficiency;
            this.members.remove(machine);
        }
        NETWORK_STATE.markDirty();
    }

    public void recycle() {
        for (Map.Entry<EFFMachine, Integer> blockPosIntEntry : this.sources.entrySet()) {
            blockPosIntEntry.getKey().setNetwork(null);
        }
        for (Map.Entry<EFFMachine, Integer> blockPosIntEntry : this.members.entrySet()) {
            blockPosIntEntry.getKey().setNetwork(null);
        }
        NETWORK.remove(this.id);
        NETWORK_STATE.markDirty();
    }

    public UUID getId() {
        return this.id;
    }

    public int getGenEfficiency() {
        return this.genEfficiency;
    }

    public int getRestEfficiency() {
        return this.restEfficiency;
    }

    public int getOutput(EFFMachine machine) {
        return this.members.getOrDefault(machine, 0);
    }

    public static NetworkState getState() {
        return NETWORK_STATE;
    }

    public static EFFNetwork getNetwork(UUID uuid) {
        return NETWORK.getOrDefault(uuid, null);
    }
}
