package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CompassTracker implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Player target = getNearest(player, Kit_PvP_Minecraft.WORLDSIZE);
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getItemInHand().getType() == Material.COMPASS){
            player.setCompassTarget(target.getLocation());
        }
    }
    private Player getNearest(Player player, int range) {
        double distance = Double.POSITIVE_INFINITY; // To make sure the first
        // player checked is closest
        Player target = null;
        for (Entity entity : player.getNearbyEntities(200, 200, 200)) {
            if (!(entity instanceof Player))
                continue;
            if(entity == player) continue; //Added this check so you don't target yourself.
            double distanceto = player.getLocation().distance(entity.getLocation());
            if (distanceto > distance)
                continue;
            distance = distanceto;
            target = (Player) entity;
        }
        return target;
    }
}
