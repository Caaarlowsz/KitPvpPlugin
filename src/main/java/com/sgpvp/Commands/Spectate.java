package com.sgpvp.Commands;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (GameVariables.gameEvents.getGameStateID() < 2) {
                player.setGameMode(GameMode.SURVIVAL);
            }
        } else player.setGameMode(GameMode.SPECTATOR);
        return false;
    }
}