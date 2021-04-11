package com.sgpvp.Tasks;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.Chat;
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
        Chat.SGPVPGlobalTitle("Game Has Started!", " ", "#FF3933", "");
        GameStartLogic.GameStart(GameVariables.world, plugin);

        PlayerInteractions.playerCanDropLava = false;
    }
}
