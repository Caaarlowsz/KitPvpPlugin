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
    double max;

    // Quest Obj
    public Quest(Player player, String title, BarColor color, BarStyle style, String activeTag, double max) {
        this.questBar = Bukkit.createBossBar(title, color, style);
        this.questBar.addPlayer(player);
        this.questBar.setProgress(0d);
        this.tag = activeTag;
        this.max = max;
    }

    // ADDS To Quest Bar
    public void increment(double addToQuestBar){
        questBar.setProgress(questBar.getProgress() + (addToQuestBar/max));
    }

    // Quest (Easy) Rewards
    public static ItemStack easyRandomQuestReward(Player player){

        String rewardMessage;
        ItemStack item = new ItemStack(new ItemStack(Material.AIR));
        Random random = new Random();
        int rewardItems = 1;
        int rng = random.nextInt(rewardItems);

        if(rng == 0) {
            item = new ItemStack(Material.IRON_INGOT, 3);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);

            rewardMessage = ChatColor.BOLD + "QUEST REWARD: 3 Iron Ingots";
            Chat.SGPvPMessage(player, rewardMessage);
        }

        return new ItemStack(item);
    }

    // Quest (Medium) Rewards
    public static ItemStack mediumRandomQuestReward(Player player){

        String rewardMessage;
        ItemStack item = new ItemStack(new ItemStack(Material.AIR));
        Random random = new Random();
        int rewardItems = 4;
        int rng = random.nextInt(rewardItems);

        if(rng == 0) {
            item = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);
            rewardMessage = ChatColor.BOLD + "QUEST REWARD: Iron Chest Plate";
            Chat.SGPvPMessage(player, rewardMessage);
        }
        if(rng == 1) {
            item = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);
            rewardMessage = ChatColor.BOLD + "QUEST REWARD: Iron Leggings";
            Chat.SGPvPMessage(player, rewardMessage);
        }
        if(rng == 2) {
            item = new ItemStack(Material.IRON_HELMET, 1);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);
            rewardMessage = ChatColor.BOLD + "QUEST REWARD: Iron Helmet";
            Chat.SGPvPMessage(player, rewardMessage);
        }
        if(rng == 3) {
            item = new ItemStack(Material.IRON_BOOTS, 1);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);
            rewardMessage = ChatColor.BOLD + "QUEST REWARD: Iron Boots";
            Chat.SGPvPMessage(player, rewardMessage);
        }

        return new ItemStack(item);
    }

    // Quest (Hard) Rewards
    public static ItemStack hardRandomQuestReward(Player player){

        ItemStack item = new ItemStack(Material.AIR);
        String rewardMessage;

        Random random = new Random();
        int rewardItems = 4;
        int rng = random.nextInt(rewardItems);

        if(rng == 0){

            item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);
            rewardMessage = ChatColor.AQUA + "QUEST REWARD: Diamond Chest Plate";
            Chat.SGPvPMessage(player, rewardMessage);
        }
        if(rng == 1){
            item = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);
            rewardMessage = ChatColor.AQUA + "QUEST REWARD: Diamond Leggings Plate";
            Chat.SGPvPMessage(player, rewardMessage);

        }
        if(rng == 2){
            item = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemMeta item_Meta = item.getItemMeta();
            item.setItemMeta(item_Meta);
            rewardMessage = ChatColor.AQUA + "QUEST REWARD: Diamond Helmet Plate";
            Chat.SGPvPMessage(player, rewardMessage);

        }

        return new ItemStack(item);

    }

    // Quest (Exotic) Rewards
    public static ItemStack exoticRandomQuestReward(Player player){

        ItemStack item = new ItemStack(Material.AIR);
        String rewardMessage;

        Random random = new Random();
        int rewardItems = 5;
        int rng = random.nextInt(rewardItems);
        if(rng == 0){
            item = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
            ItemMeta item_meta = item.getItemMeta();
            item.setItemMeta(item_meta);
            rewardMessage = ChatColor.LIGHT_PURPLE + "QUEST REWARD: Netherite Chest Plate";
            Chat.SGPvPMessage(player, rewardMessage);
        }
        if(rng == 1){
            item = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
            ItemMeta item_meta = item.getItemMeta();
            item.setItemMeta(item_meta);
            rewardMessage = ChatColor.LIGHT_PURPLE + "QUEST REWARD: Netherite Leggings";
            Chat.SGPvPMessage(player, rewardMessage);
        }
        if(rng == 2){
            item = new ItemStack(Material.NETHERITE_HELMET, 1);
            ItemMeta item_meta = item.getItemMeta();
            item.setItemMeta(item_meta);
            rewardMessage = ChatColor.LIGHT_PURPLE + "QUEST REWARD: Netherite Helmet";
            Chat.SGPvPMessage(player, rewardMessage);
        }
        if(rng == 3){
            item = new ItemStack(Material.NETHERITE_BOOTS, 1);
            ItemMeta item_meta = item.getItemMeta();
            item.setItemMeta(item_meta);
            rewardMessage = ChatColor.LIGHT_PURPLE + "QUEST REWARD: Netherite Boots";
            Chat.SGPvPMessage(player, rewardMessage);
        }

        return new ItemStack(item);
    }

    //Getters
    public double getProgress(){ return questBar.getProgress(); }
    public String getActiveTag() { return tag; }
    public double getMax(){return max; }

    //Setters
    public void setBarStyle(BarStyle barStyle){ questBar.setStyle(barStyle); }
    public void setBarColor(BarColor barColor){ questBar.setColor(barColor); }
    public void setProgress(double set){ questBar.setProgress(set); }
    public void setTitle(String title){ questBar.setTitle(title); }
    public void setTag(String setTag){ tag = setTag; }
    public void setMax(double goalNumber){ max = goalNumber; }

    public void questVisible(boolean visible){questBar.setVisible(visible);}
}
