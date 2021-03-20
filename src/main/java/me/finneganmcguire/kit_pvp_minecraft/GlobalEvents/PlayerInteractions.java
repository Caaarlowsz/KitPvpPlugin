package me.finneganmcguire.kit_pvp_minecraft.GlobalEvents;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

public class PlayerInteractions implements Listener {

    // Players can do Actions and Interactions
    public static boolean playersCanDropItems = false;
    public static boolean playerCanDropLava = false;

    @EventHandler
    public void checkIfCanDropItems(PlayerDropItemEvent e){
        if(!playersCanDropItems){
            e.getItemDrop().setPickupDelay(0); // Instant
        } else {
            e.getItemDrop().setPickupDelay(25); // Normal
        }
    }

    @EventHandler
    public void lavaPlaceEvent(PlayerBucketEmptyEvent e){
        if(!playerCanDropLava && e.getBucket().equals(Material.LAVA_BUCKET)){
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "SORRY YOU CANNOT PLACE LAVA BEFORE THE GRACE PERIOD IS OVER");
        } else {
            e.setCancelled(false);
        }
    }

}
