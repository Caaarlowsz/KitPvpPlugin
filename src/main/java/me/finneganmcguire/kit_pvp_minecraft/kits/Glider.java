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

public class Glider extends Kit{
    public String kitName = "Glider";
    private Kit_PvP_Minecraft main;

    public void Glider(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(Kit_PvP_Minecraft.canChangeKit){
                Inventory inv = player.getInventory();

                //Give Elytra
                ItemStack glider = new ItemStack(Material.ELYTRA);
                ItemMeta glider_Data = glider.getItemMeta();
                glider_Data.setDisplayName(ChatColor.BLACK + "Glider");
                glider.setItemMeta(glider_Data);
                //glider.setDisplayName(ChatColor.BLACK + "Glider");

                //glider.addEnchantment(enchantment.unbreaking, 10, true);
              
                player.getInventory().addItem(glider);

            }
        }
        return false;
    }
}
