package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Werewolf extends Kit{

    private Kit_PvP_Minecraft main;

    public void Werewolf(Kit_PvP_Minecraft main){
        this.main = main;
    }

    public String kitName = "Werewolf";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        return false;
    }
}
