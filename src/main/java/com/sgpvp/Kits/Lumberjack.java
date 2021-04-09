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

public class Lumberjack extends Kit{
    public String kitName = "Lumberjack";
    public int axeEfficiencyLevel = 10;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        Enchantment axeEnchant = Enchantment.DIG_SPEED;
        Material lumberjackAxe = Material.WOODEN_AXE;
        ItemStack axe = new ItemStack(lumberjackAxe, 1);
        ItemMeta axeMeta = axe.getItemMeta();
        axeMeta.setDisplayName(ChatColor.DARK_GREEN + "Lumberjack Axe");
        axeMeta.addEnchant(axeEnchant, axeEfficiencyLevel, true);
        axeMeta.setUnbreakable(true);
        axe.setItemMeta(axeMeta);
        Inventory inv = player.getInventory();
        inv.addItem(axe);
        /* Kit functionality ends here */
    }
}
