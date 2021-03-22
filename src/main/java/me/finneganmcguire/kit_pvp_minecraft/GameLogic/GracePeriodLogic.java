package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.GameState;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.PlayerInteractions;
import org.bukkit.World;

public class GracePeriodLogic {
    public static void GracePeriodEnd(World w){
        w.setPVP(true);
        PlayerInteractions.playerCanDropLava = true;
        GameState.gameState = GameState.gamestate_main;
        System.out.println("GAME STATE IS NOW: " + GameState.gameState);
    }
}
