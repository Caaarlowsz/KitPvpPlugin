package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FeastLogic {

    //ItemStack potion = new ItemStack(Material.POTION);
    //PotionMeta potionMeta = (PotionMeta)potion.getItemMeta();
    //potionMeta.setMainEffect(PotionEffectType.INCREASE_DAMAGE);
    //potion.setItemMeta(potionMeta);

    // COMMON SPAWNS IN FEAST
    static final ItemStack common[] = {
            //new ItemStack(Material.SPLASH_POTION, 1, (short) 16388), //poison splash
            //new ItemStack(Material.SPLASH_POTION, 1, (short) 8201), //strength splash
            //new ItemStack(Material.POTION, 1, (short) 8201), //strength
            new ItemStack(Material.LAVA_BUCKET, 1),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 1),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 2),
            new ItemStack(Material.MUSHROOM_STEW, 1),
            new ItemStack(Material.GOLDEN_APPLE, 1),
            new ItemStack(Material.TNT, 3),
            new ItemStack(Material.FLINT_AND_STEEL, 1),
            new ItemStack(Material.BOW, 1),
            new ItemStack(Material.ARROW, 16),
            new ItemStack(Material.IRON_SWORD, 1),
            new ItemStack(Material.IRON_HELMET, 1),
            new ItemStack(Material.IRON_CHESTPLATE, 1),
            new ItemStack(Material.IRON_LEGGINGS, 1),
            new ItemStack(Material.IRON_BOOTS, 1),
            new ItemStack(Material.COOKED_PORKCHOP, 5)
    };

    // UNCOMMON SPAWNS IN FEAST
    static final ItemStack uncommon[] = {
            new ItemStack(Material.DIAMOND_BOOTS, 1),
            new ItemStack(Material.DIAMOND_LEGGINGS, 1),
            new ItemStack(Material.DIAMOND_CHESTPLATE, 1),
            new ItemStack(Material.DIAMOND_HELMET, 1),
            new ItemStack(Material.DIAMOND_SWORD, 1),
            new ItemStack(Material.GOLDEN_APPLE, 3),
            new ItemStack(Material.MUSHROOM_STEW, 4),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 4),
            new ItemStack(Material.COOKED_BEEF, 8)
    };

    // RARE SPAWNS IN FEAST
    static final ItemStack rare[] = {
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1),
            new ItemStack(Material.GOLDEN_APPLE, 16),
            new ItemStack(Material.MUSHROOM_STEW, 8),
            new ItemStack(Material.NETHERITE_INGOT, 1),
            new ItemStack(Material.DIAMOND_HOE, 64)
    };
    static Random rng = new Random();
    static final int spawnX = GameVariables.world.getSpawnLocation().getBlockX();
    static final int spawnZ = GameVariables.world.getSpawnLocation().getBlockZ();

    static int min_x = spawnX - GameVariables.WORLDSIZE/2 + 100;
    static int max_x = spawnX + GameVariables.WORLDSIZE/2 - 100;
    static int min_z = spawnZ - GameVariables.WORLDSIZE/2 + 100;
    static int max_z = spawnZ + GameVariables.WORLDSIZE/2 - 100;

    public static int feast_center_x = min_x + rng.nextInt(Math.abs(min_x - max_x));
    public static int feast_center_z = min_z + rng.nextInt(Math.abs(min_z - max_z));
    public static int feast_center_y = GameVariables.world.getHighestBlockYAt(feast_center_x, feast_center_z) + 1;

    static World w = GameVariables.world;
    static int r = 15;

    public static void SpawnFeast() {
        Chat.SGPvPMessage(String.format(ChatColor.GOLD + "" + ChatColor.BOLD + "FEAST HAS SPAWNED AT: X:%d Y:%d Z:%d", feast_center_x, feast_center_y, feast_center_z));
        boolean flag = false;
        SpawnFeastPlatform();

        //Place chests
        for (int i = feast_center_x - 2; i <= feast_center_x + 2; i++) {
            for (int j = feast_center_z - 2; j <= feast_center_z + 2; j++) {
                flag = !flag;
                if (!flag) continue;
                Location chest = new Location(w, i, feast_center_y, j);
                chest.getBlock().setType(Material.CHEST); //spawn chest

                //Fill chest
                Chest c = (Chest) chest.getBlock().getState();
                final Inventory inventory = c.getBlockInventory();
                inventory.setItem(rng.nextInt(7), generateItem());
                inventory.setItem(7 + rng.nextInt(7), generateItem());
                inventory.setItem(14 + rng.nextInt(7), generateItem());
                inventory.setItem(21 + rng.nextInt(6), generateItem());
            }
        }

        //Place enchanting table
        Location enchanting_table = new Location(w, feast_center_x, feast_center_y, feast_center_z);
        enchanting_table.getBlock().setType(Material.ENCHANTING_TABLE);
    }

    public static void SpawnFeastPlatform() {
        GameVariables.feastPlatformSpawned = true;
        GameVariables.feastLocation = new Location(w, feast_center_x, feast_center_y, feast_center_z);
        for (int i = feast_center_x - r; i <= feast_center_x + r; i++) {
            for (int j = feast_center_z - r; j <= feast_center_z + r; j++) {
                Location grass = new Location(w, i, feast_center_y-1, j);
                grass.getBlock().setType(Material.GRASS_BLOCK); //spawn grass
                for (int k = 0; k < 10; k++) {
                    Location air = new Location(w, i, feast_center_y + k, j);
                    air.getBlock().setType(Material.AIR);
                }
            }
        }
    }

    public static ItemStack generateItem() {

        int rarity = rng.nextInt(100);

        if (rarity < 80) {
            return common[rng.nextInt(common.length)];
        } else if (rarity < 99) {
            return uncommon[rng.nextInt(uncommon.length)];
        } else {
            return rare[rng.nextInt(rare.length)];
        }
    }

}
