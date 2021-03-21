package me.finneganmcguire.kit_pvp_minecraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI implements Listener {
    private Inventory startmenu_gui;
    private Inventory kits_gui;

    public void openNewGUI(Player p){
        startmenu_gui = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.BLACK + "MENU");

        ItemStack kits_item = new ItemStack(Material.BLUE_CONCRETE, 1);
        ItemMeta kits_meta = kits_item.getItemMeta();

        // Item Data
        kits_meta.setDisplayName(ChatColor.GREEN + "MENU");
        kits_item.setItemMeta(kits_meta);

        startmenu_gui.setItem(3, kits_item);

        p.openInventory(startmenu_gui);
    }

    public void KitWindowGUI(Player p){
        kits_gui = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.AQUA + "KITS");

        // Brawler Gui Data
        ItemStack brawler = new ItemStack(Material.LINGERING_POTION, 1);
        ItemMeta brawlerMeta = brawler.getItemMeta();
        brawlerMeta.setDisplayName(ChatColor.RED + "BRAWLER");
        brawler.setItemMeta(brawlerMeta);
        kits_gui.setItem(0, brawler);

        // Granpa Gui Data
        ItemStack grandpa = new ItemStack(Material.STICK, 1);
        ItemMeta grandpaStickMeta = brawler.getItemMeta();
        brawlerMeta.setDisplayName(ChatColor.RED + "BRAWLER");
        brawler.setItemMeta(brawlerMeta);
        kits_gui.setItem(0, brawler);


        p.openInventory(kits_gui);
    }

    @EventHandler
    public void guiClickHandler(InventoryClickEvent e){

    }

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if ( action.equals( Action.RIGHT_CLICK_AIR ) || action.equals( Action.RIGHT_CLICK_BLOCK ) ) {
            if ( item != null && item.getType() == Material.SLIME_BALL ) {
                openNewGUI(event.getPlayer());
            }
        }
    }

}
