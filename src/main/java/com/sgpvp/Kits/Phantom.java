package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.GameItems;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Phantom extends Kit{
    public String kitName = "Phantom";
    public int phantomCooldown = 120 * 1000;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        super.onCommand(sender, command, label, args);

        /* Kit functionality starts here */
        if (sender instanceof Player && GameVariables.canChangeKit) {
            Player player = (Player) sender;
            Inventory inv = player.getInventory();
            inv.addItem(GameItems.getPhantomFeather());
        }
        /* Kit functionality ends here */

        return false;
    }
    /* Kit event handlers start here */
    HashMap<Player, Boolean> cooldowns = new HashMap<>();
    @EventHandler
    public void PhantomFly(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        if (!player.getInventory().getItemInMainHand().getType().equals(Material.FEATHER)) return;
        if (cooldowns.containsKey(player) && cooldowns.get(player)) {
            GameVariables.SGPvPMessage(player, "Your flight is on cooldown");
            return;
        }
        Thread flying = new Thread(new Flying(player));
        flying.start();
    }
    private class Flying extends Thread {
        Player player;
        Flying(Player p) { this.player = p; }
        public void run(){
            player.setAllowFlight(true);
            cooldowns.put(player, true);
            try {
                for (int s = 0; s < 7; s++) {
                    GameVariables.SGPvPMessage(player, "Flight remaining " + (7-s) + " seconds.");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.setAllowFlight(false);
            try {
                Thread.sleep(phantomCooldown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cooldowns.put(player, false);
        }
    }
    /* Kit event handlers end here */
}
