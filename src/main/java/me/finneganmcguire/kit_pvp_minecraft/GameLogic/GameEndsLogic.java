package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameEndsLogic {

    public static void EndGame(Player p){
        Bukkit.broadcastMessage(ChatColor.GOLD + "CONGRATS " + p.getPlayer().getName() + " YOU HAVE WON!");
    }

}
