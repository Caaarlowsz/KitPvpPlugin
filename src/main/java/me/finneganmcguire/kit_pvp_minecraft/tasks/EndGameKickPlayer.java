package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.DeathmatchLogic;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EndGameKickPlayer extends BukkitRunnable {
    Kit_PvP_Minecraft plugin;

    public EndGameKickPlayer(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // Kick All Players
        for (int i = 0; i < Kit_PvP_Minecraft.world.getPlayers().size(); i++) {
            Player player = Kit_PvP_Minecraft.world.getPlayers().get(i);
            player.kickPlayer(ChatColor.GOLD + "Thank You For Playing :)");
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.getServer().dispatchCommand(console, "restart");
        }
    }
}
