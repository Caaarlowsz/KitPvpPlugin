package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GracePeriodLogic;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class NightTimeChecker extends BukkitRunnable {
    Kit_PvP_Minecraft plugin;

    public NightTimeChecker(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    public World currentWorld = Kit_PvP_Minecraft.world;
    public static boolean day;

    @Override
    public void run() {
        if(currentWorld.getTime() < 13000){
            // It is day
            day = true;
        } else if(currentWorld.getTime() >= 13000){
            // It is night
            day = false;
        }
    }

    public static boolean IsItDaytime(){
        return day;
    }
}
