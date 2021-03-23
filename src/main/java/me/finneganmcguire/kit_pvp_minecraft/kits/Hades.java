package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class Hades implements CommandExecutor {

    private Kit_PvP_Minecraft main;

    public void Hades(Kit_PvP_Minecraft main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if(sender instanceof Player){
            Player player = (Player) sender;

            if(Kit_PvP_Minecraft.canChangeKit){
                // REMOVE POTION EFFECTS
                for(PotionEffect effect : player.getActivePotionEffects()){
                    player.removePotionEffect(effect.getType());
                }

                //Hades' Ingot
                Material hadesIngot = Material.iron_ingot;
                ItemStack iron_ingot = new ItemStack(hadesIngot);
                ItemMeta iron_ingot_meta = clock.getItemMeta();

                PlayerStorage.setPlayerNewKit(player.getPlayer(), "Hades");

                //Ingot Meta Data
                iron_ingot_meta.setDisplayName(ChatColor.RED + "Hades' Reckoning");
                iron_ingot_meta.setLore(Arrays.asList(ChatColor.RED + "This ingot is used to control the minds and hearts of the world.\n"));
                iron_ingot.setItemMeta(iron_ingot_meta);
              
                //Inventory
                Inventory inv = player.getInventory();
                inv.clear();
                inv.addItem(hadesIngot);
                player.sendMessage("You Have Chosen: " + ChatColor.RED + " HADES! ");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.hades_Description);
            } else{
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
            }
        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }

        return false;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityTarget(EntityTargetLivingEntityEvent evt) {
    if (evt.getTarget() instanceof Player) {
        evt.setCancelled(true);
        evt.setTarget(null);
    }
    //If the mob is already targeting another entity
    if (evt.getEntity() instanceof Mob) {
        Mob mob = (Mob) evt.getEntity();
        if (mob.getTarget() instanceof Player) {
            mob.setTarget(null);
        }
    }
}
    
