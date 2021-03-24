package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Beastmaster extends Kit {

    private Kit_PvP_Minecraft main;

    public void Beastmaster(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.onCommand(sender, command, label, args);
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (Kit_PvP_Minecraft.canChangeKit) {
                //Beastmaster Items
                ItemStack bones = new ItemStack(Material.BONE, 3);
                ItemStack wolfSpawnEggs = new ItemStack(Material.WOLF_SPAWN_EGG, 2);

                PlayerStorage.setPlayerNewKit(player.getPlayer(), "beastmaster");

                Inventory inv = player.getInventory();
                inv.addItem(bones, wolfSpawnEggs);
                player.sendMessage("You Have Chosen: " + ChatColor.DARK_GREEN + " BEASTMASTER!");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.beastmaster_Description);
            }
        }
        return false;
    }

    @EventHandler
    public void WhenFistOut(PlayerInteractEntityEvent e){

        Wolf wolf = (Wolf) e.getRightClicked();
        e.getPlayer().getLocation();
        int amountOfBones = e.getPlayer().getInventory().getItemInMainHand().getAmount();

        if(PlayerStorage.playerHasKitActive(e.getPlayer(), "beastmaster")){
            if(e.getRightClicked().getType().equals(EntityType.WOLF)){
                if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BONE)){
                    if(!wolf.isTamed()){
                        wolf.setTamed(true);
                        wolf.setOwner(e.getPlayer());
                        amountOfBones--;
                        e.getPlayer().getInventory().getItemInMainHand().setAmount(amountOfBones);
                    }
                }
            }
        }
    }
}
