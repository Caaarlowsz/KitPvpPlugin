package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class GameVariables {
    public static Location WorldSpawn;
    public static int WORLDSIZE; //Length of bounding box edge
    public static class WorldBounds {
        public static int MINX = 0;
        public static int MAXX = 0;
        public static int MINY = 0;
        public static int MAXY = 256;
        public static int MINZ = 0;
        public static int MAXZ = 0;
    }
    public static class CustomItems {
        public static ItemStack GlassBow;
        public static ItemStack GlassSword;
        public static ItemStack FeastSoup;
    }
}
