package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
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
            GameVariables.SGPvPMessage(ChatColor.BOLD + "Grace Period Ends In 1 Minute");
        }

        if(timerCurrent == 30){
            GameVariables.SGPvPMessage(ChatColor.BOLD + "Grace Period Ends In 30 Seconds");
        }

        if(timerCurrent == 15){
            GameVariables.SGPvPMessage(ChatColor.BOLD + "Grace Period Ends In 15 Seconds");
        }

        // When 4 seconds left
        if(timerCurrent > 0 && timerCurrent <= 5){
            GameVariables.SGPvPMessage(ChatColor.WHITE + "Grace Period Ends In... " + timerCurrent);
        }

        timerCurrent -= 1;
    }
}
