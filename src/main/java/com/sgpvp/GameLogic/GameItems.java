package com.sgpvp.GameLogic;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameItems {
    public static List<ItemStack> getChemistPotions() {
        List<ItemStack> potions = new ArrayList<ItemStack>();
        potions.add(getSplashDamagePotion(true));
        potions.add(getSplashPoisonPotion(true));
        potions.add(getSplashWeaknessPotion());
        return potions;
    }
    public static ItemStack getSplashDamagePotion(boolean upgraded) {
        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        PotionData pd = new PotionData(PotionType.INSTANT_DAMAGE, false, upgraded);
        pm.setBasePotionData(pd);
        potion.setItemMeta(pm);
        return potion;
    }
    public static ItemStack getSplashPoisonPotion(boolean upgraded) {
        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        PotionData pd = new PotionData(PotionType.POISON, false, upgraded);
        pm.setBasePotionData(pd);
        potion.setItemMeta(pm);
        return potion;
    }
    public static ItemStack getSplashWeaknessPotion() {
        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        PotionData pd = new PotionData(PotionType.WEAKNESS, false, false);
        pm.setBasePotionData(pd);
        potion.setItemMeta(pm);
        return potion;
    }
    public static ItemStack getGlassBow() {
        ItemStack glassBow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = glassBow.getItemMeta();
        bowMeta.setDisplayName(ChatColor.WHITE + "Glass Bow");
        bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
        if (bowMeta instanceof Damageable)
            ((Damageable) bowMeta).damage(100);
        glassBow.setItemMeta(bowMeta);
        glassBow.setDurability((short) 0);
        return glassBow;
    }
    public static ItemStack getPhantomFeather() {
        ItemStack phantomFeather = new ItemStack(Material.FEATHER, 1);
        ItemMeta phantomMeta = phantomFeather.getItemMeta();
        phantomMeta.setDisplayName(ChatColor.WHITE + "Phantom Feather");
        phantomFeather.setItemMeta(phantomMeta);
        return phantomFeather;
    }

    public static ItemStack getMilkmanBucket() {
        ItemStack milk_bucket = new ItemStack(Material.MILK_BUCKET, 1);
        ItemMeta milk_bucket_data = milk_bucket.getItemMeta();
        milk_bucket_data.setDisplayName(ChatColor.BOLD + "The Milkman's Bucket Of Milk");
        milk_bucket_data.setLore(Arrays.asList("Gives Speed 1", "Gives Regeneration 1", "Gives Fire Resistant 1"));
        milk_bucket.setItemMeta(milk_bucket_data);
        return milk_bucket;
    }
    public static ItemStack getKangarooFoot() {
        ItemStack kangarooFoot = new ItemStack(Material.RABBIT_FOOT, 1);
        ItemMeta kangarooMeta = kangarooFoot.getItemMeta();
        kangarooMeta.setDisplayName(ChatColor.BOLD + "Kangaroo Foot");
        kangarooMeta.setLore(Arrays.asList("Launch yourself into the air!"));
        kangarooFoot.setItemMeta(kangarooMeta);
        return kangarooFoot;
    }
    public static ItemStack getThorAxe() {
        ItemStack thorAxe = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta thorAxeMeta = thorAxe.getItemMeta();
        thorAxeMeta.setDisplayName(ChatColor.BOLD + "Thor's Hammer");
        thorAxeMeta.setLore(Arrays.asList("Smite your foes"));
        thorAxe.setItemMeta(thorAxeMeta);
        return thorAxe;
    }
    public static ItemStack getTimeClock() {
        ItemStack timeClock = new ItemStack(Material.CLOCK);
        ItemMeta timeClockMeta = timeClock.getItemMeta();
        timeClockMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Wizard Clock");
        timeClockMeta.addEnchant(Enchantment.CHANNELING, 10, true);
        timeClockMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "This used to belong to one of the greatest wizards of all time...\n",ChatColor.WHITE + "Current Owner: ^$%#&"));
        timeClockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        timeClock.setItemMeta(timeClockMeta);
        return timeClock;
    }
    public static ItemStack getMonkStaff() {
        ItemStack monkStaff = new ItemStack(Material.BLAZE_ROD);
        ItemMeta monkStaffMeta = monkStaff.getItemMeta();
        monkStaffMeta.setDisplayName(ChatColor.RED + "Monk Staff");
        monkStaffMeta.setLore(Arrays.asList(ChatColor.RED + "Khakkhara"));
        monkStaff.setItemMeta(monkStaffMeta);
        return monkStaff;
    }
    public static ItemStack getSwitcherBalls(int n) {
        String switcherBallName = ChatColor.translateAlternateColorCodes ('&', "&b&lSwitcher Balls");
        ItemStack switcherBalls = new ItemStack(Material.SNOWBALL, n);
        ItemMeta meta = switcherBalls.getItemMeta();
        meta.setDisplayName(switcherBallName);
        switcherBalls.setItemMeta(meta);
        return switcherBalls;
    }
}
