package com.cartoonishvillain.carrythenametag;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.tooltip.api.client.ItemTooltipCallback;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize(ModContainer mod) {

        // This method is invoked by the Quilt mod loader when it is ready
        // to load your mod. You can access Quilt and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Quilt world!");
        CommonCarry.init();

        // Some code like events require special initialization from the
        // loader specific code.
        ItemTooltipCallback.EVENT.register(CommonCarry::onItemTooltip);
    }
}
