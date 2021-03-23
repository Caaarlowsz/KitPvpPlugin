package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Glider implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void Glider(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            if(Kit_PvP_Minecraft.canChangeKit){
                Inventory inv = player.getInventory();
                inv.clear();

                //Give Elytra
                ItemStack wings = new ItemStack(Material.elytra);
                ItemMeta wings_Data = wings.getItemMeta();
                wings.setItemMeta(wings_Data);

                wings.addEnchantment(enchantment.unbreaking, 3, true);
              
                player.getInventory().addItem(wings);

                player.sendMessage("You Have Chosen: " + ChatColor.DARK_RED+ " FIREMAN! ");
                player.sendMessage("ChatColor.BLACK+ "YOU ARE BATMAN");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.fireman_Description);
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
