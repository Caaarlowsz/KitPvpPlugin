package com.sgpvp.GlobalEvents;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractions implements Listener {

    // Players can do Actions and Interactions
    public static boolean playersCanDropItems = false;
    public static boolean playerCanDropLava = false;
    public static boolean playerCanTakeDamage = false;

    @EventHandler
    public void checkIfCanDropItems(PlayerDropItemEvent e) {
        if (!playersCanDropItems) {
            e.getItemDrop().setPickupDelay(0); // Instant
        } else {
            e.getItemDrop().setPickupDelay(25); // Normal
        }
    }

    @EventHandler
    public void lavaPlaceEvent(PlayerBucketEmptyEvent e) {
        if (!playerCanDropLava && e.getBucket().equals(Material.LAVA_BUCKET)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Cannot place lava before grace period");
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void playerTakeDamage(EntityDamageEvent p) {
        if (!playerCanTakeDamage) {
            p.setCancelled(true);
        } else {
            p.setCancelled(false);
        }
    }

    @EventHandler
    public void flintAndSteelEvent(PlayerInteractEvent e){
        try{
            if (!playerCanDropLava && e.getItem().getType().equals(Material.FLINT_AND_STEEL)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.DARK_RED + "Cannot use flint and steel before grace period ends");
            } else {
                e.setCancelled(false);
            }
        } catch (Exception exception){
            return;
        }
    }

}