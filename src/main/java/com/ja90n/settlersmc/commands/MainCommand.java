package com.ja90n.settlersmc.commands;

import com.ja90n.settlersmc.SettlersMC;
import com.ja90n.settlersmc.enums.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private SettlersMC settlersMC;

    public MainCommand(SettlersMC settlersMC){
        this.settlersMC = settlersMC;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "This command can only be used by a player");
        } else {
            Player player = (Player) sender;
            if (args.length == 1){
                settlersMC.getSettlersManager().createSettler(player.getLocation(), Team.PINK);
            } else {
                settlersMC.getSettlersManager().setTarget(player.getLocation());
            }
        }
        return false;
    }
}
