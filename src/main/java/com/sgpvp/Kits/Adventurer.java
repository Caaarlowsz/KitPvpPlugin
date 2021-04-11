package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Adventurer extends Kit{
    public String kitName = "Adventurer";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        ItemStack map = new ItemStack(Material.MAP);
        ItemMeta map_meta = map.getItemMeta();
        map_meta.setDisplayName(ChatColor.YELLOW + "The Adventurers Map");

        map.setItemMeta(map_meta);

        player.getInventory().addItem(new ItemStack(map));

        /* Kit functionality ends here */
    }

    /* Kit event handlers start here */
    @EventHandler
    public void OnMapRightClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;

        boolean holdingMap = player.getInventory().getItemInMainHand().getType() == Material.MAP;

        if(holdingMap && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 10);
            player.sendTitle("A Quest Has Been Added!", "Quest: test", 1 , 20, 1);
            Chat.DebugMessage("Map Event activated");
        }

    }
    /* Kit event handlers end here */
}
