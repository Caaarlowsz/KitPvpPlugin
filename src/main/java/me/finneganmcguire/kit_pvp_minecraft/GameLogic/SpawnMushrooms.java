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
    static final int spawnX = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockX();
    static final int spawnZ = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockZ();

    static int min_x = spawnX - Kit_PvP_Minecraft.WORLDSIZE/2;
    static int max_x = spawnX + Kit_PvP_Minecraft.WORLDSIZE/2;
    static int min_z = spawnZ - Kit_PvP_Minecraft.WORLDSIZE/2;
    static int max_z = spawnZ + Kit_PvP_Minecraft.WORLDSIZE/2;

    public static void spawnMushrooms(World w) {

        for (int i = min_x; i < max_x; i++) {
            for (int k = min_z; k < max_z; k++) {
                int j = w.getHighestBlockYAt(i, k);
                //if (!((new Location(w, i, j, k)).getBlock().equals(Material.GRASS_BLOCK) )) continue;
                if (rng.nextInt(50) < 3) {
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
