package io.github.acaciatide.stackablefoodbabric;

import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import io.github.acaciatide.stackablefoodbabric.config.FoodStackConfig;

public class StackableFoodBabric {
    // デバッグ用: 初期化タイミング確認
    //static {
    //    // System.out.println("[StackableFood] Config class (GCAPI Entrypoint) is loaded!");
    //}

    @ConfigRoot(value = "config", visibleName = "Stackable Food Config")
    public static final FoodStackConfig config = new FoodStackConfig();
}
