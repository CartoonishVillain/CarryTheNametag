package com.cartoonishvillain.carrythenametag;

import net.fabricmc.api.ModInitializer;

public class FabricCarry implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonCarry.init();
    }
}
