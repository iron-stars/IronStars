package com.xekr.ironstars.registry;

import com.xekr.ironstars.capability.IStarsEnergyCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class AllCapabilities {
    public static Capability<IStarsEnergyCapability> STARS_ENERGY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});


    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IStarsEnergyCapability.class);
    }
}
