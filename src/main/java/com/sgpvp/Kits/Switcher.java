package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Switcher extends Kit{
    public String kitName = "Switcher";
    public String switcherBallName = ChatColor.translateAlternateColorCodes ('&', "&b&lSwitcher Balls");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        ItemStack switcherBalls = new ItemStack(Material.SNOWBALL, 10);
        ItemMeta meta = switcherBalls.getItemMeta();
        meta.setDisplayName(switcherBallName);
        switcherBalls.setItemMeta(meta);

        player.getInventory().addItem(switcherBalls);


        /* Kit functionality ends here */
    }

    List<UUID> switcherBalls = new ArrayList<>();

    @EventHandler
    public void markSnowball(ProjectileLaunchEvent event) {
        Player player = (Player) event.getEntity().getShooter();
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
        Entity hit = event.getHitEntity();
        if (hit == null) {
            GameVariables.SGPvPMessage(shooter, "You missed.");
            switcherBalls.remove(snowball.getUniqueId());
            return;
        }
        GameVariables.SGPvPMessage(shooter, "You hit something!");

        Location shooterLocation = shooter.getLocation();
        Location hitLocation = hit.getLocation();
        shooter.teleport(hitLocation);
        hit.teleport(shooterLocation);
        switcherBalls.remove(snowball.getUniqueId());

        if (!(hit instanceof Player)) return; // You didn't hit a player exit function
        GameVariables.SGPvPMessage(shooter, "&aYou switched locations with " +
                ((Player)hit).getDisplayName() + ".");
        GameVariables.SGPvPMessage((Player) hit, "&aYou switched locations with " +
                shooter.getDisplayName() + ".");
    }
    private boolean isSwitcherBall(ItemStack item) {
        if (!item.getItemMeta().hasDisplayName()) return false;
        return item.getItemMeta().getDisplayName().equals(switcherBallName);
    }
}