package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.GameItems;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Cultivator extends Kit {
    public String kitName = "Cultivator";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        ItemStack cultivatorHoe = GameItems.getCultivatorHoe();
        Inventory inv = player.getInventory();
        inv.addItem(cultivatorHoe);
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
