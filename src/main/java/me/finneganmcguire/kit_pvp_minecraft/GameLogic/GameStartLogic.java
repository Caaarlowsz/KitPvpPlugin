package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.GameState;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.PlayerInteractions;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class GameStartLogic {
    static final int WORLDSIZE = 500;

    static final int spawnX = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockX();
    static final int spawnZ = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockZ();

    // When Game Starts
    public static void GameStart(World w){

        GameState.gameState = GameState.gamestate_graceperiod;
        System.out.println("GAME STATE IS NOW: " + GameState.gameState);

        // Finds all players and teleports them to spawn
        for (int i = 0; i < w.getPlayers().size(); i++) {
            w.getPlayers().get(i).teleport(w.getSpawnLocation());
            w.getPlayers().get(i).setGameMode(GameMode.SURVIVAL);
            //remove potion effects
            for(PotionEffect effect : w.getPlayers().get(i).getActivePotionEffects())
                w.getPlayers().get(i).removePotionEffect(effect.getType());
            w.getPlayers().get(i).getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        }
        Kit_PvP_Minecraft.canChangeKit = false;
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder center %d %d", spawnX, spawnZ));
        Bukkit.getServer().dispatchCommand(console, String.format("worldborder set %d", WORLDSIZE));

        PlayerInteractions.playersCanDropItems = true;
        PlayerInteractions.playerCanTakeDamage = true;

        //set time to morning
        w.setTime(0);


    }
}
