package com.sgpvp.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Glider extends Kit{
    public String kitName = "Glider";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        ItemStack glider = new ItemStack(Material.ELYTRA);
        ItemMeta glider_Data = glider.getItemMeta();
        glider_Data.setDisplayName(ChatColor.BLACK + "Glider");
        glider_Data.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        glider.setItemMeta(glider_Data);
        player.getInventory().addItem(glider);
        /* Kit functionality ends here */
    }
}
