package com.sgpvp.Kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Recycler extends Kit{

    private com.sgpvp.main main;
    public String kitName = "Recycler";
    public void Recycler(com.sgpvp.main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        return false;
    }
}
