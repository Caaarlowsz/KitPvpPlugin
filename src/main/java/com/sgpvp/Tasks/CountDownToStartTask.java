package com.sgpvp.Tasks;

import com.sgpvp.GameLogic.Chat;
import com.sgpvp.main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownToStartTask extends BukkitRunnable {

    main plugin;

    public CountDownToStartTask(main plugin){
        this.plugin = plugin;
    }
    public static int timerCurrent = 15;

    @Override
    public void run() {

        //15 seconds till start
        if(timerCurrent == 15){
            Chat.SGPvPMessage(ChatColor.DARK_GREEN + "Game Starting In 15 Seconds");
        }

        // When 4 seconds left
        if(timerCurrent > 0 && timerCurrent <= 4){
            Chat.SGPvPMessage(ChatColor.GREEN + "Game Starting in... " + timerCurrent);
        }
        timerCurrent -= 1;
    }
}
