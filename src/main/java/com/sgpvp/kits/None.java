
package com.sgpvp.kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class None extends Kit{
    public String kitName = "None";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        super.onCommand(sender, command, label, args);

        /* Kit functionality starts here */



        /* Kit functionality ends here */

        return false;
    }
    /* Kit event handlers start here */
    // Used in some kits to add additional functionality
    // if (!PlayerStorage.playerHasKitActive(p, kitName.toLowerCase())) return; // Critical line

    /* Kit event handlers end here */
}
