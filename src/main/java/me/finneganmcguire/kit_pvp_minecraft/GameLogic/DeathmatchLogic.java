package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;

import static java.lang.Math.*;

public class DeathmatchLogic{

    private static int r = 30;


    private static Location deathmatchLocation = GameVariables.world.getSpawnLocation(); //Change to somewhere else???
    static int deathMatchCenterX = deathmatchLocation.getBlockX();
    static int deathMatchCenterZ = deathmatchLocation.getBlockZ();
    static int deathMatchCenterY = GameVariables.world.getHighestBlockYAt((int) round(deathMatchCenterX), (int) round(deathMatchCenterZ)) - 1;

    // When deathmatch begins
    public static void DeathmatchBegin(){

        GameVariables.gameState = GameVariables.gamestate_deathmatch;
        System.out.println("GAME STATE IS NOW: " + GameVariables.gameState);

        GameVariables.SGPvPMessage(ChatColor.DARK_RED + "THE DEATHMATCH BEGINS NOW! FIGHT TO THE DEATH!");
        //Remove blocks above chests
        for (int i = deathMatchCenterX - r; i <= deathMatchCenterX + r; i++) {
            for (int j = deathMatchCenterZ - r; j <= deathMatchCenterZ + r; j++) {
                Location grass = new Location(GameVariables.world, i, deathMatchCenterY, j);
                grass.getBlock().setType(Material.GRASS_BLOCK); //spawn grass
                for (int k = 1; k < 100; k++ ) {
                    Location air = new Location(GameVariables.world, i, deathMatchCenterY+k, j);
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
        int playerCount = GameVariables.world.getPlayers().size();
        // Finds all players left and teleports them to spawn
        for (int i = 0; i < playerCount; i++) {
            double tX = deathMatchCenterX + r*cos(2*PI*i/playerCount);
            double tZ = deathMatchCenterZ + r*sin(2*PI*i/playerCount);
            double tY = deathMatchCenterY;
            Location tLocation = new Location(GameVariables.world, tX, tY, tZ);
            GameVariables.world.getPlayers().get(i).teleport(tLocation);
        }
    }
}
