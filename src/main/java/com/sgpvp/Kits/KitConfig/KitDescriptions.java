package com.sgpvp.Kits.KitConfig;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.HashMap;

public class KitDescriptions {
    private static final HashMap<String, String>descrptions = new HashMap<String, String>(){{
        put("TimeWizard", "The time wizard is a very unique kit and is good when used at the right time\n - Start with a clock, upon right click you freeze all players around you within 7 blocks");
        put("Werewolf", "Werewolf is best when it is night time.\n - You get Nightvision, Speed & Extra Health At Night");
        put("Brawler", "The brawler kit is a great aggressive kit\n - The brawler kit has extra damage when punching with your fist");
        put("Chameleon", "The chameleon kit is great for more passive players\n - You can disguise yourself as any mob\n - Upon taking damage you will be revealed");
        put("Lumberjack", "The lumberjack kit is great for players that like to use block to get height advantages or create structures\n - The lumberjack kit starts with a Efficiency 10 Wooden Axe that is unbreakable");
        put("Grandpa", "The grandpa kit is great at keeping enemies away from you and works well against aggressive players\n - The grandpa kit starts with a knockback 2 stick");
        put("Turtle", "The turtle kit is great at players who like more defensive gameplay.\n - Holding Sneak/Shift Gives You Resistance 2 & Weakness 2");
        put("Milkman", "The milk man is good for having a good source of buffs \n - You start with a milk bucket\n - Drinking milk gives you Speed 1, Regeneration 1, and Fire Resistance \n - Speed ()");
        put("Fireman", "The fireman kit is great when it comes to dealing with lava or fire\n - The fireman kit has immunity to fire and starts with a water bucket");
        put("Pyromancer", "The pyromancer is a very lethal and can be useful in many different situations\n - The Pyromancer starts with a lava bucket as well as flint and steel");
        put("Recycler", "After 3 uses of soup, you get a free soup! :)");
        put("Beastmaster", "The beast master is a very lethal kit and for people that can control wolves wisely\n - Your bones instantly tame wolves\n - The beastmaster starts with 2 Wolf Eggs and 3 Bones ");
        put("Glider", "Glide around the map in an elytra. ");
        put("Blacksmith", "Upgrade your items to the next tier for free! \n - You have an anvil that can upgrade an any item \n - Axes or Swords cannot be upgraded to diamond tier \n - One time use");
        put("Viper", "The viper kit is an aggressive kit \n - The viper kit has a chance to apply poison on every hit");
        put("Vampire", "Player kills fully heal you");
        put("Snail", "You have a chance of giving a player slowness on every hit");
        put("Monk", "You start with a monk staff that upon hitting an enemy puts the item they are holding back into their inventory.");
        put("Assassin", "Holding shift charges an attack, after you are charged hitting a player will do massive amounts of damage.");
        put("Kangaroo", "Start with a Kangaroo Foot and upon right clicking the item, you are able to jump really high");
        put("Bomber", "Killing anything will create an explosion on the thing you killed after death \n You are completely resistant to explosions");
        put("Sunwalker", "Gain a speed buff during the day");
        put("Phantom", "Start with a feather that allows you to fly for 7 seconds when right clicking it \n It starts a cooldown after use");
        put("Stomper", "Instead of taking fall damage, you deal your fall damage to the players around the spot where you hit the ground");
        put("Switcher", "Receive 10 snowballs, upon hitting an enemy with one you swap positions with them");
        put("Thor", "Receive the hammer of Thor! \n Upon right clicking the axe you strike down lighting within a close range of you");
        put("Explorer", "Explore far and wide!");
        put("Adventurer", "Receive Quests and gain Fame and Glory!");
    }};
    private static final HashMap<String, ChatColor> kitcolor = new HashMap<String, ChatColor>(){{
        put("TimeWizard", ChatColor.DARK_PURPLE);
        put("Werewolf", ChatColor.DARK_RED);
        put("Brawler", ChatColor.YELLOW);
        put("Chameleon", ChatColor.GREEN);
        put("Lumberjack", ChatColor.DARK_GREEN);
        put("Grandpa", ChatColor.GRAY);
        put("Turtle", ChatColor.GREEN);
        put("Milkman", ChatColor.AQUA);
        put("Fireman", ChatColor.RED);
        put("Pyromancer", ChatColor.RED);
        put("Recycler", ChatColor.GREEN);
        put("Beastmaster", ChatColor.BLUE);
        put("Glider", ChatColor.DARK_GRAY);
        put("Groundhog", ChatColor.DARK_GRAY);
        put("Hades", ChatColor.DARK_RED);
        put("Demonking", ChatColor.DARK_RED);
        put("Blacksmith", ChatColor.BLACK);
        put("Viper", ChatColor.DARK_AQUA);
        put("Snail", ChatColor.DARK_GREEN);
        put("Vampire", ChatColor.DARK_BLUE);
        put("Monk", ChatColor.YELLOW);
        put("Assassin", ChatColor.GRAY);
        put("Thor", ChatColor.YELLOW);
        put("Explorer", ChatColor.DARK_RED);
        put("Adventurer", ChatColor.BLUE);
    }};

    /*
        List of colors / kits using:
            Black: Blacksmith
            Dark Green: Lumberjack Snail
            Dark Red: Werewolf Hades Demonking
            Dark Gray: Glider Groundhog
            Dark Blue:
            Dark Aqua: Viper
            Dark Purple: TimeWizard
            Gold:
            Green: Chameleon Turtle Recycler
            Yellow: Brawler
            Red: Fireman Pyromancer
            Gray: Grandpa
            Blue: Beastmaster
            Aqua: Milkman
            Light Purple:
            White: RESERVED FOR DELIMITERS []
     */

    private static final HashMap<String, Material> kitItem = new HashMap<String, Material>(){{
        put("Bomber", Material.TNT);
        put("Stomper", Material.NETHERITE_BOOTS);
        put("Beastmaster", Material.WOLF_SPAWN_EGG);
        put("Fireman", Material.WATER_BUCKET);
        put("Milkman", Material.MILK_BUCKET);
        put("Pyromancer", Material.FLINT_AND_STEEL);
        put("Grandpa", Material.STICK);
        put("Chameleon", Material.LEATHER_HORSE_ARMOR);
        put("Brawler", Material.STONE_SWORD);
        put("Lumberjack", Material.OAK_LOG);
        put("Turtle", Material.TURTLE_HELMET);
        put("Glider", Material.ELYTRA);
        put("Phantom", Material.FEATHER);
        put("Assassin", Material.NETHERITE_SWORD);
        put("Sunwalker", Material.MAGMA_CREAM);
        put("Monk", Material.BLAZE_ROD);
        put("Recycler", Material.MUSHROOM_STEW);
        put("Snail", Material.NAUTILUS_SHELL);
        put("Viper", Material.ENDER_EYE);
        put("Kangaroo", Material.RABBIT_FOOT);
        put("Switcher", Material.SNOWBALL);
        put("Chemist", Material.DRAGON_BREATH);
        put("Blacksmith", Material.ANVIL);
        put("TimeWizard", Material.CLOCK);
        put("Thor", Material.WOODEN_AXE);
        put("Vampire", Material.FERMENTED_SPIDER_EYE);
        put("Werewolf", Material.BAT_SPAWN_EGG);
        put("Explorer", Material.MAP);
        put("Cultivator", Material.WHEAT_SEEDS);
        put("Groundhog", Material.STONE_SHOVEL);
        put("Adventurer", Material.MAP);
    }};
    // Method to return kit color
    public static ChatColor color(String kitname) {
        if (kitcolor.containsKey(kitname)) return kitcolor.get(kitname);
        if (kitcolor.containsKey(getKitName(kitname))) return kitcolor.get(getKitName(kitname));
        return ChatColor.GRAY;
    }

    // Method to return kit description
    public static String description(String kitname) {
        if (descrptions.containsKey(kitname)) return descrptions.get(kitname);
        if (descrptions.containsKey(getKitName(kitname))) return descrptions.get(getKitName(kitname));
        return "Description not available, sorry!";
    }
    // Method to return kit description
    public static Material material(String kitname) {
        if (kitItem.containsKey(kitname)) return kitItem.get(kitname);
        if (kitItem.containsKey(getKitName(kitname))) return kitItem.get(getKitName(kitname));
        return Material.GRASS_BLOCK;
    }

    // Method for converting lowercase kitname to uppercase kitname
    private static HashMap<String, String> kitName = new HashMap<>();
    public static String getKitName(String lowername) {
        if (kitName.containsKey(lowername)) return kitName.get(lowername);
        for (String kit : kitcolor.keySet()) {
            kitName.put(kit.toLowerCase(), kit);
            if (kit.toLowerCase().equals(lowername)) return kit;
        } return "None";
    }
}
