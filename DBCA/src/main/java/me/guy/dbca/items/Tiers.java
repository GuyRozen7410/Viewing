package me.guy.dbca.items;


import me.guy.dbca.DBCA;
import net.minecraft.item.Item;

public class Tiers {
    private static final Item[] Tier1 = {DBCA.potatoCore, DBCA.potatoLimb};


    public static boolean getItemInTier(Item item, byte Tier){
        if (Tier == 1){
            for (Item i : Tier1){
                if (i == item){
                    return true;
                }
            }
        }
        return false;
    }

}
