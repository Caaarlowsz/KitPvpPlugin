package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class Cultivator extends Kit {
    public String kitName = "Cultivator";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        ItemStack stone_hoe = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta stone_hoe_data = stone_hoe.getItemMeta();
        if (stone_hoe_data == null) return;
        stone_hoe_data.setDisplayName(ChatColor.BOLD + "The Cultivator's Powerful Plow");
        stone_hoe_data.setLore(Collections.singletonList("Instantly grows any crop or sapling"));
        stone_hoe.setItemMeta(stone_hoe_data);
        Inventory inv = player.getInventory();
        inv.addItem(stone_hoe);

        /* Kit functionality ends here */
    }


    @EventHandler
    public void onSeedPlace(BlockPlaceEvent event) {
        if(!PlayerData.playerHasKitActive(event.getPlayer(), "cultivator")) return;
        try {
            Block block = event.getBlockPlaced();
            for (int i = 0; i < 6; i++)
                block.applyBoneMeal(BlockFace.DOWN);
        } catch (Exception e) {
            Chat.DebugMessage(e.getMessage());
        }
    }
}
