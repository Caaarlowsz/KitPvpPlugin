package me.finneganmcguire.kit_pvp_minecraft.tasks;

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

    @Override
    public void run() {
        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "CHEST CIRCLE SPAWNING SOON!");
    }
}
