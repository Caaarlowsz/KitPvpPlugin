package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Beastmaster implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void Lumberjack(Kit_PvP_Minecraft main){
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

                //Beastmaster Items
                ItemStack bones = new ItemStack(Material.BONE, 3);
                ItemStack wolfSpawnEggs = new ItemStack(Material.WOLF_SPAWN_EGG, 2);

                Inventory inv = player.getInventory();
                inv.clear();

                PlayerStorage.setPlayerNewKit(player.getPlayer(), "beastmaster");

                inv.addItem(bones, wolfSpawnEggs);
                player.sendMessage("You Have Chosen: " + ChatColor.DARK_GREEN + " BEASTMASTER!");
            } else{
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
            }
        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }

        return false;
    }

    @EventHandler
    public void WhenFistOut(PlayerInteractEntityEvent e){

        Wolf wolf = (Wolf) e.getRightClicked();

        if(PlayerStorage.playerHasKitActive(e.getPlayer(), "beastmaster")){
            if(e.getRightClicked().getType().equals(EntityType.WOLF) && e.getHand().equals(Material.BONE)){
                wolf.setTamed(true);
                wolf.setOwner(e.getPlayer());
            }
        }
    }
}
