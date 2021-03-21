package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownToStartTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public CountDownToStartTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }
    public static int timerCurrent = 15;

    @Override
    public void run() {

        //15 seconds till start
        if(timerCurrent == 15){
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Game Starting In 15 Seconds");
        }

        // When 4 seconds left
        if(timerCurrent > 0 && timerCurrent <= 4){
            Bukkit.broadcastMessage(ChatColor.GREEN + "Game Starting in... " + timerCurrent);
        }
        timerCurrent -= 1;
    }
}
