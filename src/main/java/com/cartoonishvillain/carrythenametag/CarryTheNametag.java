package com.cartoonishvillain.carrythenametag;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CarryTheNametag.MODID)
public class CarryTheNametag
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "carrythenametag";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CarryTheNametag(IEventBus modEventBus)
    {
        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onBabyBorn(BabyEntitySpawnEvent event)
    {
        if (event.getParentA() instanceof Animal && event.getParentB() instanceof Animal)
            doRename((Animal) event.getParentA(), (Animal) event.getParentB(), event.getChild());
    }


    public static void doRename(Animal animal1, Animal animal2, AgeableMob ageableMob) {
        Component componentcustomName1;
        Component componentcustomName2;

        try {
            componentcustomName1 = animal1.getCustomName();
            componentcustomName2 = animal2.getCustomName();
        } catch (ClassCastException e) {
            return;
        }

        if(componentcustomName1 != null && componentcustomName2 != null) {
            String customName1 = componentcustomName1.getString();
            String customName2 = componentcustomName2.getString();
            if(customName1.equals(customName2)){
                //the custom names are the same, so this new animal also has the same name.
                ageableMob.setCustomName(Component.literal(customName1));
            }else{
                if(ageableMob.getRandom().nextInt(2) == 1) {
                    String newname1 = customName1.substring(0, customName1.length()/2);
                    String newname2 = customName2.substring(customName2.length()/2);
                    ageableMob.setCustomName(Component.literal(newname1 + newname2));
                }
                else {
                    String newname1 = customName2.substring(0, customName2.length()/2);
                    String newname2 = customName1.substring(customName1.length()/2);
                    ageableMob.setCustomName(Component.literal(newname1 + newname2));
                }

                ageableMob.setPersistenceRequired();
            }
        }
    }
}
