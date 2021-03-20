package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.Math;

import java.util.Locale;
import java.util.Random;

public class FeastLogic {
    static final Material items[] = {Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET,
            Material.DIAMOND_HOE, Material.DIAMOND_SWORD, Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE,
            Material.IRON_HELMET, Material.IRON_SWORD};
    static Random rng = new Random();
    static final int spawnX = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockX();
    static final int spawnZ = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockZ();

    static int min_x = spawnX - Kit_PvP_Minecraft.WORLDSIZE/2 + 100;
    static int max_x = spawnX + Kit_PvP_Minecraft.WORLDSIZE/2 - 100;
    static int min_z = spawnZ - Kit_PvP_Minecraft.WORLDSIZE/2 + 100;
    static int max_z = spawnZ + Kit_PvP_Minecraft.WORLDSIZE/2 - 100;

    public static void SpawnFeast(World w) {
        int feast_center_x = min_x + rng.nextInt(Math.abs(min_x - max_x));
        int feast_center_z = min_z + rng.nextInt(Math.abs(min_x - max_x));
        int feast_center_y = w.getHighestBlockYAt(feast_center_x, feast_center_z) + 1;
        Bukkit.broadcastMessage(String.format(ChatColor.GOLD + "" + ChatColor.GOLD + "Feast Spawned At %d %d %d", feast_center_x, feast_center_y, feast_center_z));
        boolean flag = false;

        for (int i = feast_center_x - 2; i <= feast_center_x + 2; i++) {
            for (int j = feast_center_z - 2; j <= feast_center_z + 2; j++) {
                Location chest = new Location(w, (double)i, (double)feast_center_y, (double)j);
                chest.getBlock().setType(Material.AIR); //delete block
                flag = !flag;
                if (!flag) continue;
                chest.getBlock().setType(Material.CHEST); //spawn chest
                Chest c = (Chest) chest.getBlock().getState();
                for (int k = 0; k < 3; k++) {
                    c.getBlockInventory().addItem(new ItemStack(items[rng.nextInt(items.length)]));;
                }

            }
        }

        for (int i = feast_center_x - 2; i <= feast_center_x + 2; i++) {
            for (int j = feast_center_z - 2; j <= feast_center_z + 2; j++) {
                Location chest = new Location(w, (double)i, (double)feast_center_y-1, (double)j);
                chest.getBlock().setType(Material.GRASS_BLOCK); //spawn grass
            }
        }
        for (int i = feast_center_x - 2; i <= feast_center_x + 2; i++) {
            for (int j = feast_center_z - 2; j <= feast_center_z + 2; j++) {
                Location chest = new Location(w, (double)i, (double)feast_center_y+1, (double)j);
                chest.getBlock().setType(Material.AIR); //spawn grass
            }
        }

        Location feast_center = new Location(w, (double)feast_center_x, (double)feast_center_y, (double)feast_center_z);

        feast_center.getBlock().setType(Material.ENCHANTING_TABLE);
    }
}
