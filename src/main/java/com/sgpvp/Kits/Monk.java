package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.GameItems;
import com.sgpvp.GameLogic.ProgressBar;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Random;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Monk extends Kit{
    public String kitName = "Monk";
    public int staffCooldown = 30 * 1000;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        Inventory inv = player.getInventory();
        inv.addItem(GameItems.getMonkStaff());
        /* Kit functionality ends here */
    }
    /* Kit event handlers start here */
    // Used in some kits to add additional functionality
    // if (!PlayerData.playerHasKitActive(p, kitName.toLowerCase())) return; // Critical line
    @EventHandler
    public void staffHit(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        if (!e.getRightClicked().getType().equals(EntityType.PLAYER)) return;
        if (!player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) return;
        if (cooldowns.containsKey(player) && cooldowns.get(player)) {
            Chat.SGPvPMessage(player, "Your staff is on cooldown");
            return;
        }
        Player victim = (Player) e.getRightClicked();
        PlayerInventory inventory = victim.getInventory();
        if (inventory.getItemInMainHand().equals(null)) return;
        Random rng = new Random();
        int index = rng.nextInt(35-9) + 9;
        ItemStack main = inventory.getItemInMainHand();
        ItemStack other = inventory.getItem(index);
        inventory.setItemInMainHand(other);
        inventory.setItem(index, main);

        cooldowns.put(player, true);
        Thread staffCooldown = new Thread(new StaffCooldown(player));
        staffCooldown.start();

    }
    HashMap<Player, Boolean> cooldowns = new HashMap<>();
    private class StaffCooldown extends Thread {
        Player player;
        StaffCooldown(Player p) { this.player = p; }
        public void run(){
            ProgressBar cooldownProgress = new ProgressBar(player, "Cooldown: ", BarColor.RED, BarStyle.SOLID, staffCooldown);
            try {
                for (int i = 0; i < staffCooldown; i++) {
                    Thread.sleep(1);
                    cooldownProgress.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cooldowns.put(player, false);
        }
    }
    /* Kit event handlers end here */
}
