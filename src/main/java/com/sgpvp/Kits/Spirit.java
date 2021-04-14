package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.ProgressBar;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
 */

public class Spirit extends Kit{
    public String kitName = "Spirit"; // Try to keep this the same as the class name <3
    public int cooldown = 25 * 1000; //20s*20tps
    public int duration = 8 * 1000;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        player.getInventory().addItem(new ItemStack(Material.GHAST_TEAR, 1));
        /* Kit functionality ends here */
    }
    /* Kit event handlers start here */
    // Used in some kits to add additional functionality
    // if (!PlayerData.playerHasKitActive(p, kitName.toLowerCase())) return; // Critical line


    HashMap<Player, Boolean> cooldowns = new HashMap<>();
    @EventHandler
    public void spiritInvisible(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        if (!player.getInventory().getItemInMainHand().getType().equals(Material.GHAST_TEAR)) return;
        if (cooldowns.containsKey(player) && cooldowns.get(player)) {
            Chat.SGPvPMessage(player, "Your invisibility is on cooldown");
            return;
        }
        Thread invisible = new Thread(new Invisible(player));
        invisible.start();
    }
    private class Invisible extends Thread {
        Player player;
        Invisible(Player p) { this.player = p; }
        public void run(){
            player.setInvisible(true);
            cooldowns.put(player, true);
            ProgressBar cooldownProgress = new ProgressBar(player, "Cooldown: ", BarColor.RED, BarStyle.SOLID, cooldown);
            ProgressBar invisibleDuration = new ProgressBar(player, "Invisibility: ", BarColor.WHITE, BarStyle.SOLID, duration);
            try {
                for (int s = 0; s < duration; s++) {
                    Thread.sleep(1);
                    cooldownProgress.increment();
                    invisibleDuration.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.setInvisible(false);
            try {
                for (int i = 0; i < cooldown - duration; i++) {
                    Thread.sleep(1);
                    cooldownProgress.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cooldowns.put(player, false);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        removeInvisibility(e.getEntity());
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e){
        removeInvisibility(e.getDamager());
    }
    private void removeInvisibility(Entity entity) {
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        if (!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;
        player.setInvisible(false);
    }
    /* Kit event handlers end here */
}
