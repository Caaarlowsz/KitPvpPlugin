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
    public static int timerCurrent = 31;

    @Override
    public void run() {

        timerCurrent -= 1;

        //30 seconds till start
        if(timerCurrent == 30){
            Bukkit.broadcastMessage("Game Starting In 30 Seconds");
        }

        // When 4 seconds left
        if(timerCurrent > 0 && timerCurrent <= 4){
            Bukkit.broadcastMessage("Game Starting in... " + timerCurrent);
        }
    }
}
