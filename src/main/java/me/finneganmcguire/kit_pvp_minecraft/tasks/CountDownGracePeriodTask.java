package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownGracePeriodTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public CountDownGracePeriodTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }
    public static int timerCurrent = 60;

    @Override
    public void run() {

        //15 seconds till start
        if(timerCurrent == 60){
            Bukkit.broadcastMessage(ChatColor.BOLD + "Grace Period Ends In 1 Minute");
        }

        if(timerCurrent == 30){
            Bukkit.broadcastMessage(ChatColor.BOLD + "Grace Period Ends In 30 Seconds");
        }

        if(timerCurrent == 15){
            Bukkit.broadcastMessage(ChatColor.BOLD + "Grace Period Ends In 15 Seconds");
        }

        // When 4 seconds left
        if(timerCurrent > 0 && timerCurrent <= 5){
            Bukkit.broadcastMessage(ChatColor.WHITE + "Grace Period Ends In... " + timerCurrent);
        }

        timerCurrent -= 1;
    }
}