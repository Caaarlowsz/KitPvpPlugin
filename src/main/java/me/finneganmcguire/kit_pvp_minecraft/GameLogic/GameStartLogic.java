package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import org.bukkit.GameMode;
import org.bukkit.World;

public class GameStartLogic {

    public static void GameStart(World w){
        // Finds all players and teleports them to spawn
        for (int i = 0; i < w.getPlayers().size(); i++) {
            w.getPlayers().get(i).teleport(w.getSpawnLocation());
            w.getPlayers().get(i).setGameMode(GameMode.SURVIVAL);
        }
    }

}
