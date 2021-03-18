package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.finneganmcguire.kit_pvp_minecraft.tasks.ChestCircleSpawnTask;

public class ForceEndServer extends BukkitRunnable implements Listener {

    Kit_PvP_Minecraft plugin;

    public ForceEndServer(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    // When Deathmatch task is called, run this
    @Override
    public void run() {
        StopServer();
    }

    @EventHandler
    public void StopServer(){
        //WILL STOP SERVER

    }
}