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
import java.util.Collections;
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
        phantomMeta.setLore(Arrays.asList("Usage: Left OR Right Click To Fly"));
        phantomMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        phantomMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        phantomFeather.setItemMeta(phantomMeta);
        return phantomFeather;
    }

    public static ItemStack getMilkmanBucket() {
        ItemStack milk_bucket = new ItemStack(Material.MILK_BUCKET, 1);
        ItemMeta milk_bucket_data = milk_bucket.getItemMeta();
        milk_bucket_data.setDisplayName(ChatColor.BOLD + "The Milkman's Bucket Of Milk");
        milk_bucket_data.setLore(Arrays.asList("Gives Speed 1", "Gives Regeneration 1", "Gives Fire Resistant 1\n", "Works for any milk bucket"));
        milk_bucket_data.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        milk_bucket_data.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        milk_bucket.setItemMeta(milk_bucket_data);
        return milk_bucket;
    }
    public static ItemStack getKangarooFoot() {
        ItemStack kangarooFoot = new ItemStack(Material.RABBIT_FOOT, 1);
        ItemMeta kangarooMeta = kangarooFoot.getItemMeta();
        kangarooMeta.setDisplayName(ChatColor.BOLD + "Kangaroo Foot");
        kangarooMeta.setLore(Arrays.asList("Usage: Holding Right Click Allows You To Jump Higher"));
        kangarooMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        kangarooFoot.setItemMeta(kangarooMeta);
        return kangarooFoot;
    }
    public static ItemStack getThorAxe() {
        ItemStack thorAxe = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta thorAxeMeta = thorAxe.getItemMeta();
        thorAxeMeta.setDisplayName(ChatColor.BOLD + "Thor's Hammer");
        thorAxeMeta.setLore(Arrays.asList("Usage: Left OR Right Click To Activate Lightning"));
        thorAxeMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        thorAxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        thorAxe.setItemMeta(thorAxeMeta);
        return thorAxe;
    }
    public static ItemStack getTimeClock() {
        ItemStack timeClock = new ItemStack(Material.CLOCK);
        ItemMeta timeClockMeta = timeClock.getItemMeta();
        timeClockMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Wizard Clock");
        timeClockMeta.addEnchant(Enchantment.CHANNELING, 10, true);
        timeClockMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        timeClockMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "This used to belong to one of the greatest wizards of all time...\n", "Left OR Right Click To Freeze Players Around You"));
        timeClockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        timeClock.setItemMeta(timeClockMeta);
        return timeClock;
    }
    public static ItemStack getMonkStaff() {
        ItemStack monkStaff = new ItemStack(Material.BLAZE_ROD);
        ItemMeta monkStaffMeta = monkStaff.getItemMeta();
        monkStaffMeta.setDisplayName(ChatColor.RED + "Monk Staff");
        monkStaffMeta.setLore(Arrays.asList(ChatColor.RED + "Khakkhara \n", "Usage: Left OR Right Click On An Enemy To Put The Item They Are Holding Back Into Their Inventory"));
        monkStaffMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        monkStaffMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        monkStaff.setItemMeta(monkStaffMeta);
        return monkStaff;
    }
    public static ItemStack getSwitcherBalls(int n) {
        String switcherBallName = ChatColor.translateAlternateColorCodes ('&', "&b&lSwitcher Balls");
        ItemStack switcherBalls = new ItemStack(Material.SNOWBALL, n);
        ItemMeta meta = switcherBalls.getItemMeta();
        meta.setDisplayName(switcherBallName);
        meta.setLore(Arrays.asList("Swaps Position With An Entity On Hit"));
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        switcherBalls.setItemMeta(meta);
        return switcherBalls;
    }
    public static ItemStack getCultivatorHoe() {
        ItemStack cultivatorHoe = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta cultivatorHoeMeta = cultivatorHoe.getItemMeta();
        cultivatorHoeMeta.setDisplayName(ChatColor.BOLD + "The Cultivator's Powerful Plow");
        cultivatorHoeMeta.setLore(Collections.singletonList("Instantly grows any crop or sapling"));
        cultivatorHoeMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        cultivatorHoe.setItemMeta(cultivatorHoeMeta);
        return cultivatorHoe;
    }
    public static ItemStack getGroundhogShovel() {
        ItemStack groundhogShovel = new ItemStack(Material.STONE_SHOVEL, 3);
        ItemMeta groundhogShovelMeta = groundhogShovel.getItemMeta();
        groundhogShovelMeta.setDisplayName(ChatColor.translateAlternateColorCodes ('&', "&a&lGroundhog's Spade"));
        groundhogShovelMeta.setLore(Collections.singletonList("Usage: Left OR Right Click To Burrow Your Way Underground"));
        groundhogShovelMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        groundhogShovelMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        groundhogShovel.setItemMeta(groundhogShovelMeta);
        return groundhogShovel;
    }

}
