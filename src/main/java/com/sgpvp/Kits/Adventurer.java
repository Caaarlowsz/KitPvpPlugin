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
        Sound questComplete = Sound.ENTITY_PLAYER_LEVELUP;

        if (!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;
        boolean holdingMap = player.getInventory().getItemInMainHand().getType().equals(Material.MAP);
        if(!(holdingMap)) return;
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            player.playSound(player.getLocation(), questComplete, 10, 10);
        }
    }
    /* Kit event handlers end here */
}
