package com.xekr.ironstars.state;

import com.google.common.collect.Lists;
import com.xekr.ironstars.efficiency.EFFNetwork;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.List;
import java.util.UUID;

public class NetworkState {
    private final List<Runnable> dirtyListeners = Lists.newArrayList();

    private void addDirtyListener(Runnable pRunnable) {
        this.dirtyListeners.add(pRunnable);
    }

    public void markDirty() {
        for(Runnable runnable : this.dirtyListeners) {
            runnable.run();
        }
    }

    public NetworkStateSavedData loadData() {
        NetworkStateSavedData state = new NetworkStateSavedData();
        this.addDirtyListener(state::setDirty);
        return state;
    }

    public NetworkStateSavedData loadData(CompoundTag nbt) {
        return this.loadData().load(nbt);
    }

    public static class NetworkStateSavedData extends SavedData{
        private NetworkStateSavedData() {}

        @Override
        public CompoundTag save(CompoundTag nbt) {
            ListTag listTag = EFFNetwork.serialize();
            nbt.put("networks", listTag);
            return nbt;
        }

        public NetworkStateSavedData load(CompoundTag nbt) {
            for (Tag network : nbt.getList("networks", Tag.TAG_STRING)) {
                String uuidString = network.getAsString();
                UUID uuid = UUID.fromString(uuidString);
                EFFNetwork.create(uuid);
            }
            EFFNetwork.freeze();
            return this;
        }
    }
}
