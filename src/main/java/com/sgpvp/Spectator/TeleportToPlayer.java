package com.sgpvp.Spectator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportToPlayer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof  Player)) return false;
        Player player = (Player) sender;
        if (!(player.getGameMode().equals(GameMode.SPECTATOR))) return false;
        if (Bukkit.getPlayer(args[0]) == null) return false;
        player.teleport(Bukkit.getPlayer(args[0]));
        return true;
    }
}
