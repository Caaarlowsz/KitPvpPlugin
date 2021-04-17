package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameLog;
import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.Kits.KitConfig.KitDescriptions;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Formatter;

public class Chat implements Listener {
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
        GameLog.saveError(new Exception(message));
        String prefix = "&f[&6DEBUG&f] &r";
        message = ChatColor.translateAlternateColorCodes('&', prefix + message);
        if (GameVariables.DEBUG) Bukkit.broadcastMessage(message);
    }

    public static void SGPVPGlobalTitle(String title, String subtitle, String color, String subcolor){
        //for (Player player : Bukkit.getOnlinePlayers()) {
        //    player.sendTitle(title, subtitle, 1, 2, 1);
        //}
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("title @a title {\"text\":\"%s\",\"color\":\"%s\"}", title, color));
        Bukkit.getServer().dispatchCommand(console, String.format("title @a subtitle {\"text\":\"%s\",\"color\":\"%s\"}", subtitle, subcolor));
    }

    public static void SGPVPPersonalTitle(String player,String title, String subtitle, String color, String subcolor){
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, String.format("title %s title {\"text\":\"%s\",\"color\":\"%s\"}", player, title, color));
    }

    public static String getPrefix(Player p) {
        String prefix = "";
        if (p.isOp()) prefix += ChatColor.translateAlternateColorCodes('&', "&f[&6★&f]&r");
        prefix += ChatColor.translateAlternateColorCodes('&', "&f[");
        prefix += KitDescriptions.color(PlayerData.getPlayerKit(p)) + KitDescriptions.getKitName(PlayerData.getPlayerKit(p));
        prefix += ChatColor.translateAlternateColorCodes('&', "&f] &r");

        return prefix;
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        TextComponent message = new TextComponent(getPrefix(player) + player.getDisplayName());
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/stp " + player.getName()));
        message.addExtra(ChatColor.WHITE + " > " + event.getMessage());
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Teleport to player").create()));
        for (Player players : event.getRecipients()) { players.spigot().sendMessage(message); }
        event.setCancelled(true);
    }
}
