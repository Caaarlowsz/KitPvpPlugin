package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.GameItems;
import com.sgpvp.GameLogic.ProgressBar;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - The command for this kit must be added to the plugin.yml
        - A reference to this kit must be added to the kits table
          in the Kit_PvP_Minecraft class.
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Thor extends Kit{
    public String kitName = "Thor"; // Try to keep this the same as the class name <3
    public int axeCooldown = 5 * 1000;
    public int maxDistance = 20;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (!GameVariables.canChangeKit) return false;

        /* Kit functionality starts here */
        if (sender instanceof Player && GameVariables.canChangeKit) {
            Player player = (Player) sender;
            Inventory inv = player.getInventory();
            inv.addItem(GameItems.getThorAxe());
        }
        /* Kit functionality ends here */

        return false;
    }
    /* Kit event handlers start here */
    HashMap<Player, Boolean> cooldowns = new HashMap<>();
    @EventHandler
    public void AxeSwing(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        if (!player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_AXE)) return;
        if (cooldowns.containsKey(player) && cooldowns.get(player)) return;
        cooldowns.put(player, true);
        Location ligtningLocation = player.getTargetBlockExact(maxDistance).getLocation();
        GameVariables.world.strikeLightning(ligtningLocation);
        Thread axeSwung = new Thread(new AxeSwing(player));
        axeSwung.start();
    }
    private class AxeSwing extends Thread {
        Player player;
        AxeSwing(Player p) { this.player = p; }
        public void run(){
            ProgressBar cooldownBar = new ProgressBar(player, "Cooldown", BarColor.RED, BarStyle.SOLID, axeCooldown);
            for (int i = 0; i < axeCooldown; i++) {
                try { Thread.sleep(1);
                    cooldownBar.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace(); }
            }
            cooldownBar.removePlayer(player);
            cooldowns.put(player, false);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(final EntityDamageEvent e){
        if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
        if (!PlayerData.playerHasKitActive((Player) e.getEntity(), kitName.toLowerCase())) return;
        if(e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING) ||
                e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) ||
                e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
            e.setCancelled(true);
    }
    /* Kit event handlers end here */
}
