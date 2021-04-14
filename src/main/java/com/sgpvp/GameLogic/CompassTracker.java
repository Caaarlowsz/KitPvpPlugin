package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class CompassTracker implements Listener {
    private int teamingRadius = 20;

    public Location getNearestPlayertoSelf(Player p){
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        int a = 0;
        while (a < players.size()) {
            if (players.get(a) == p) {
                players.remove(a);
            }
            else if (players.get(a).getGameMode() == GameMode.SPECTATOR) {
                players.remove(a);
            } else if (distance(p.getLocation(), players.get(a).getLocation()) < teamingRadius) {
                players.remove(a);
            } else {
                a++;
            }
        }

        if (players.size() == 0) {
            Chat.SGPvPMessage(p, "No players found, compass now pointing at spawn" );
            return GameVariables.WorldSpawn;
        }

        Location p_loc = p.getLocation();
        Player near = players.get(0);

        for (int i = 1; i < players.size(); i++) {
            if (distance(players.get(i).getLocation(), p_loc) < distance(near.getLocation(), p_loc)) {
                near = players.get(i);
            }
        }
        Chat.SGPvPMessage(p, "Now tracking " + near.getName());
        return near.getLocation();
    }

    @EventHandler
    public void rightClickCompass(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        boolean holdingCompass = player.getInventory().getItemInMainHand().getType() == Material.COMPASS;
        boolean leftClick = event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK);
        boolean rightClick = event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK);

        if(holdingCompass && rightClick){
            Location l = getNearestPlayertoSelf(player);
            player.setCompassTarget(l);
        } else if (holdingCompass && GameVariables.feastPlatformSpawned && leftClick) {
            Location l = GameVariables.feastLocation;
            Chat.SGPvPMessage(player, "Compass now pointing at feast location");
            player.setCompassTarget(l);
        }
    }

    public int distance(Location a, Location b) {
        int x1 = a.getBlockX(), x2 = b.getBlockX(), y1 = a.getBlockY(), y2 = b.getBlockY();
        return (int)(Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
    }
}