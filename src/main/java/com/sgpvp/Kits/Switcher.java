package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.GameItems;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Switcher extends Kit{
    public String kitName = "Switcher";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        ItemStack switcherBalls = GameItems.getSwitcherBalls(10);
        player.getInventory().addItem(switcherBalls);
        /* Kit functionality ends here */
    }

    List<UUID> switcherBalls = new ArrayList<>();

    @EventHandler
    public void markSnowball(ProjectileLaunchEvent event) {
        Player player = (Player) event.getEntity().getShooter();
        if (player == null) return;
        PlayerInventory inventory = player.getInventory();

        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return; // No access to kit
        if (!isSwitcherBall(inventory.getItemInMainHand()) &&
                !isSwitcherBall(inventory.getItemInOffHand())) return; // Not holding switcher ball

        switcherBalls.add(event.getEntity().getUniqueId());

    }

    @EventHandler
    public void switcher(ProjectileHitEvent event) {
        if (!switcherBalls.contains(event.getEntity().getUniqueId())) return;
        Snowball snowball = (Snowball) event.getEntity();
        Player shooter = (Player) snowball.getShooter();
        if (shooter == null) return;
        Entity hit = event.getHitEntity();
        if (hit == null) {
            Chat.SGPvPMessage(shooter, "You missed.");
            switcherBalls.remove(snowball.getUniqueId());
            return;
        }
        Chat.SGPvPMessage(shooter, "You hit something!");

        Location shooterLocation = shooter.getLocation();
        Location hitLocation = hit.getLocation();
        shooter.teleport(hitLocation);
        hit.teleport(shooterLocation);
        switcherBalls.remove(snowball.getUniqueId());

        if (!(hit instanceof Player)) return; // You didn't hit a player exit function
        Chat.SGPvPMessage(shooter, "&aYou switched locations with " +
                ((Player)hit).getDisplayName() + ".");
        Chat.SGPvPMessage((Player) hit, "&aYou switched locations with " +
                shooter.getDisplayName() + ".");
    }
    private boolean isSwitcherBall(ItemStack item) {
        if (item.getItemMeta() == null) return false;
        if (!item.getItemMeta().hasDisplayName()) return false;
        String switcherBallName = ChatColor.translateAlternateColorCodes ('&', "&b&lSwitcher Balls");
        return item.getItemMeta().getDisplayName().equals(switcherBallName);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKill(final EntityDeathEvent e){
        if (e.getEntity().getKiller() == null) return;
        Player killer = e.getEntity().getKiller();
        if (!PlayerData.playerHasKitActive(killer, kitName.toLowerCase())) return;
        //Chat.SGPvPMessage(killer, "");
        ItemStack switcherBalls = GameItems.getSwitcherBalls(2);
        killer.getInventory().addItem(switcherBalls);
    }
}