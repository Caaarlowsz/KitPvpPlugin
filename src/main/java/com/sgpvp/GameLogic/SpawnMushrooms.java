package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Random;

public class SpawnMushrooms implements Listener {
    static Random rng = new Random();

    public static void spawnMushrooms(World w) {

        int min_x = GameVariables.WorldBounds.MINX;
        int max_x = GameVariables.WorldBounds.MAXX;
        int min_z = GameVariables.WorldBounds.MINZ;
        int max_z = GameVariables.WorldBounds.MAXZ;

        for (int i = min_x; i < max_x; i++) {
            for (int k = min_z; k < max_z; k++) {
                int j = w.getHighestBlockYAt(i, k);
                if (!((new Location(w, i, j, k)).getBlock().getType().equals(Material.GRASS_BLOCK) )) continue;
                if (rng.nextInt(50) == 0) {
                    if (rng.nextInt(2)==0) {
                        Location redShroomer = new Location(w, i, j+1, k);
                        redShroomer.getBlock().setType(Material.RED_MUSHROOM);
                    } else {
                        Location brownShroomer = new Location(w, i, j+1, k);
                        brownShroomer.getBlock().setType(Material.BROWN_MUSHROOM);
                    }
                }
            }
        }
    }
    /*
    @Override
    public void onChunkLoad(ChunkLoadEvent e) {
        if (!e.isNewChunk()) return;
        int chunkX = e.getChunk().getX();
        int chunkZ = e.getChunk().getZ();
        e.getChunk().
    }*/
}
