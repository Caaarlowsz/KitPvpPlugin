package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - The command for this kit must be added to the plugin.yml
        - A reference to this kit must be added to the kits table
          in the Kit_PvP_Minecraft class.
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class KitTemplate extends Kit{
    public String kitName = "ExampleKitName"; // Try to keep this the same as the class name <3

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */



        /* Kit functionality ends here */
    }
    /* Kit event handlers start here */
    // Used in some kits to add additional functionality
    // if (!PlayerData.playerHasKitActive(p, kitName.toLowerCase())) return; // Critical line

    /* Kit event handlers end here */
}
