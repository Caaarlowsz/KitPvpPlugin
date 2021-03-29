package com.sgpvp.Tasks;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.DeathmatchLogic;
import com.sgpvp.main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathmatchTask extends BukkitRunnable {

    main plugin;

    public DeathmatchTask(main plugin){
        this.plugin = plugin;
    }

    int timer = 180;

    // When Deathmatch task is called, run this
    @Override
    public void run() {

        if(timer == 180){
            GameVariables.SGPvPMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 3 MINUTES");
        }

        else if(timer == 120){
            GameVariables.SGPvPMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 2 MINUTES");
        }

        else if(timer == 60){
            GameVariables.SGPvPMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 1 MINUTE");
        }

        else if(timer == 30){
            GameVariables.SGPvPMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 30 SECONDS!");
        }

        else if(timer == 10){
            GameVariables.SGPvPMessage(ChatColor.DARK_RED + "DEATHMATCH STARTING IN 10 SECONDS!");
        }

        else if(timer <= 5 && timer > 1){
            GameVariables.SGPvPMessage(ChatColor.RED + "DEATHMATCH STARTING IN " + timer + "!");
        }

        else if (timer == 1){
            DeathmatchLogic.DeathmatchBegin();
        }

        timer--;
    }
}