package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.Math;

import java.util.Random;

public class SpawnMushrooms {
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
}
