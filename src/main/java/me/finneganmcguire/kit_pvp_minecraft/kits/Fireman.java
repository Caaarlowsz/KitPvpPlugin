package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fireman extends Kit{
    public String kitName = "Fireman";
    private Kit_PvP_Minecraft main;

    public void Fireman(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (GameVariables.canChangeKit) {
                Inventory inv = player.getInventory();

                //Fireman Item Stuff
                ItemStack firemanWater = new ItemStack(Material.WATER_BUCKET);
                ItemMeta firemanWater_Data = firemanWater.getItemMeta();
                firemanWater.setItemMeta(firemanWater_Data);

                // Potion Effects
                int lengthOfResistance = 1000000;
                int strengthOfResistance = 0;
                PotionEffect firemanResistance = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, lengthOfResistance, strengthOfResistance);

                player.getInventory().addItem(firemanWater);
                firemanResistance.apply(player);

            }
        }
        return false;
    }
}
