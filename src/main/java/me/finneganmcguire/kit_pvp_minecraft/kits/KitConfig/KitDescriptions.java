package me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class KitDescriptions {
    private static final HashMap<String, String>descrptions = new HashMap<String, String>(){{
        put("TimeWizard", "The time wizard is a very unique kit and is good when used at the right time\n - The Time wizard starts with a special clock that stops time for players around you temporarily");
        put("Werewolf", "Werewolf is best when it is night time.\n - Other players see a glowing outline of you in the world\n- You get Nightvision, Speed & Extra Health At Night");
        put("Brawler", "The brawler kit is a great aggressive kit\n - The brawler kit has extra damage when punching with your fist");
        put("Chameleon", "The chameleon kit is great for more passive players\n - You can disguise yourself as any mob\n - Upon taking damage you will be revealed");
        put("Lumberjack", "The lumberjack kit is great for players that like to use block to get height advantages or create structures\n - The lumberjack kit starts with a Efficiency 10 Wooden Axe that is unbreakable");
        put("Grandpa", "The grandpa kit is great at keeping enemies away from you and works well against aggressive players\n - The grandpa kit starts with a knockback 2 stick");
        put("Turtle", "The turtle kit is great at players who like more defensive gameplay.\n - Holding Sneak/Shift Gives You Resistance 2 & Weakness 2");
        put("Milkman", "The milk man is good for having a good source of buffs \n - You start with a milk bucket\n - Drinking milk gives you Speed 1, Regeneration 1, and Fire Resistance for 8 seconds");
        put("Fireman", "The fireman kit is great when it comes to dealing with lava or fire\n - The fireman kit has immunity to fire and starts with a water bucket");
        put("Pyromancer", "The pyromancer is a very lethal and can be useful in many different situations\n - The Pyromancer start with a lava bucket as well as flint and steel");
        put("Recycler", "After 3 uses of soup, you get a free soup! :)");
        put("Beastmaster", "The beast master is a very lethal kit and for people that can control wolves wisely\n - Your bones instantly tame wolves\n - The beastmaster starts with 2 Wolf Eggs and 3 Bones ");
        put("Glider", "Glide around the map in an elytra. ");
        put("Groundhog", "");
        put("Hades", "");
    }};
    private static final HashMap<String, ChatColor> kitcolor = new HashMap<String, ChatColor>(){{
        put("TimeWizard", ChatColor.DARK_PURPLE);
        put("Werewolf", ChatColor.DARK_RED);
        put("Brawler", ChatColor.YELLOW);
        put("Chameleon", ChatColor.GREEN);
        put("Lumberjack", ChatColor.GREEN);
        put("Grandpa", ChatColor.GRAY);
        put("Turtle", ChatColor.GREEN);
        put("Milkman", ChatColor.WHITE);
        put("Fireman", ChatColor.RED);
        put("Pyromancer", ChatColor.RED);
        put("Recycler", ChatColor.GREEN);
        put("Beastmaster", ChatColor.WHITE);
        put("Glider", ChatColor.DARK_GRAY);
        put("Groundhog", ChatColor.DARK_GRAY);
        put("Hades", ChatColor.DARK_RED);
    }};

    public static ChatColor color(String kitname) {
        return kitcolor.get(kitname);
    }
    public static String description(String kitname) {
        return descrptions.get(kitname);
    }
}
