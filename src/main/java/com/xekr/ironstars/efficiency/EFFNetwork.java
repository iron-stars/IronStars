package com.xekr.ironstars.efficiency;

import com.xekr.ironstars.IronStarsUtil;
import com.xekr.ironstars.blocks.entity.AbstractEFFBlockEntity;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class EFFNetwork {
    private static final Map<UUID, EFFNetwork> NETWORK = new HashMap<>();

    private final UUID id;
    private final Map<AbstractEFFBlockEntity, Integer> sources = new HashMap<>();
    private final Map<AbstractEFFBlockEntity, Integer> members = new LinkedHashMap<>();

    private EFFNetwork(AbstractEFFBlockEntity blockEntity) {
        this.id = Mth.createInsecureUUID(IronStarsUtil.RANDOM);
        this.sources.put(blockEntity, blockEntity.getMachineEfficiency());
    }

    public static EFFNetwork create(AbstractEFFBlockEntity blockEntity) {
        if (blockEntity.hasNetwork() || !blockEntity.isSourceMachine()) return null;
        EFFNetwork network = new EFFNetwork(blockEntity);
        NETWORK.put(network.id, network);
        return network;
    }





}
