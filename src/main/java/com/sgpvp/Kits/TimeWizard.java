package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.GameItems;
import com.sgpvp.GameLogic.ProgressBar;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;

public class TimeWizard extends Kit{

    public String kitName = "TimeWizard";
    public int duration = 3 * 1000;
    public int freezeRadius = 7;
    public int cooldown = 45 * 1000;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        Inventory inv = player.getInventory();
        inv.addItem(GameItems.getTimeClock());
        /* Kit functionality ends here */
    }
    HashMap<Player, Boolean> cooldowns = new HashMap<>();
    HashMap<Entity, Boolean> frozen = new HashMap<>();
    @EventHandler
    public void ClockActivated(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        if (!player.getInventory().getItemInMainHand().getType().equals(Material.CLOCK)) return;
        if (cooldowns.containsKey(player) && cooldowns.get(player)) return;
        Chat.SGPvPMessage(player, "&5Time freeze activated!");
        List<Entity> nearby = player.getNearbyEntities(freezeRadius, freezeRadius, freezeRadius);
        Thread freeze = new Thread(new Freeze(player, nearby));
        freeze.start();
    }
    private class Freeze extends Thread {
        Player player;
        List<Entity> nearby;
        Freeze(Player p, List<Entity> nearby) { this.player = p; this.nearby = nearby; }
        public void run(){
            cooldowns.put(player, true);
            for (Entity e : nearby) {
                if (e.getType().equals(EntityType.PLAYER)) {
                    Player p = (Player) e;
                    if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                        Chat.SGPvPMessage(player, "You froze " + e.getName());
                        frozen.put(p, true);
                        p.setAllowFlight(true);
                    }
                }
            }
            ProgressBar freezeProgress = new ProgressBar(player, "Freeze duration: ", BarColor.BLUE, BarStyle.SOLID, duration);
            ProgressBar cooldownProgress = new ProgressBar(player, "Cooldown: ", BarColor.GREEN, BarStyle.SOLID, cooldown);
            for (int i = 0; i < duration; i++) {
                try {
                    Thread.sleep(1);
                    freezeProgress.increment();
                    cooldownProgress.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            freezeProgress.removePlayer(player);
            for (Entity e : nearby) {
                if (e.getType().equals(EntityType.PLAYER)) {
                    Player p = (Player) e;
                    frozen.put(p, false);
                    p.setAllowFlight(false);
                }
            }
            for (int i = 0; i < cooldown - duration; i++) {
                try {
                    Thread.sleep(1);
                    cooldownProgress.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cooldownProgress.removePlayer(player);
            cooldowns.put(player, false);
        }
    }
    @EventHandler
    public void frozen(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!frozen.containsKey(player)) return;
        if (!frozen.get(player)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void damaged(EntityDamageEvent e) {
        if (!e.getEntityType().equals(EntityType.PLAYER)) return;
        Player player = (Player) e.getEntity();
        if (frozen.containsKey(player)) frozen.put(player, false);
    }
}
