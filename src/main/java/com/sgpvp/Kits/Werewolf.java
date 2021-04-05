package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class  Werewolf extends Kit{

    private com.sgpvp.main main;

    public void Werewolf(com.sgpvp.main main){
        this.main = main;
    }

    public String kitName = "Werewolf";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (!GameVariables.canChangeKit) return false;
        return false;
    }
}
