package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

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