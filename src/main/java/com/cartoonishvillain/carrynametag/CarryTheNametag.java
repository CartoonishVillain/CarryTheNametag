package com.cartoonishvillain.carrynametag;

import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("carrynametag")
public class CarryTheNametag
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public CarryTheNametag() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
    public static class CarryNametagEvent {
        @SubscribeEvent
        public static void onBlocksRegistry(BabyEntitySpawnEvent event) {
            TextComponent componentcustomName1 = (TextComponent) event.getParentA().getCustomName();
            TextComponent componentcustomName2 = (TextComponent) event.getParentB().getCustomName();

            if(componentcustomName1 != null && componentcustomName2 != null && event.getChild() != null) {
                String customName1 = componentcustomName1.getText();
                String customName2 = componentcustomName2.getText();
                if(customName1.equals(customName2)){
                    //the custom names are the same, so this new animal also has the same name.
                    event.getChild().setCustomName(new TextComponent(customName1));
                }else{
                    String name1 = customName1;
                    String name2 = customName2;

                    String newname1 = name1.substring(0, name1.length()/2);
                    String newname2 = name2.substring(name2.length()/2);

                    if(event.getChild().getRandom().nextInt(2) == 1) event.getChild().setCustomName(new TextComponent(newname1 + newname2));
                    else event.getChild().setCustomName(new TextComponent(newname2 + newname1));
                }
                event.getChild().setPersistenceRequired();
            }
        }
    }
}
