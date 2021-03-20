package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.PlayerInteractions;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;

public class GameStartLogic {
    static final int WORLDSIZE = 500;

    static final int spawnX = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockX();
    static final int spawnZ = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockZ();

    // When Game Starts
    public static void GameStart(World w){
        // Finds all players and teleports them to spawn
        for (int i = 0; i < w.getPlayers().size(); i++) {
            w.getPlayers().get(i).teleport(w.getSpawnLocation());
            w.getPlayers().get(i).setGameMode(GameMode.SURVIVAL);
        }
        Kit_PvP_Minecraft.canChangeKit = false;
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder center %d %d", spawnX, spawnZ));
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder set %d", WORLDSIZE));

        PlayerInteractions.playersCanDropItems = true;
    }
}
