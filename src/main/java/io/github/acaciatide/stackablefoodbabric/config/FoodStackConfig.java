package io.github.acaciatide.stackablefoodbabric.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class FoodStackConfig {
    @ConfigEntry(name = "Max Food Stack Size",
            maxValue = 64.0,
            requiresRestart = true,
            multiplayerSynced = true
    )
    public Integer maxFoodStackSize = 64;
}
