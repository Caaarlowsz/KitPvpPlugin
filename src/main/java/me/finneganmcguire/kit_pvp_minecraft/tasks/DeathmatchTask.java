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

    // When Deathmatch task is called, run this
    @Override
    public void run() {
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING SOON...");
        DeathmatchLogic.DeathmatchBegin(Kit_PvP_Minecraft.world);
    }
}