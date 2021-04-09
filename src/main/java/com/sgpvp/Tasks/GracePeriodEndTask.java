package com.sgpvp.Tasks;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.GracePeriodLogic;
import com.sgpvp.main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class GracePeriodEndTask extends BukkitRunnable {

    main plugin;

    public GracePeriodEndTask(main plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Chat.SGPvPMessage(ChatColor.BOLD+ "GRACE PERIOD HAS ENDED");
        GracePeriodLogic.GracePeriodEnd(GameVariables.world);

        GameVariables.gameState = GameVariables.gamestate_main;
    }
}
