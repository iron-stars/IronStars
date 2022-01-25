package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AllSounds {
    private static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IronStars.ID);

    public static final SoundEvent DEEP_SPACE = register("deep_space");

    private static SoundEvent register(String name) {
        SoundEvent sound = new SoundEvent(IronStars.id(name));
        REGISTRY.register(name, () -> sound);
        return sound;
    }

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
