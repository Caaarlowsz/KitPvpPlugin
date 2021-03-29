package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GracePeriodLogic;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.GameState;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class GracePeriodEndTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public GracePeriodEndTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        GameVariables.SGPvPMessage(ChatColor.BOLD+ "GRACE PERIOD HAS ENDED");
        GracePeriodLogic.GracePeriodEnd(Kit_PvP_Minecraft.world);

        GameState.gameState = GameState.gamestate_main;
    }
}
