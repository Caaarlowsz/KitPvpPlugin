package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Pyromancer extends Kit{

    private Kit_PvP_Minecraft main;

    public void Pyromancer(Kit_PvP_Minecraft main) {
        this.main = main;
    }
    public String kitName = "Pyromancer";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(GameVariables.canChangeKit){
                // Class Items
                ItemStack lavabucket = new ItemStack(Material.LAVA_BUCKET);
                ItemStack flintandsteel = new ItemStack(Material.FLINT_AND_STEEL);

                Inventory inv = player.getInventory();
                inv.addItem(lavabucket, flintandsteel);

            }
        }
        return false;
    }
}

