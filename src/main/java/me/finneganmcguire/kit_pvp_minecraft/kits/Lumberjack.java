package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Lumberjack implements CommandExecutor {

    private Kit_PvP_Minecraft main;

    public void Lumberjack(Kit_PvP_Minecraft main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

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

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            if(Kit_PvP_Minecraft.canChangeKit){
                Inventory inv = player.getInventory();
                inv.clear();

                player.sendMessage("You Have Chosen: " + ChatColor.DARK_GREEN + " LUMBERJACK! ");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.lumberjack_Description);

                // Give Axe To Player
                inv.addItem(axe);
            } else{
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
            }

        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }

        return false;
    }
}
