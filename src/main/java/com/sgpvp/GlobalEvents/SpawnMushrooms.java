package com.sgpvp.GlobalEvents;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Random;

public class SpawnMushrooms implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        if (!e.isNewChunk()) return;

        Random rng = new Random();

        int chunkX = e.getChunk().getX();
        int chunkZ = e.getChunk().getZ();

        int min_x = chunkX*16;
        int max_x = min_x+16;
        int min_z = chunkZ*16;
        int max_z = min_z+16;

        World w = GameVariables.world;

        for (int i = min_x; i < max_x; i++) {
            for (int k = min_z; k < max_z; k++) {
                int j = w.getHighestBlockYAt(i, k);
                if (!((new Location(w, i, j, k)).getBlock().getType().equals(Material.GRASS_BLOCK) )) continue;
                if (rng.nextInt(50) == 0) {
                    Location mushroom = new Location(w, i, j+1, k);
                    if (rng.nextInt(2)==0)  mushroom.getBlock().setType(Material.RED_MUSHROOM);
                    else mushroom.getBlock().setType(Material.BROWN_MUSHROOM);
                }
            }
        }
    }
    public static void spawnInitialMushrooms() {
        Random rng = new Random();
        World w = GameVariables.world;

        int min_x = GameVariables.WorldSpawn.getBlockX() - 256;
        int max_x = GameVariables.WorldSpawn.getBlockX() + 256;
        int min_z = GameVariables.WorldSpawn.getBlockZ() - 256;
        int max_z = GameVariables.WorldSpawn.getBlockZ() + 256;

        for (int i = min_x; i < max_x; i++) {
            for (int k = min_z; k < max_z; k++) {
                int j = w.getHighestBlockYAt(i, k);
                if (!((new Location(w, i, j, k)).getBlock().getType().equals(Material.GRASS_BLOCK) )) continue;
                if (rng.nextInt(50) == 0) {
                    Location mushroom = new Location(w, i, j+1, k);
                    if (rng.nextInt(2)==0)  mushroom.getBlock().setType(Material.RED_MUSHROOM);
                    else mushroom.getBlock().setType(Material.BROWN_MUSHROOM);
                }
            }
        }

    }
}
