package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import com.google.common.util.concurrent.Service;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldEvent;

import static java.lang.Math.*;

public class DeathmatchLogic{

    private static Location deathmatchLocation;
    private static int r = 30;

    // When deathmatch begins
    public static void DeathmatchBegin(World e){
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "THE DEATHMATCH BEGINS NOW! FIGHT TO THE DEATH!");

        deathmatchLocation = e.getSpawnLocation(); //Change to somewhere else???
        int deathMatchCenterX = deathmatchLocation.getBlockX();
        int deathMatchCenterZ = deathmatchLocation.getBlockZ();
        int deathMatchCenterY = e.getHighestBlockYAt((int) round(deathMatchCenterX), (int) round(deathMatchCenterZ)) - 1;

        int playerCount = e.getPlayers().size();


        //Remove blocks above chests
        for (int i = deathMatchCenterX - r; i <= deathMatchCenterX + r; i++) {
            for (int j = deathMatchCenterZ - r; j <= deathMatchCenterZ + r; j++) {
                Location grass = new Location(Kit_PvP_Minecraft.world, i, deathMatchCenterY, j);
                grass.getBlock().setType(Material.GRASS_BLOCK); //spawn grass
                for (int k = 1; k < 10; k++ ) {
                    Location air = new Location(Kit_PvP_Minecraft.world, i, deathMatchCenterY+k, j);
                    air.getBlock().setType(Material.AIR); //spawn grass
                }
            }
        }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder center %d %d", deathMatchCenterX, deathMatchCenterZ));
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder set %d", r*2));
        r = 20;
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
