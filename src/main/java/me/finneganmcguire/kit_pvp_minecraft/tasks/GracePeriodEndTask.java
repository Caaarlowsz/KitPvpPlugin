package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameStartLogic;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GracePeriodLogic;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import me.finneganmcguire.kit_pvp_minecraft.tasks.ChestCircleSpawnTask;

public class GracePeriodEndTask extends BukkitRunnable {

    Kit_PvP_Minecraft plugin;

    public GracePeriodEndTask(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(ChatColor.BOLD+ "GRACE PERIOD HAS ENDED");
        GracePeriodLogic.GracePeriodEnd(Kit_PvP_Minecraft.world);
    }
}
