package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import org.bukkit.World;

public class GracePeriodLogic {
    public static void GracePeriodEnd(World w){
        w.setPVP(true);
    }
}
