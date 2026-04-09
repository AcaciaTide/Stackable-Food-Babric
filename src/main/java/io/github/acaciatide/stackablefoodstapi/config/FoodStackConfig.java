package io.github.acaciatide.stackablefoodstapi.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class FoodStackConfig {
    @ConfigEntry(name = "Max Food Stack Size", maxLength = 64)
    public Integer maxFoodStackSize = 64;
}
