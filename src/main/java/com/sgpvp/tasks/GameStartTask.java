package com.sgpvp.tasks;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.GameStartLogic;
import com.sgpvp.GlobalEvents.PlayerInteractions;
import com.sgpvp.main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartTask extends BukkitRunnable {

    main plugin;

    public GameStartTask(main plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        GameVariables.SGPvPMessage(ChatColor.YELLOW + "GAME HAS STARTED!");
        GameStartLogic.GameStart(GameVariables.world);

        PlayerInteractions.playerCanDropLava = false;
    }
}
