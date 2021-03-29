package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.PlayerInteractions;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;

public class GameStartLogic {

    static final int spawnX = GameVariables.world.getSpawnLocation().getBlockX();
    static final int spawnZ = GameVariables.world.getSpawnLocation().getBlockZ();

    // When Game Starts
    public static void GameStart(World w){

        GameVariables.gameState = GameVariables.gamestate_graceperiod;
        System.out.println("GAME STATE IS NOW: " + GameVariables.gameState);

        // Finds all players and teleports them to spawn
        for (int i = 0; i < w.getPlayers().size(); i++) {
            w.getPlayers().get(i).teleport(w.getSpawnLocation());
            w.getPlayers().get(i).setGameMode(GameMode.SURVIVAL);
            //remove potion effects
           // for(PotionEffect effect : w.getPlayers().get(i).getActivePotionEffects())
            //    w.getPlayers().get(i).removePotionEffect(effect.getType());
            w.getPlayers().get(i).getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        }
        GameVariables.canChangeKit = false;
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder center %d %d", spawnX, spawnZ));
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder set %d", GameVariables.WORLDSIZE));

        PlayerInteractions.playersCanDropItems = true;
        PlayerInteractions.playerCanTakeDamage = true;

        GameVariables.world.setTime(GameVariables.WorldTimeWhenGameStarts);
        GameVariables.world.setDifficulty(Difficulty.NORMAL);
    }
}
