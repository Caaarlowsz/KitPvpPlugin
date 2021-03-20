package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.Math;

import java.util.Random;

public class FeastLogic {

    static final ItemStack common[] = {
            new ItemStack(Material.SPLASH_POTION, 1, (short) 16388), //poison splash
            new ItemStack(Material.SPLASH_POTION, 1, (short) 8201), //strength splash
            new ItemStack(Material.POTION, 1, (short) 8201), //strength
            new ItemStack(Material.LAVA_BUCKET, 1),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 1),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 2),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 3),
            new ItemStack(Material.MUSHROOM_STEW, 1),
            new ItemStack(Material.GOLDEN_APPLE, 1),
            new ItemStack(Material.TNT, 3),
            new ItemStack(Material.FLINT_AND_STEEL, 1),
            new ItemStack(Material.IRON_SWORD, 1),
            new ItemStack(Material.IRON_HELMET, 1),
            new ItemStack(Material.IRON_CHESTPLATE, 1),
            new ItemStack(Material.IRON_LEGGINGS, 1),
            new ItemStack(Material.IRON_BOOTS, 1),
            new ItemStack(Material.DIAMOND_HOE, 20)
    };
    static final ItemStack uncommon[] = {
            new ItemStack(Material.DIAMOND_BOOTS, 1),
            new ItemStack(Material.DIAMOND_LEGGINGS, 1),
            new ItemStack(Material.DIAMOND_CHESTPLATE, 1),
            new ItemStack(Material.DIAMOND_HELMET, 1),
            new ItemStack(Material.DIAMOND_SWORD, 1),
            new ItemStack(Material.GOLDEN_APPLE, 3),
            new ItemStack(Material.MUSHROOM_STEW, 4),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 16)
    };
    static final ItemStack rare[] = {
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1),
            new ItemStack(Material.GOLDEN_APPLE, 16),
            new ItemStack(Material.MUSHROOM_STEW, 8),
            new ItemStack(Material.NETHERITE_INGOT, 1)
    };
    static Random rng = new Random();
    static final int spawnX = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockX();
    static final int spawnZ = Kit_PvP_Minecraft.world.getSpawnLocation().getBlockZ();

    static int min_x = spawnX - Kit_PvP_Minecraft.WORLDSIZE/2 + 100;
    static int max_x = spawnX + Kit_PvP_Minecraft.WORLDSIZE/2 - 100;
    static int min_z = spawnZ - Kit_PvP_Minecraft.WORLDSIZE/2 + 100;

    public static void SpawnFeast(World w) {
        int feast_center_x = min_x + rng.nextInt(Math.abs(min_x - max_x));
        int feast_center_z = min_z + rng.nextInt(Math.abs(min_x - max_x));
        int feast_center_y = w.getHighestBlockYAt(feast_center_x, feast_center_z) + 1;
        Bukkit.broadcastMessage(String.format(ChatColor.GOLD + "" + ChatColor.GOLD + "Feast Spawned At %d %d %d", feast_center_x, feast_center_y, feast_center_z));
        boolean flag = false;

        //Place chests
        for (int i = feast_center_x - 2; i <= feast_center_x + 2; i++) {
            for (int j = feast_center_z - 2; j <= feast_center_z + 2; j++) {
                Location chest = new Location(w, i, feast_center_y, j);
                chest.getBlock().setType(Material.AIR); //delete block
                flag = !flag;
                if (!flag) continue;
                chest.getBlock().setType(Material.CHEST); //spawn chest

                //Fill chest
                Chest c = (Chest) chest.getBlock().getState();
                final Inventory inventory = c.getBlockInventory();
                inventory.setItem(rng.nextInt(9), generateItem());
                inventory.setItem(9 + rng.nextInt(9), generateItem());
                inventory.setItem(18 + rng.nextInt(9), generateItem());
            }
        }

        //Place enchanting table
        Location enchanting_table = new Location(w, feast_center_x, feast_center_y, feast_center_z);
        enchanting_table.getBlock().setType(Material.ENCHANTING_TABLE);

        //Place platform below chests
        for (int i = feast_center_x - 2; i <= feast_center_x + 2; i++) {
            for (int j = feast_center_z - 2; j <= feast_center_z + 2; j++) {
                Location chest = new Location(w, i, feast_center_y-1, j);
                chest.getBlock().setType(Material.GRASS_BLOCK); //spawn grass
            }
        }

        //Remove blocks above chests
        for (int i = feast_center_x - 2; i <= feast_center_x + 2; i++) {
            for (int j = feast_center_z - 2; j <= feast_center_z + 2; j++) {
                Location chest = new Location(w, i, feast_center_y+1, j);
                chest.getBlock().setType(Material.AIR); //spawn grass
            }
        }

    }
    public static ItemStack generateItem() {

        int rarity = rng.nextInt(100);

        if (rarity < 70) {
            return common[rng.nextInt(common.length)];
        } else if (rarity < 98) {
            return uncommon[rng.nextInt(uncommon.length)];
        } else {
            return rare[rng.nextInt(rare.length)];
        }
    }

}
