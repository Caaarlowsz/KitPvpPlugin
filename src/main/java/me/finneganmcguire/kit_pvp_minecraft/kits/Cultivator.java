package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Cultivator extends Kit {
    public String kitName = "Cultivator";

    private Kit_PvP_Minecraft main;

    public Cultivator(Kit_PvP_Minecraft main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        ItemStack stone_hoe = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta stone_hoe_data = stone_hoe.getItemMeta();
        assert stone_hoe_data != null;
        stone_hoe_data.setDisplayName(ChatColor.BOLD + "The Cultivator's Powerful Plow");
        stone_hoe_data.setLore(Collections.singletonList("Instantly grows any crop or sapling"));
        stone_hoe.setItemMeta(stone_hoe_data);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Kit_PvP_Minecraft.canChangeKit) {
                //Finds player in hashmap database - Changes kit to Cultivator
                PlayerStorage.setPlayerNewKit(Objects.requireNonNull(player.getPlayer()), "cultivator");

                Inventory inv = player.getInventory();
                inv.addItem(stone_hoe);

            }
        }
        return false;
    }


    @EventHandler
    //Instant Oak Tree
    public void onOakSaplingPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.TREE);
        }
    }

    //Instant Birch Tree
    public void onBirchSaplingPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.BIRCH);
        }
    }

    //Instant Spruce Tree
    public void onSpruceSaplingPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.DARK_OAK);
        }
    }

    //Instant Jungle Tree
    public void onJungleSaplingPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.JUNGLE);
        }
    }

    //Instant Acacia Tree
    public void onAcaciaSaplingPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.ACACIA);
        }
    }

    //Instant Redwood Tree
    public void onRedwoodSaplingPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.REDWOOD);
        }
    }

    //Instant Red Mushrooms
    public void onRedMushroomPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.RED_MUSHROOM);
        }
    }

    //Instant Brown Mushrooms
    public void onBrownMushroomPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Sapling) {
            event.getBlock().setType(Material.AIR);

            Location location = event.getBlock().getLocation();

            location.getWorld().generateTree(location, TreeType.BROWN_MUSHROOM);
        }
    }

    /*
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

     */
}
