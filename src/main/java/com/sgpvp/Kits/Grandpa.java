package com.sgpvp.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class Grandpa extends Kit{
    public String kitName = "Grandpa";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */


        ItemStack grandpaStick = new ItemStack(Material.STICK);
        ItemMeta stickData = grandpaStick.getItemMeta();

        if (stickData == null) return;
        stickData.setDisplayName(ChatColor.BOLD + "Grandpa Joe's Stick");
        stickData.setLore(Collections.singletonList("This stick belongs to grandpa joe"));
        stickData.addEnchant(Enchantment.KNOCKBACK, 2, true);

        grandpaStick.setItemMeta(stickData);

        Inventory inv = player.getInventory();
        inv.addItem(grandpaStick);

        /* Kit functionality ends here */
    }
}
