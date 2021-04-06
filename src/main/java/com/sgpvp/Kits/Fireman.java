package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
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
    private com.sgpvp.main main;

    public void Fireman(com.sgpvp.main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

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


        /* Kit functionality ends here */
    }
}
