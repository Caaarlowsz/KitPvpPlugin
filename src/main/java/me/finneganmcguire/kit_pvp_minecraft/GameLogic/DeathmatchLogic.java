package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import com.google.common.util.concurrent.Service;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldEvent;

import static java.lang.Math.*;

public class DeathmatchLogic{
    //TODO
    private static Location deathmatchLocation;
    private static float r = 15;

    // When deathmatch begins
    public static void DeathmatchBegin(World e){
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "THE DEATHMATCH BEGINS NOW! FIGHT TO THE DEATH!");

        deathmatchLocation = e.getSpawnLocation(); //Change to somewhere else???
        float deathMatchCenterX = deathmatchLocation.getBlockX();
        //float deathMatchCenterY = deathmatchLocation.getBlockY();
        float deathMatchCenterZ = deathmatchLocation.getBlockZ();
        int playerCount = e.getPlayers().size();

        // Finds all players left and teleports them to spawn
        for (int i = 0; i < playerCount; i++) {
            double tX = deathMatchCenterX + r*cos(2*PI*i/playerCount);
            double tZ = deathMatchCenterZ + r*sin(2*PI*i/playerCount);
            double tY = e.getHighestBlockYAt((int) round(tX), (int) round(tZ)) + 1;
            Location tLocation = new Location(e, tX, tY, tZ);
            e.getPlayers().get(i).teleport(tLocation);
        }
    }
}
