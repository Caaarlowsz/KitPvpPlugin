package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        return true;
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */



        /* Kit functionality ends here */
    }
}
