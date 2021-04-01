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
        return ChatColor.translateAlternateColorCodes('§', "§dHohto §5¦ &5Survival ");
    }


    @Override
    public List<String> getLines(Player player) {
        int health = (int) player.getHealth();
        List<String> lines = new ArrayList<>();
        lines.add("&5&m                          ");
        lines.add("&dSinä: &5" + player.getName());
        lines.add("&dPelaajia: &5" + Bukkit.getOnlinePlayers().size());
        lines.add("&dRaha: &5" + eco.format(eco.getBalance(player)));
        lines.add("&dTapot: &5" + player.getStatistic(Statistic.PLAYER_KILLS));
        lines.add("&dKuolemat: &5" + player.getStatistic(Statistic.DEATHS));
        lines.add("&dSydämet: &5" + health /2);
        lines.add("&5&m                          ");
        lines.add("&dHohto.ml");
        return lines;
    }
}