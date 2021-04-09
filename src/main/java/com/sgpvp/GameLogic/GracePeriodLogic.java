package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GlobalEvents.PlayerInteractions;
import org.bukkit.World;

public class GracePeriodLogic {
    public static void GracePeriodEnd(World w){
        w.setPVP(true);
        PlayerInteractions.playerCanDropLava = true;
        GameVariables.gameState = GameVariables.gamestate_main;
        System.out.println("GAME STATE IS NOW: " + GameVariables.gameState);
    }
}
