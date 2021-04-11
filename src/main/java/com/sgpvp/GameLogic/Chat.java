package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Formatter;

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

    public static void SGPVPGlobalTitle(String title, String subtitle, String color, String subcolor){
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("title @a title {\"text\":\"%s\",\"color\":\"%s\"}", title, color));
        Bukkit.getServer().dispatchCommand(console, String.format("title @a subtitle {\"text\":\"%s\",\"color\":\"%s\"}", subtitle, subcolor));
    }
}
