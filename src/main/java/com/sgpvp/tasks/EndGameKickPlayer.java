package com.sgpvp.tasks;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EndGameKickPlayer extends BukkitRunnable {
    main plugin;

    public EndGameKickPlayer(main plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // Kick All Players
        for (int i = 0; i < GameVariables.world.getPlayers().size(); i++) {
            Player player = GameVariables.world.getPlayers().get(i);
            player.kickPlayer(ChatColor.GOLD + "Thank You For Playing :)");
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.getServer().dispatchCommand(console, "restart");
        }
    }
}
