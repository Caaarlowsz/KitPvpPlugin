package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.GameItems;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Kangaroo extends Kit{
    public String kitName = "Kangaroo";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        super.onCommand(sender, command, label, args);

        /* Kit functionality starts here */
        if (sender instanceof Player && GameVariables.canChangeKit) {
            Player player = (Player) sender;
            Inventory inv = player.getInventory();
            inv.addItem(GameItems.getKangarooFoot());
        }
        /* Kit functionality ends here */

        return false;
    }
    /* Kit event handlers start here */
    @EventHandler
    public void onKangarooFoot(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        GameVariables.SGPvPMessage(player, "You leap through the air!");
        boolean holdingFoot = player.getInventory().getItemInMainHand().getType() == Material.RABBIT_FOOT;
        if (holdingFoot && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event
                .getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event
                .getAction() == Action.PHYSICAL)) {
            PotionEffect jumpboost = new PotionEffect(PotionEffectType.JUMP, 10, 1);
            jumpboost.apply(player);
        }
    }
    /* Kit event handlers end here */
}
