package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class Groundhog extends Kit{
    public String kitName = "Groundhog";
    private com.sgpvp.main main;

    public void Groundhog(com.sgpvp.main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */



        /* Kit functionality ends here */
    }

    @EventHandler
    public void onSlimeBallUse(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getItem().getType().equals(Material.SLIME_BALL)) {

            //Removes slime ball
            int items = event.getPlayer().getInventory().getItemInMainHand().getAmount();
            items--;
            event.getPlayer().getInventory().getItemInMainHand().setAmount(items);

            //Enable console
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

            //Example code
            Location playerLocation = event.getPlayer().getLocation();
            Location someBlockLocation = new Location(GameVariables.world,
                    playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ());
            someBlockLocation.getBlock().setType(Material.GRASS_BLOCK);

            //Clearing blocks
            for (int clearY = -22; clearY < -19; clearY++) {
                for (int clearX = -1; clearX < 1; clearX++) {
                    for (int clearZ = -1; clearZ < 1; clearZ++) {
                        Bukkit.getServer().dispatchCommand(console, String.format("setblock %d %d %d minecraft:air replace", clearX, clearY, clearZ));
                    }
                }
            }

            //Adding floor
            for (int z = -1; z < 1; z++) {
                for (int x = -1; x < 1; x++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock %d -22 %d minecraft:bricks replace", x, z));
                }
            }

            //Adding ceiling
            for (int z2 = -1; z2 < 1; z2++) {
                for (int x2 = -1; x2 < 1; x2++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock %d -22 %d minecraft:bricks replace", x2, z2));
                }
            }

            //Adding walls
            for (int y3 = -22; y3 < -19; y3++) {
                for (int z3 = -1; z3 < 1; z3++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock -2 %d %d minecraft:bricks replace", y3, z3));
                }
            }

            for (int y4 = -22; y4 < -19; y4++) {
                for (int z4 = -1; z4 < 1; z4++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock -2 %d %d minecraft:bricks replace", y4, z4));
                }
            }

            for (int y5 = -22; y5 < -19; y5++) {
                for (int x5 = -1; x5 < 1; x5++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock %d %d 2 minecraft:bricks replace", x5, y5));
                }
            }

            for (int y6 = -22; y6 < -19; y6++) {
                for (int x6 = -1; x6 < 1; x6++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock %d %d -2 minecraft:bricks replace", x6, y6));
                }
            }

            //Places and ignites TNT to indicate slime ball use
            Bukkit.getServer().dispatchCommand(console, "setblock ~ ~ ~ minecraft:tnt replace");

            //Places and ignites TNT to indicate slime ball use
            Player player = event.getPlayer();

            Bukkit.getServer().dispatchCommand(console, "setblock ~ ~ ~ minecraft:tnt replace");
            Location location = player.getLocation();

            Location loc = new Location(GameVariables.world, 0, -20, 0);
            player.teleport(loc);
        }
    }
}

