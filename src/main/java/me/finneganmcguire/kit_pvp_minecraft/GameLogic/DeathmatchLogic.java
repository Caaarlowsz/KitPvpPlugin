package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import com.google.common.util.concurrent.Service;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.GameState;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldEvent;

import static java.lang.Math.*;

public class DeathmatchLogic{

    private static int r = 30;


    private static Location deathmatchLocation = Kit_PvP_Minecraft.world.getSpawnLocation(); //Change to somewhere else???
    static int deathMatchCenterX = deathmatchLocation.getBlockX();
    static int deathMatchCenterZ = deathmatchLocation.getBlockZ();
    static int deathMatchCenterY = Kit_PvP_Minecraft.world.getHighestBlockYAt((int) round(deathMatchCenterX), (int) round(deathMatchCenterZ)) - 1;

    // When deathmatch begins
    public static void DeathmatchBegin(){

        GameState.gameState = GameState.gamestate_deathmatch;
        System.out.println("GAME STATE IS NOW: " + GameState.gameState);

        GameVariables.SGPvPMessage(ChatColor.DARK_RED + "THE DEATHMATCH BEGINS NOW! FIGHT TO THE DEATH!");
        //Remove blocks above chests
        for (int i = deathMatchCenterX - r; i <= deathMatchCenterX + r; i++) {
            for (int j = deathMatchCenterZ - r; j <= deathMatchCenterZ + r; j++) {
                Location grass = new Location(Kit_PvP_Minecraft.world, i, deathMatchCenterY, j);
                grass.getBlock().setType(Material.GRASS_BLOCK); //spawn grass
                for (int k = 1; k < 100; k++ ) {
                    Location air = new Location(Kit_PvP_Minecraft.world, i, deathMatchCenterY+k, j);
                    air.getBlock().setType(Material.AIR); //spawn grass
                }
            }
        }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder center %d %d", deathMatchCenterX, deathMatchCenterZ));
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder set %d", r*2));
        hold();

    }
    public static void hold() {
        r -= 10;
        int playerCount = Kit_PvP_Minecraft.world.getPlayers().size();
        // Finds all players left and teleports them to spawn
        for (int i = 0; i < playerCount; i++) {
            double tX = deathMatchCenterX + r*cos(2*PI*i/playerCount);
            double tZ = deathMatchCenterZ + r*sin(2*PI*i/playerCount);
            double tY = deathMatchCenterY;
            Location tLocation = new Location(Kit_PvP_Minecraft.world, tX, tY, tZ);
            Kit_PvP_Minecraft.world.getPlayers().get(i).teleport(tLocation);
        }
    }
}
