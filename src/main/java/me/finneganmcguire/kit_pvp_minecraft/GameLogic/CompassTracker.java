package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class CompassTracker implements Listener {

    private Kit_PvP_Minecraft main;

    public void CompassTracker(Kit_PvP_Minecraft main){
        this.main = main;
    }

    public Location getNearestPlayertoSelf(Player p){
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        int a = 0;
        while (a < players.size()) {
            if (players.get(a) == p) {
                players.remove(a);
            }
            else if (players.get(a).getGameMode() == GameMode.SPECTATOR) {
                players.remove(a);
            } else {
                a++;
            }
        }

        if (players.size() == 0) return new Location(Kit_PvP_Minecraft.world, 0, 0, 0);

        Location p_loc = p.getLocation();
        Player near = players.get(0);

        for (int i = 1; i < players.size(); i++) {
            if (distance(players.get(i).getLocation(), p_loc) < distance(near.getLocation(), p_loc)) {
                near = players.get(i);
            }
        }
        p.sendMessage("Now tracking " + near.getName());
        return near.getLocation();
    }

    @EventHandler
    public void rightClickCompass(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        boolean holdingCompass = player.getInventory().getItemInMainHand().getType() == Material.COMPASS;
        if(holdingCompass){
            Location l = getNearestPlayertoSelf(player);
            player.setCompassTarget(l);
        }
    }

    public int distance(Location a, Location b) {
        int x1 = a.getBlockX(), x2 = b.getBlockX(), y1 = a.getBlockY(), y2 = b.getBlockY();
        return (int)(Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
    }
}