package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SoupEvent implements Listener {

    private com.sgpvp.main main;

    public void SoupEvent(com.sgpvp.main main){
        this.main = main;
    }

    @EventHandler
    public void soupHeal(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        boolean holdingSoup = player.getInventory().getItemInMainHand().getType() == Material.MUSHROOM_STEW;
        if (holdingSoup && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event
                .getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event
                .getAction() == Action.PHYSICAL) && player.getHealth() != 20.0D && player.getHealth() != 0.0D) {
            player.setHealth((player.getHealth() + 7.0D > 20.0D ) ? 20.0D : (player.getHealth() + 7.0D));
            player.getInventory().getItemInMainHand().setType(Material.BOWL);
            if (PlayerData.playerHasKitActive(player, "recycler"))
                Recycle(player);
            return;
        }
    }
    private void Recycle(Player player) {
        GameVariables.SGPvPMessage(player,"Recycling bowl");
        player.getInventory().setItemInMainHand(null); //removes bowl
        if (PlayerData.recycleBowl(player)) {
            GameVariables.SGPvPMessage(player, "have a free soup bruv");
            player.getInventory().addItem(new ItemStack(Material.MUSHROOM_STEW, 1));
        }
    }
}
