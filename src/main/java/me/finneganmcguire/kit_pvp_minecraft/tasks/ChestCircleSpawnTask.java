package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.FeastLogic;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import me.finneganmcguire.kit_pvp_minecraft.tasks.ChestCircleSpawnTask;

public class ChestCircleSpawnTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public ChestCircleSpawnTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    int timer = 120; // 2min timer

    @Override
    public void run() {

        if(timer == 120){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "FEAST SPAWNING IN 2 MINUTES!");
        }

        else if(timer == 60){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "FEAST SPAWNING IN 1 MINUTE!");
        }

        else if(timer == 30){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "FEAST SPAWNING IN 30 SECONDS!");
        }

        else if(timer == 10){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "FEAST SPAWNING IN 10 SECONDS!");
        }

        else if(timer < 10 && timer > 1){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "FEAST SPAWNING IN " + timer + "!");
        }

        else if (timer == 1){
            FeastLogic.SpawnFeast(Kit_PvP_Minecraft.world);
        }

        timer--;
    }
}
