package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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



        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getClickedBlock().getType().equals(damaged_anvil)){
                if(PlayerData.playerHasKitActive(event.getPlayer(), "blacksmith")){

                    // AXE UPGRADES
                    if(itemToUpgrade.getType().equals(Material.WOODEN_AXE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.STONE_AXE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                        event.getPlayer().spawnParticle(particle, event.getClickedBlock().getLocation(), 50);
                    }
                    else if(itemToUpgrade.getType().equals(Material.STONE_AXE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_AXE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_AXE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Cannot Upgrade Axes To Diamond!");
                    }

                    // PICK UPGRADES
                    if(itemToUpgrade.getType().equals(Material.WOODEN_PICKAXE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.STONE_PICKAXE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.STONE_PICKAXE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_PICKAXE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_PICKAXE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.DIAMOND_PICKAXE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }

                    // SWORD UPGRADES
                    if(itemToUpgrade.getType().equals(Material.WOODEN_SWORD)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.STONE_SWORD);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.STONE_SWORD)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_SWORD);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_SWORD)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Cannot Upgrade Swords To Diamond!");
                    }

                    // HOE UPGRADES
                    if(itemToUpgrade.getType().equals(Material.WOODEN_HOE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.STONE_HOE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.STONE_HOE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_HOE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_HOE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.DIAMOND_HOE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }

                    // ARMOUR UPGRADES

                    // Helmets
                    if(itemToUpgrade.getType().equals(Material.LEATHER_HELMET)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_HELMET);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_HELMET)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.DIAMOND_HELMET);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }

                    // CHESTPLATE UPGRADES
                    if(itemToUpgrade.getType().equals(Material.LEATHER_CHESTPLATE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_CHESTPLATE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_CHESTPLATE)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.DIAMOND_CHESTPLATE);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }

                    // Leggings
                    if(itemToUpgrade.getType().equals(Material.LEATHER_LEGGINGS)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_LEGGINGS);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_LEGGINGS)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.DIAMOND_LEGGINGS);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }

                    // Boots
                    if(itemToUpgrade.getType().equals(Material.LEATHER_BOOTS)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.IRON_BOOTS);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }
                    else if(itemToUpgrade.getType().equals(Material.IRON_BOOTS)){
                        GameVariables.SGPvPMessage(event.getPlayer(), ChatColor.GOLD + "Item Has Been Upgraded!");
                        itemToUpgrade.setType(Material.DIAMOND_BOOTS);
                        event.getClickedBlock().setType(Material.AIR);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), anvil_Sound, anvil_Sound_Volume, anvil_Sound_Pitch);
                    }

                }
            }
        }
    }
}

