package me.lehtinenkaali.COMMANDS;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Stats implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("tilastot")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Hyvä yritys");
                return true;
            }
            Player p = (Player) sender;
            if(args.length == 0) {
                // tilastot
                p.sendMessage("§eTee /tilastot {kuolemat/tapot}");
                return true;
            }
                if (args.length == 1 && command.getName().equalsIgnoreCase("Kuolemat")) {
                    p.sendMessage("§eSinulla on " + p.getStatistic(Statistic.DEATHS) + " Kuolemaa");
                }

                if (args.length == 1 && command.getName().equalsIgnoreCase("Tapot")) {
                    p.sendMessage("§e Sinulla on " + p.getStatistic(Statistic.PLAYER_KILLS) + "Tappoa");
                }

        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("Tapot");
            arguments.add("Kuolemat");
        }
        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
