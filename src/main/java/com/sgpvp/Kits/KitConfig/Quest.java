package com.sgpvp.Kits.KitConfig;

import com.sgpvp.GameLogic.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Quest {
    BossBar questBar;
    String tag;

    // Quest Obj
    public Quest(Player player, String title, BarColor color, BarStyle style, String activeTag) {
        this.questBar = Bukkit.createBossBar(title, color, style);
        this.questBar.addPlayer(player);
        this.questBar.setProgress(0d);
        this.tag = activeTag;
    }

    // ADDS 0.1 To Quest Bar
    public void increment(double addToQuestBar){
        this.questBar.setProgress(questBar.getProgress() + addToQuestBar);
    }

    // Quest (Easy) Rewards
    public static ItemStack mobQuestReward(){ return new ItemStack(Material.IRON_INGOT, 8); }
    public static ItemStack coalQuestReward(){ return new ItemStack(Material.IRON_INGOT, 8); }
    public static ItemStack bowlQuestReward(){ return new ItemStack(Material.IRON_INGOT, 4); }

    // Quest (Medium) Rewards
    public static ItemStack smeltIronQuestReward(){

        String rewardText = ChatColor.GOLD + "Reward: Iron Chestplate w/ Enchants";
        ItemStack iron_chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta iron_chestplate_meta = iron_chestplate.getItemMeta();
        iron_chestplate_meta.addEnchant(Enchantment.DURABILITY, 2, true);
        iron_chestplate_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        iron_chestplate.setItemMeta(iron_chestplate_meta);
        return new ItemStack(iron_chestplate);
    }

    public static ItemStack killPlayersQuestReward(){

        ItemStack diamond_chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta diamond_chestplate_meta = diamond_chestplate.getItemMeta();
        diamond_chestplate_meta.addEnchant(Enchantment.DURABILITY, 1, true);
        diamond_chestplate_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        diamond_chestplate.setItemMeta(diamond_chestplate_meta);
        return new ItemStack(diamond_chestplate);
    }

    public static ItemStack exoticKillPlayersQuestReward(){

        ItemStack netherite_chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        ItemMeta netherite_chestplate_meta = netherite_chestplate.getItemMeta();
        netherite_chestplate_meta.addEnchant(Enchantment.DURABILITY, 1, true);
        netherite_chestplate_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        netherite_chestplate.setItemMeta(netherite_chestplate_meta);
        return new ItemStack(netherite_chestplate);
    }

    // Quest (Hard) Rewards
    public static ItemStack diamondsQuestReward(){ return new ItemStack(Material.DIAMOND_SWORD, 1); }

    //Getters
    public double getProgress(){return questBar.getProgress();}
    public String getActiveTag() { return tag;}

    //Setters
    public void setBarStyle(BarStyle barStyle){ questBar.setStyle(barStyle); }
    public void setBarColor(BarColor barColor){ questBar.setColor(barColor); }
    public void setProgress(double set){questBar.setProgress(set);}
    public void setTitle(String title){questBar.setTitle(title);}
    public void setTag(String setTag){tag = setTag;}

    public void questVisible(boolean visible){questBar.setVisible(visible);}
}
