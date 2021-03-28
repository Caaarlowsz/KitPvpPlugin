package me.finneganmcguire.kit_pvp_minecraft.kits;

        import org.bukkit.command.Command;
        import org.bukkit.command.CommandSender;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - The command for this kit must be added to the plugin.yml
        - A reference to this kit must be added to the kits table
          in the Kit_PvP_Minecraft class.
        - If the kit contains event handlers then the kit must be
          registered in Kit_PvP_Minecraft (pluginManager.registerEvents)
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Switcher extends Kit{
    public String kitName = "Switcher";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);

        return false;
    }

    // Player shooter = (Player)event.getDamager();

}
