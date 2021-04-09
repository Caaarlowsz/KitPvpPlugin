package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {
    public static void SGPvPMessage(Player p, String message) {
        String prefix = "&f[&6SG&4PvP&f] &r";
        message = ChatColor.translateAlternateColorCodes('&', prefix + message);
        p.sendMessage(message);
    }

    public static void SGPvPMessage(String message) {
        String prefix = "&f[&6SG&4PvP&f] &r";
        message = ChatColor.translateAlternateColorCodes('&', prefix + message);
        Bukkit.broadcastMessage(message);
    }

    public static void DebugMessage(String message) {
        String prefix = "&f[&6DEBUG&f] &r";
        message = ChatColor.translateAlternateColorCodes('&', prefix + message);
        if (GameVariables.DEBUG) Bukkit.broadcastMessage(message);
    }
}
