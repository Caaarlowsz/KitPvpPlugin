package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import com.google.common.util.concurrent.Service;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldEvent;

public class DeathmatchLogic{

    public static void DeathmatchBegin(World e){
        Bukkit.broadcastMessage("THE DEATHMATCH BEGINS NOW!");

        // Finds all players left and teleports them to spawn
        for (int i = 0; i < e.getPlayers().size(); i++) {
            e.getPlayers().get(i).teleport(e.getSpawnLocation());
        }
    }

    // Runs When Deathmatch Ends
    public void DeathmatchEnd(Player p){
        Bukkit.broadcastMessage(ChatColor.GREEN + "CONGRATS " + p.getPlayer() + " YOU HAVE WON!");
    }

}
