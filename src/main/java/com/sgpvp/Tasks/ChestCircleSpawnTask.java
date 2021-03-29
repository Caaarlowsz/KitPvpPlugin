package com.sgpvp.Tasks;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.FeastLogic;
import com.sgpvp.main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class ChestCircleSpawnTask extends BukkitRunnable {

    main plugin;

    public ChestCircleSpawnTask(main plugin){
        this.plugin = plugin;
    }

    int timer = 120; // 2min timer

    @Override
    public void run() {

        if(timer == 120){
            GameVariables.SGPvPMessage(ChatColor.LIGHT_PURPLE + String.format("FEAST SPAWNING IN 2 MINUTES at (%d, %d)!", FeastLogic.feast_center_x, FeastLogic.feast_center_z));
            FeastLogic.SpawnFeastPlatform();
        }

        else if(timer == 60){
            GameVariables.SGPvPMessage(ChatColor.LIGHT_PURPLE + String.format("FEAST SPAWNING IN 1 MINUTE at (%d, %d)!", FeastLogic.feast_center_x, FeastLogic.feast_center_z));
        }

        else if(timer == 10 || timer == 30 || timer < 10 && timer > 1){
            GameVariables.SGPvPMessage(ChatColor.LIGHT_PURPLE + String.format("FEAST SPAWNING IN %d SECONDS at (%d, %d)!", timer, FeastLogic.feast_center_x, FeastLogic.feast_center_z));
        }

        else if (timer == 1){
            FeastLogic.SpawnFeast();
        }

        timer--;
    }
}
