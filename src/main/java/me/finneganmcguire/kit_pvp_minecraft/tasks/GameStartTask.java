package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameStartLogic;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.PlayerInteractions;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public GameStartTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        GameVariables.SGPvPMessage(ChatColor.YELLOW + "GAME HAS STARTED!");
        GameStartLogic.GameStart(Kit_PvP_Minecraft.world);

        PlayerInteractions.playerCanDropLava = false;
    }
}
