package com.sgpvp.Tasks;

import com.sgpvp.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class ForceEndServer extends BukkitRunnable implements Listener {

    main plugin;

    public ForceEndServer(main plugin){
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