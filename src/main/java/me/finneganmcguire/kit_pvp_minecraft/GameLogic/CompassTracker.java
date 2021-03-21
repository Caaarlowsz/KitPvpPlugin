package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class CompassTracker implements Listener {

    List<Entity> playersNearbyPlayer;
    double distanceToPlayer = 0.0;

    public String getNearestPlayertoSelf(Player p){
        List<Entity> nearestEntities = p.getNearbyEntities(Kit_PvP_Minecraft.WORLDSIZE, Kit_PvP_Minecraft.WORLDSIZE, Kit_PvP_Minecraft.WORLDSIZE);
        for (int i = 0; i < nearestEntities.size(); i++) {
                if(nearestEntities.get(i).getType().equals(EntityType.PLAYER)){
                    Player player = (Player) nearestEntities.get(i);
                    playersNearbyPlayer.add(player);
                }
        }

        return null;
    }

    @EventHandler
    public void rightClickCompass(PlayerInteractEvent e){
        if(e.getItem().getType().equals(Material.COMPASS)){
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                // When Right Clicking Compass - give nearest player

            }
        }
    }
}
