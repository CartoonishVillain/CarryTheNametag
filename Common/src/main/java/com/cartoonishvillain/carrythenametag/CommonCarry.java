package com.cartoonishvillain.carrythenametag;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;

public class CommonCarry {
    public static void init() {


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
                String name1 = customName1;
                String name2 = customName2;



                if(ageableMob.getRandom().nextInt(2) == 1) {
                    String newname1 = name1.substring(0, name1.length()/2);
                    String newname2 = name2.substring(name2.length()/2);
                    ageableMob.setCustomName(Component.literal(newname1 + newname2));
                }
                else {
                    String newname1 = name2.substring(0, name2.length()/2);
                    String newname2 = name1.substring(name1.length()/2);
                    ageableMob.setCustomName(Component.literal(newname1 + newname2));
                }

                ageableMob.setPersistenceRequired();
            }
        }

    }
}