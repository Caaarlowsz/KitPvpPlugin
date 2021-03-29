package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Sunwalker extends Kit {
    private Kit_PvP_Minecraft main;
    public String kitName = "Sunwalker";
    public void Sunwalker(Kit_PvP_Minecraft main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        return false;
    }
}
