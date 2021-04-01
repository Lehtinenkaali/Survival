package me.lehtinenkaali.EVENTIT;

import me.lehtinenkaali.Survival;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {



    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Economy eco = Survival.getEconomy();
        Player p = e.getPlayer();
        Block b = e.getBlock();

        switch (b.getType()) {

            case STONE:
                eco.depositPlayer(p, 0.1);
                break;
            case AIR:
                eco.depositPlayer(p, 0.0);
                break;
            case DIAMOND:
                eco.depositPlayer(p, 30);
                break;
            case EMERALD:
                eco.depositPlayer(p, 50);
                break;
            case IRON_ORE:
                eco.depositPlayer(p, 5);
                break;
            case GOLD_ORE:
                eco.depositPlayer(p, 10);
                break;
            case NETHERRACK:
                eco.depositPlayer(p, 0.1);
                break;
            case ANCIENT_DEBRIS:
                eco.depositPlayer(p, 70);
                break;
            case NETHER_GOLD_ORE:
                eco.depositPlayer(p, 5);
                break;
            case COAL_BLOCK:
                eco.depositPlayer(p, 1);
                break;
            case OBSIDIAN:
                eco.depositPlayer(p, 3);
                break;
            case ANDESITE:
                eco.depositPlayer(p, 0.3);
                break;
            case DIORITE:
                eco.depositPlayer(p, 0.3);
                break;
            case GRANITE:
                eco.depositPlayer(p, 0.3);
                break;
            case SPAWNER:
                eco.depositPlayer(p, 100);
                break;
            case COAL_ORE:
                eco.depositPlayer(p, 1);
                break;
            case REDSTONE_ORE:
                eco.depositPlayer(p, 2);
                break;
            case LAPIS_ORE:
                eco.depositPlayer(p, 2);
                break;


        }


    }
}
