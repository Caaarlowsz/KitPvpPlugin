package com.sgpvp.Tasks;

import com.sgpvp.GameLogic.Chat;
import com.sgpvp.main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownGracePeriodTask extends BukkitRunnable {

    main plugin;

    public CountDownGracePeriodTask(main plugin){
        this.plugin = plugin;
    }
    public static int timerCurrent = 60;

    @Override
    public void run() {

        //15 seconds till start
        if(timerCurrent == 60){
            Chat.SGPVPGlobalTitle("Grace Period", "Ends In 1 Minute", "", "");
        }

        if(timerCurrent == 30){
            Chat.SGPVPGlobalTitle("Grace Period", "Ends In 30 Seconds", "", "");
        }

        if(timerCurrent == 15){
            Chat.SGPVPGlobalTitle("Grace Period", "Ends In 15 Seconds", "", "");
        }

        // When 4 seconds left
        if(timerCurrent > 0 && timerCurrent <= 5){
            Chat.SGPVPGlobalTitle("Grace Period Ends In ", "Ends In " + timerCurrent, "", "");
        }

        timerCurrent -= 1;
    }
}
