package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class GameVariables {

    public static HashMap<String, Object> GameItems = new HashMap<>();
    public static Location WorldSpawn;
    public static int WORLDSIZE; //Length of bounding box edge
    public static boolean feastPlatformSpawned = false;
    public static Location feastLocation;

    public static void SGPvPMessage(Player p, String message) {
        String prefix = "&l&f[&6SG&4PvP&f] &r";
        message = ChatColor.translateAlternateColorCodes('&', prefix + message);
        p.sendMessage(message);
    }
    public static void SGPvPMessage(String message) {
        String prefix = "&l&f[&6SG&4PvP&f] &r";
        message = ChatColor.translateAlternateColorCodes('&', prefix + message);
        Bukkit.broadcastMessage(message);
    }

    public static class WorldBounds {
        public static int MINX = 0;
        public static int MAXX = 0;
        public static int MINY = 0;
        public static int MAXY = 256;
        public static int MINZ = 0;
        public static int MAXZ = 0;
    }
    public static class CustomItems {
        //GameItems.put("GlassBow", new GlassBow);

        public static class GlassBow {
            public static ItemStack getGlassBow() {
                ItemStack glassBow = new ItemStack(Material.BOW, 1);
                ItemMeta bowMeta = glassBow.getItemMeta();
                bowMeta.setDisplayName(ChatColor.WHITE + "Glass Bow");
                bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
                if (bowMeta instanceof Damageable)
                    ((Damageable) bowMeta).damage(1);
                glassBow.setItemMeta(bowMeta);
                return glassBow;
            }
            @EventHandler
            public void onRightClick (PlayerInteractEvent event) {
                Player p = event.getPlayer();
                if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (event.getItem().getType() == Material.BOW) {
                        // insert logic
                        p.sendMessage("Your bow shatters into pieces");
                        p.getInventory().setItemInMainHand(null);
                    }
                }
            }
        }
        public static ItemStack GlassSword;
        public static ItemStack FeastSoup;


    }
}
