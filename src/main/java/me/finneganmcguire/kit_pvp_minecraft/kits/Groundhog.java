package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.*;
import java.util.Arrays;

import static me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft.world;

public class Groundhog implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void Groundhog (Kit_PvP_Minecraft main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ItemStack slime_ball = new ItemStack(Material.SLIME_BALL, 2);
        ItemMeta slime_ball_data = slime_ball.getItemMeta();
        slime_ball_data.setDisplayName(ChatColor.BOLD + "The Groundhog's Grounding Gifts");
        slime_ball_data.setLore(Arrays.asList("Teleports 20 blocks below current level into a box"));
        slime_ball.setItemMeta(slime_ball_data);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Kit_PvP_Minecraft.canChangeKit) {
                // REMOVE POTION EFFECTS
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }

                Inventory inv = player.getInventory();
                inv.clear();

                //Finds player in hashmap database --> Changes Kit To milkman
                PlayerStorage.setPlayerNewKit(player.getPlayer(), "Groundhog");

                inv.addItem(slime_ball);
                player.sendMessage("You Have Chosen: " + ChatColor.BOLD + " GROUNDHOG! ");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.groundhog_Description);
            } else {
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
            }

        } else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }
        return false;
    }

    @EventHandler
    public void onSlimeBallUse(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        boolean holdingSlimeBall = player.getInventory().getItemInMainHand().getType() == Material.SLIME_BALL;
        ItemStack slime_ball = event.getItemDrop().getItemStack();

        if (holdingSlimeBall == true) {
            //Enable console
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

            //Clearing blocks
            for (int clearY = -22; clearY < -19; clearY++) {
                for (int clearX = -1; clearX < 1; clearX++) {
                    for (int clearZ = -1; clearZ < 1; clearZ++) {
                        Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(%d, %d, %d), air, replace))", clearX, clearY, clearZ));
                    }
                }
            }

            //Adding floor
            for (int z = -1; z < 1; z++) {
                for (int x = -1; x < 1; x++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(%d, -22, %d), brick, replace))", x, z));
                }
            }

            //Adding ceiling
            for (int z2 = -1; z2 < 1; z2++) {
                for (int x2 = -1; x2 < 1; x2++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(%d, -22, %d), brick, replace))", x2, z2));
                }
            }

            //Adding walls
            for (int y3 = -22; y3 < -19; y3++) {
                for (int z3 = -1; z3 < 1; z3++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(-2, %d, %d), brick, replace))", y3, z3));
                }
            }

            for (int y4 = -22; y4 < -19; y4++) {
                for (int z4 = -1; z4 < 1; z4++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(-2, %d, %d), brick, replace))", y4, z4));
                }
            }

            for (int y5 = -22; y5 < -19; y5++) {
                for (int x5 = -1; x5 < 1; x5++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(%d, %d, 2), brick, replace))", x5, y5));
                }
            }

            for (int y6 = -22; y6 < -19; y6++) {
                for (int x6 = -1; x6 < 1; x6++) {
                    Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(%d, %d, -2), brick, replace))", x6, y6));
                }
            }

            //Places and ignites TNT to indicate slime ball use
            Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(playerLocation), primedtnt, replace))"));

            //Places and ignites TNT to indicate slime ball use
            Bukkit.getServer().dispatchCommand(console, String.format("setblock(pos(playerLocation), primedtnt, replace))"));
            Location location = player.getLocation();
            Location loc = new Location(world, 0, -20, 0);
            player.teleport(loc);


        }
    }
}

