package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;

public class GameStartLogic {
    static final int WORLDSIZE = 500;
    // When Game Starts
    public static void GameStart(World w){
        // Finds all players and teleports them to spawn
        for (int i = 0; i < w.getPlayers().size(); i++) {
            w.getPlayers().get(i).teleport(w.getSpawnLocation());
            w.getPlayers().get(i).setGameMode(GameMode.SURVIVAL);
        }

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, "worldborder center 0 0");
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder set %d", WORLDSIZE));
    }
}
