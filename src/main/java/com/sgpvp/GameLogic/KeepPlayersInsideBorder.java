package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class KeepPlayersInsideBorder implements Listener {
    @EventHandler
    public void onPlayerMove ( PlayerMoveEvent event ) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        if (!playerInBounds(playerLocation)) player.teleport(GameVariables.WorldSpawn);
    }
    private boolean playerInBounds(Location location) {
        return !(location.getBlockX() > GameVariables.WorldBounds.MAXX ||
                location.getBlockX() < GameVariables.WorldBounds.MINX ||
                location.getBlockY() > GameVariables.WorldBounds.MAXY ||
                location.getBlockY() < GameVariables.WorldBounds.MINY ||
                location.getBlockZ() > GameVariables.WorldBounds.MAXZ ||
                location.getBlockZ() < GameVariables.WorldBounds.MINZ);
    }
}
