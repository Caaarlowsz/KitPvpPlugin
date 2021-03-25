package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Cultivator extends Kit {
    public String kitName = "Cultivator";

    private Kit_PvP_Minecraft main;

    public void Cultivator(Kit_PvP_Minecraft main) {
        this.main = main;
    }

    public List<Material> seeds;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        ItemStack stone_hoe = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta stone_hoe_data = stone_hoe.getItemMeta();
        stone_hoe_data.setDisplayName(ChatColor.BOLD + "The Cultivator's Powerful Plow");
        stone_hoe_data.setLore(Arrays.asList("Instantly grows any crop or sapling"));
        stone_hoe.setItemMeta(stone_hoe_data);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Kit_PvP_Minecraft.canChangeKit) {
                //Finds player in hashmap database - Changes kit to Cultivator
                PlayerStorage.setPlayerNewKit(player.getPlayer(), "cultivator");

                Inventory inv = player.getInventory();
                inv.addItem(stone_hoe);

            }
        }
        return false;
    }


    @EventHandler
    //Instant Oak Tree
    public void onSeedPlace(BlockPlaceEvent event) {
        if(PlayerStorage.playerHasKitActive(event.getPlayer(), "cultivator")) {
            Material material = event.getBlockPlaced().getType();
            CropState cropState = CropState.RIPE;
        }
    }
}
