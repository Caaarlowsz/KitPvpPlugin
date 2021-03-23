package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GameVariables {
    public static Location WorldSpawn;
    public static int WORLDSIZE; //Length of bounding box edge
    public static boolean feastPlatformSpawned = false;
    public static Location feastLocation;
    public static class WorldBounds {
        public static int MINX = 0;
        public static int MAXX = 0;
        public static int MINY = 0;
        public static int MAXY = 256;
        public static int MINZ = 0;
        public static int MAXZ = 0;
    }
    public static class CustomItems {
        public static class GlassBow {
            public static ItemStack getGlassBow() {
                ItemStack glassBow = new ItemStack(Material.BOW);
                return glassBow;
            }
            @EventHandler
            public void onRightClick (PlayerInteractEvent event) {
                Player p = event.getPlayer();
                if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (event.getItem().getType() == Material.BOW) {
                        // insert logic
                    }
                }
            }
        }
        public static ItemStack GlassSword;
        public static ItemStack FeastSoup;
    }
}
