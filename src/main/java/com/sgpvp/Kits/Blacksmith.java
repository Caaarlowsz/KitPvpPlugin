package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Blacksmith extends Kit implements Listener {
    public String kitName = "Blacksmith";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        ItemStack anvil = new ItemStack(Material.DAMAGED_ANVIL);
        ItemMeta anvil_meta = anvil.getItemMeta();
        if (anvil_meta == null) return;
        anvil_meta.setDisplayName(ChatColor.RED + "The Blacksmiths Anvil");

        anvil_meta.setLore(Arrays.asList("This can upgrade any item to its next tier for free", "Right click the anvil with the item you want to be upgraded","One time use!"));
        anvil.setItemMeta(anvil_meta);



        player.getInventory().addItem(anvil);
        /* Kit functionality ends here */
    }

    @EventHandler
    public void onRightClickAnvil(PlayerInteractEvent event){

        ItemStack itemToUpgrade = event.getItem();
        Material damaged_anvil = Material.DAMAGED_ANVIL;
        Sound anvil_Sound = Sound.BLOCK_ANVIL_USE;
        Particle particle = Particle.ASH;

        float anvil_Sound_Pitch = 2f;
        float anvil_Sound_Volume = 15f;

        if(!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
        if (event.getClickedBlock() == null) return;
        if (itemToUpgrade == null) return;
        if(!(event.getClickedBlock().getType().equals(damaged_anvil))) return;
        if(!PlayerData.playerHasKitActive(event.getPlayer(), "blacksmith")) return;

        // AXE UPGRADES
        if(itemToUpgrade.getType().equals(Material.WOODEN_AXE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.STONE_AXE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
            event.getPlayer().spawnParticle(particle, event.getClickedBlock().getLocation(), 50);
        }
        else if(itemToUpgrade.getType().equals(Material.STONE_AXE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_AXE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_AXE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Cannot Upgrade Axes To Diamond!");
        }

        // PICK UPGRADES
        if(itemToUpgrade.getType().equals(Material.WOODEN_PICKAXE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.STONE_PICKAXE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.STONE_PICKAXE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_PICKAXE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_PICKAXE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.DIAMOND_PICKAXE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }

        // SWORD UPGRADES
        if(itemToUpgrade.getType().equals(Material.WOODEN_SWORD)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.STONE_SWORD);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.STONE_SWORD)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_SWORD);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_SWORD)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Cannot Upgrade Swords To Diamond!");
        }

        // HOE UPGRADES
        if(itemToUpgrade.getType().equals(Material.WOODEN_HOE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.STONE_HOE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.STONE_HOE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_HOE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_HOE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.DIAMOND_HOE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }

        // ARMOUR UPGRADES

        // Helmets
        if(itemToUpgrade.getType().equals(Material.LEATHER_HELMET)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_HELMET);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_HELMET)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.DIAMOND_HELMET);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }

        // CHESTPLATE UPGRADES
        if(itemToUpgrade.getType().equals(Material.LEATHER_CHESTPLATE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_CHESTPLATE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_CHESTPLATE)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.DIAMOND_CHESTPLATE);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }

        // Leggings
        if(itemToUpgrade.getType().equals(Material.LEATHER_LEGGINGS)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_LEGGINGS);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_LEGGINGS)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.DIAMOND_LEGGINGS);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }

        // Boots
        if(itemToUpgrade.getType().equals(Material.LEATHER_BOOTS)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.IRON_BOOTS);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
        else if(itemToUpgrade.getType().equals(Material.IRON_BOOTS)){
            Chat.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
            itemToUpgrade.setType(Material.DIAMOND_BOOTS);
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
        }
    }
}

