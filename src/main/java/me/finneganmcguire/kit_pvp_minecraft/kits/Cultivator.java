package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Cultivator implements CommandExecutor, Listener {

   

    private Kit_PvP_Minecraft main;
    
    public void Cultivator(Kit_PvP_Minecraft main) {
        this.main = main;
    }
  
  
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      
      ItemStack stone_hoe = new ItemStack(Material.STONE_HOE, 1);
      ItemMeta stone_hoe_data = stone_hoe.getItemMeta();
      stone_hoe_data.setDisplayName(ChatColor.BOLD + "The Cultivator's Powerful Plow");
      stone_hoe_data.setLore(Arrays.asList("Instantly grows any crop or sapling"));
      stone_hoe.setItemMeta(stone_hoe_data);
      
      if (sender instanceof Player) {
        Player player = (Player) sender;
        
        if (Kit_PvP_Minecraft.canChangeKit) {
          //Removing Potion Effects
          for(PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }

          Inventory inv = player.getInventory();
          inv.clear();
    
          //Finds player in hashmap database - Changes kit to Cultivator
          PlayerStorage.setPlayerNewKit(player.getPlayer(), "cultivator");
          
          inv.addItem(oak_sapling);
          player.sendMessage("You Have Chosen: " + ChatColor.BOLD + " CULTIVATOR! ");
          player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.cultivator_Description);
          
        }
        
        else {
          player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
        }
        
      }
      
      else {
        main.getLogger().info("You Have To Be Player To Get Kit");
      }
      
      return false;
    }
    
    /* 
    @EventHandler
    //Instant Oak Tree
    public void onOakSaplingPlace(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
           
        if (material == Material.oak_sapling) {
            Sapling s = new Sapling(Material.oak_sapling);
            ItemStack item = s.toItemStack();
           
            s.setIsInstantGrowable(true);
           
            event.getBlock().setType(item.getType());
        }
    }
  
    //Instant Spruce Tree
    public void onSpruceSaplingPlace(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
           
        if (material == Material.spruce_sapling) {
            Sapling s = new Sapling(Material.spruce_sapling);
            ItemStack item = s.toItemStack();
           
            s.setIsInstantGrowable(true);
           
            event.getBlock().setType(item.getType());
        }
    }
  
    //Instant Birch Tree
    public void onBirchSaplingPlace(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
           
        if (material == Material.birch_sapling) {
            Sapling s = new Sapling(Material.birch_sapling);
            ItemStack item = s.toItemStack();
           
            s.setIsInstantGrowable(true);
           
            event.getBlock().setType(item.getType());
        }
    }
  
    //Instant Jungle Tree
    public void onJungleSaplingPlace(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
           
        if (material == Material.jungle_sapling) {
            Sapling s = new Sapling(Material.jungle_sapling);
            ItemStack item = s.toItemStack();
           
            s.setIsInstantGrowable(true);
           
            event.getBlock().setType(item.getType());
        }
    }
  
    //Instant Acacia Tree
    public void onAcaciaSaplingPlace(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
           
        if (material == Material.acacia_sapling) {
            Sapling s = new Sapling(Material.acacia_sapling);
            ItemStack item = s.toItemStack();
           
            s.setIsInstantGrowable(true);
           
            event.getBlock().setType(item.getType());
        }
    }
  
    //Instant Dark Oak Tree
    public void onDarkOakSaplingPlace(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
           
        if (material == Material.dark_oak_sapling) {
            Sapling s = new Sapling(Material.dark_oak_sapling);
            ItemStack item = s.toItemStack();
           
            s.setIsInstantGrowable(true);
           
            event.getBlock().setType(item.getType());
        }
    }
  
   //Instant Red Mushrooms
   public void onRedMushroomPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof red_mushroom) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            red_mushroom.setStage(red_mushroom.getMaximumStage());
        }
    }
  
   //Instant Brown Mushrooms
   public void onBrownMushroomPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof brown_mushroom) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            brown_mushroom.setStage(brown_mushroom.getMaximumStage());
        }
    }
    
  
    //Instant Wheat
    public void onWheatSeedsPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof wheat_seeds) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            wheat_seeds.setStage(wheat_seeds.getMaximumStage());
        }
    }
  
  
    //Instant Beetroots
    public void onBeetrootSeedsPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof beetroot_seeds) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            beetroot_seeds.setStage(beetroot_seeds.getMaximumStage());
        }
    }
  
    //Instant Pumpkins
    public void onPumpkinSeedsPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof pumpkin_seeds) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            pumpkin_seeds.setStage(pumpkin_seeds.getMaximumStage());
        }
    }
  
    //Instant Melons
    public void onMelonSeedsPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof melon_seeds) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            melon_seeds.setStage(melon_seeds.getMaximumStage());
        }
    }
  
    //Instant Nether Warts
    public void onNetherWartsPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof nether_wart) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            nether_wart.setStage(nether_wart.getMaximumStage());
        }
    }
    
    //Instant Carrots
    public void onCarrotPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof carrot) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            carrot.setStage(carrot.getMaximumStage());
        }
    }
  
    //Instant Potatoes
    public void onPotatoPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof carrot) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            potato.setStage(potato.getMaximumStage());
        }
    }
  
    //Instant Sweet Berries
    public void onSweetBerriesPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof sweet_berries) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            sweet_berries.setStage(sweet_berries.getMaximumStage());
        }
    }
  
    //Instant Cocoa Beans
    public void onCocoaBeansPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();
     
        if (data instanceof cocoa_beans) {
            //event.getBlock().setType(Material.AIR);
         
            Location location = event.getBlock().getLocation();
         
            cocoa_beans.setStage(cocoa_beans.getMaximumStage());
        }
    }
    /*
    
}
