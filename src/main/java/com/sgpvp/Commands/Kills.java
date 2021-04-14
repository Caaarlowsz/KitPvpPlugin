package com.sgpvp.Commands;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.Chat;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kills implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        //int kills = GameVariables.killLeaderboard.getKills(player);
        //Chat.SGPvPMessage(player, String.format("You have %d kills", kills));
        return true;
    }
}