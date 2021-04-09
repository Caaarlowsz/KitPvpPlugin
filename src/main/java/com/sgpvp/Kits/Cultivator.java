package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.List;

public class Cultivator extends Kit {
    public String kitName = "Cultivator";

    public List<Material> seeds;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        ItemStack stone_hoe = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta stone_hoe_data = stone_hoe.getItemMeta();
        stone_hoe_data.setDisplayName(ChatColor.BOLD + "The Cultivator's Powerful Plow");
        stone_hoe_data.setLore(Arrays.asList("Instantly grows any crop or sapling"));
        stone_hoe.setItemMeta(stone_hoe_data);
        Inventory inv = player.getInventory();
        inv.addItem(stone_hoe);


        /* Kit functionality ends here */
    }


    @EventHandler
    //Instant Oak Tree
    public void onSeedPlace(BlockPlaceEvent event) {
        if(PlayerData.playerHasKitActive(event.getPlayer(), "cultivator")) {
            Material material = event.getBlockPlaced().getType();
            CropState cropState = CropState.RIPE;
        }
    }
}
