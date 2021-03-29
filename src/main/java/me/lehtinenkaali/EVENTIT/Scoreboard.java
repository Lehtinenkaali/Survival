package me.lehtinenkaali.EVENTIT;


import me.lehtinenkaali.Survival;
import me.missionary.board.provider.BoardProvider;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class Scoreboard implements BoardProvider {

    Economy eco = Survival.getEconomy();

    @Override
    public String getTitle(Player player) {
        return ChatColor.translateAlternateColorCodes('§', "§6CoreHQ §7¦ &7Survival ");
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&7&m                          ");
        lines.add("&eSinä: &6" + player.getName());
        lines.add("&ePelaajia: &6" + Bukkit.getOnlinePlayers().size());
        lines.add("&eRaha: &6" + eco.format(eco.getBalance(player)));
        lines.add("&eTapot: &6" + player.getStatistic(Statistic.PLAYER_KILLS));
        lines.add("&eKuolemat: &6" + player.getStatistic(Statistic.DEATHS));
        lines.add("&eSydämet: &6" + player.getHealth() /2);
        lines.add("&7&m                          ");
        lines.add("&dhohto.eu");
        return lines;
    }
}