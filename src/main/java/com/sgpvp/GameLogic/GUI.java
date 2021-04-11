package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.Kits.*;
import com.sgpvp.Kits.KitConfig.KitDescriptions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

public class GUI implements Listener, CommandExecutor {
    private HashMap<Player, Inventory> inventories = new HashMap<>();
    private Inventory kits_gui;
    private Kit[] kits = new Kit[54];

    public void openNewGUI(Player p) {
        Inventory startmenu_gui;
        startmenu_gui = Bukkit.createInventory(null, 54, ChatColor.BLACK + "MENU");

        int i = 0;
        for (String kitName : GameVariables.kits.keySet()) {
            Kit kit = GameVariables.kits.get(kitName);
            startmenu_gui.addItem(createGuiItem(KitDescriptions.material(kitName), kitName, KitDescriptions.description(kitName)));
            kits[i] = kit;
            i += 1;
            if (i == GameVariables.kits.size()) break;
        }

        p.openInventory(startmenu_gui);
        inventories.put(p, startmenu_gui);
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(Material material, String name, String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory() != inventories.get(player)) return;

        e.setCancelled(true);

        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        Player p = (Player) e.getWhoClicked();
        kits[e.getRawSlot()].onCommand(p, null, null, null);

    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(InventoryDragEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory() == inventories.get(player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item != null && item.getType() == Material.SLIME_BALL) {
                openNewGUI(event.getPlayer());
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //this.openNewGUI((Player) sender);

        ((Player)sender).getInventory().addItem(new ItemStack(Material.SLIME_BALL, 1));
        return true;
    }
}