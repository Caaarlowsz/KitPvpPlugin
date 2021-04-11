package com.sgpvp.Tasks;

import com.sgpvp.GameLogic.Chat;
import com.sgpvp.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;

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
            Chat.SGPVPGlobalTitle("Game Starting", "In 15 Seconds", "#FF3933", "#B52C28");
        }

        // When 4 seconds left
        if(timerCurrent > 0 && timerCurrent <= 4){
            Chat.SGPVPGlobalTitle("Game Starting","In " + timerCurrent + " Seconds", "#FF3933", "#B52C28");
        }

        timerCurrent -= 1;
    }
}
