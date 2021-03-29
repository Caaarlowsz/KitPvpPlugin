package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
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
    private Kit_PvP_Minecraft main;

    public void Lumberjack(Kit_PvP_Minecraft main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        int axeEfficiencyLevel = 10;

        Enchantment axeEnchant = Enchantment.DIG_SPEED;
        Material lumberjackAxe = Material.WOODEN_AXE;

        ItemStack axe = new ItemStack(lumberjackAxe, 1);
        ItemMeta axeMeta = axe.getItemMeta();
        axeMeta.setDisplayName(ChatColor.DARK_GREEN + "Lumberjack Axe");
        axeMeta.addEnchant(axeEnchant, axeEfficiencyLevel, true);
        axeMeta.setUnbreakable(true);
        axe.setItemMeta(axeMeta);

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(Kit_PvP_Minecraft.canChangeKit){


                // Give Axe To Player
                Inventory inv = player.getInventory();
                inv.addItem(axe);
            }

        }
        return false;
    }
}
