package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.DeathmatchLogic;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.DeathmatchLogic.*;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import me.finneganmcguire.kit_pvp_minecraft.tasks.ChestCircleSpawnTask;

public class DeathmatchTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public DeathmatchTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    int timer = 180;

    // When Deathmatch task is called, run this
    @Override
    public void run() {

        if(timer == 180){
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 3 MINUTES");
        }

        else if(timer == 120){
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 2 MINUTES");
        }

        else if(timer == 60){
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 1 MINUTE");
        }

        else if(timer == 30){
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 30 SECONDS!");
        }

        else if(timer == 10){
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 10 SECONDS!");
        }

        else if(timer <= 5 && timer > 1){
            Bukkit.broadcastMessage(ChatColor.RED + "DEATHMATCH STARTING IN " + timer + "!");
        }

        else if (timer == 1){
            DeathmatchLogic.DeathmatchBegin();
        }

        timer--;
    }
}