package com.sgpvp.Kits;

import com.sgpvp.GameLogic.GameItems;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Groundhog extends Kit{
    public String kitName = "Groundhog";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        ItemStack groundhogShovel = GameItems.getGroundhogShovel();
        Inventory inv = player.getInventory();
        inv.addItem(groundhogShovel);
        /* Kit functionality ends here */
    }

    private boolean isGroundhogShovel(ItemStack item) {
        if (item.getItemMeta() == null) return false;
        if (!item.getItemMeta().hasDisplayName()) return false;
        String groundhogShovelName = ChatColor.translateAlternateColorCodes ('&', "&a&lGroundhog's Spade");
        return item.getItemMeta().getDisplayName().equals(groundhogShovelName);
    }

    @EventHandler
    public void onDig(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (!isGroundhogShovel(heldItem)) return;
        if (playerLocation.getBlockY() < 30) return;
        World world = player.getWorld();
        TNTPrimed tnt = world.spawn(playerLocation, TNTPrimed.class);
        tnt.setFuseTicks(200);

        Location bunker = playerLocation.add(0, -20, 0);
        double bx = bunker.getX(), by = bunker.getY(), bz = bunker.getZ();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    Location block = new Location(world, bx+i, by+j, bz+k);
                    block.getBlock().setType(Material.AIR);
                    if (i == 0 || i == 4 || j == 0 || j == 4 || k == 0 || k == 4)
                        block.getBlock().setType(Material.BRICK);
                }
            }
        }
        player.teleport(bunker);
    }
}

