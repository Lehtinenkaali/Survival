package me.lehtinenkaali.COMMANDS;

import me.lehtinenkaali.Survival;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("tili"))
        if (sender instanceof Player)
            return true;
        Player p = (Player) sender;
        Economy eco = Survival.getEconomy();

        if (args.length == 0) {
            p.sendMessage("§eNykyinen saldosi on: " + eco.format(eco.getBalance(p)));
            }else if (p.hasPermission("CoreHQ.admin") && args.length == 2 && args[0].equalsIgnoreCase("Poista")) {
                double nosta_amount = Double.valueOf(args[1]);
                EconomyResponse response = eco.withdrawPlayer(p, nosta_amount);
                if (response.transactionSuccess()) {
                    p.sendMessage("§eTililtäsi on poistettu " + eco.format(response.amount));
                    p.sendMessage("Sinun uusi saldosi on: " + eco.format(response.balance));
                } else {
                    p.sendMessage("§eVirhe, Siirtäessä rahaa tililtäsi");
                    p.sendMessage(response.errorMessage);
                }
            } else if (p.hasPermission("CoreHQ.admin") && args.length == 2 && args[0].equalsIgnoreCase("lisää")) {
            double talleta_amount = Double.valueOf(args[1]);
            EconomyResponse response = eco.depositPlayer(p, talleta_amount);
            if (response.transactionSuccess()) {
                p.sendMessage("§eTilillesi on lisätty " + eco.format(response.amount));
                p.sendMessage("Sinun uusi saldosi on: " + eco.format(response.balance));
            } else {
                p.sendMessage("§eVirhe, Siirtäessä rahaa tilillesi");
                p.sendMessage(response.errorMessage);
            }
        }
        return false;
    }

}

