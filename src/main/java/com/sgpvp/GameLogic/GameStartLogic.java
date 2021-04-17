package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GlobalEvents.PlayerInteractions;
import com.sgpvp.main;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameStartLogic {

    static final int spawnX = GameVariables.world.getSpawnLocation().getBlockX();
    static final int spawnZ = GameVariables.world.getSpawnLocation().getBlockZ();

    // When Game Starts
    public static void GameStart(World w, main main){
        for (int i = 0; i < w.getPlayers().size(); i++) {
            w.getPlayers().get(i).setGameMode(GameMode.SURVIVAL);
            w.getPlayers().get(i).getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        }

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder center %d %d", spawnX, spawnZ));
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder set %d", GameVariables.WORLD_SIZE));
        Bukkit.getServer().dispatchCommand(console, String.format("spreadplayers %d %d %d %d %b %s", spawnX, spawnZ, 2, 20, false, "@a"));

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setFoodLevel(20);
            player.setSaturation(20);
        }

        PlayerInteractions.playersCanDropItems = true;
        PlayerInteractions.playerCanTakeDamage = true;
        GameVariables.canChangeKit = false;

        GameVariables.world.setTime(GameVariables.WorldTimeWhenGameStarts);
        GameVariables.world.setDifficulty(Difficulty.EASY);
        GameVariables.plugin.enableKitEvents();
    }
}
