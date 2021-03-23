package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupEvent implements Listener {

    private Kit_PvP_Minecraft main;

    public void SoupEvent(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @EventHandler
    public void soupHeal(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        boolean holdingSoup = player.getInventory().getItemInMainHand().getType() == Material.MUSHROOM_STEW;
        if (holdingSoup && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event
                .getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event
                .getAction() == Action.PHYSICAL) && player.getHealth() != 20.0D && player.getHealth() != 0.0D) {
            player.setHealth((player.getHealth() + 7.0D > player.getMaxHealth() ) ? player.getMaxHealth(): (player.getHealth() + 7.0D));
            player.getInventory().getItemInMainHand().setType(Material.BOWL);
            //player.setItemInHand(null); removes bowl
            return;
        }
    }
}
