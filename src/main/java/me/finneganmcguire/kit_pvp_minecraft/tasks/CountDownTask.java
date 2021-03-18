package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public CountDownTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }
    int timerCurrent = 4;

    @Override
    public void run() {
        Bukkit.broadcastMessage("Game Starting in... " + timerCurrent);
        timerCurrent -= 1;
    }
}
