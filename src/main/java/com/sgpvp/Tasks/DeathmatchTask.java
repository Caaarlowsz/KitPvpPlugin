package com.sgpvp.Tasks;

import com.sgpvp.GameLogic.Chat;
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

        String deathmatchTitle_Color = "#A0322E";
        String deathmatchSubTitle_Color = "#901612";

        if(timer == 180){
            Chat.SGPVPGlobalTitle("DEATHMATCH", "STARTING IN 3 MINUTES", deathmatchTitle_Color, deathmatchSubTitle_Color);
        }

        else if(timer == 60){
            Chat.SGPVPGlobalTitle("DEATHMATCH", "STARTING IN 1 MINUTE", deathmatchTitle_Color, deathmatchSubTitle_Color);
        }

        else if(timer == 30){
            Chat.SGPVPGlobalTitle("DEATHMATCH", "STARTING IN 30 SECONDS", deathmatchTitle_Color, deathmatchSubTitle_Color);
        }

        else if(timer == 10){
            Chat.SGPVPGlobalTitle("DEATHMATCH", "STARTING IN 10 SECONDS", deathmatchTitle_Color, deathmatchSubTitle_Color);
        }

        else if(timer <= 5 && timer >= 1){
            Chat.SGPVPGlobalTitle("DEATHMATCH", "STARTING IN " + timer + " SECONDS", "", "");
        }

        else if (timer == 0){
            DeathmatchLogic.DeathmatchBegin();
        }

        timer--;
    }
}