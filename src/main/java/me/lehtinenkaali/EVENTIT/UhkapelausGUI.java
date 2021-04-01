package me.lehtinenkaali.EVENTIT;


import me.lehtinenkaali.Survival;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UhkapelausGUI implements Listener{
    List<Inventory> invs = new ArrayList<Inventory>();

    public static ItemStack[] contents;
    private int ItemIndex = 0;

    public static UhkapelausGUI uhkapelausgui;


    public void shuffle(Inventory inv) {
        if (contents == null) {
            ItemStack[] items = new ItemStack[10];

            items[0] = new ItemStack(Material.DIRT, 32);
            items[1] = new ItemStack(Material.DIAMOND, 3);
            items[2] = new ItemStack(Material.IRON_NUGGET, 40);
            items[3] = new ItemStack(Material.DIAMOND, 32);
            items[4] = new ItemStack(Material.EMERALD, 5);
            items[5] = new ItemStack(Material.ENDER_CHEST, 1);
            items[6] = new ItemStack(Material.ENDER_EYE, 6);
            items[7] = new ItemStack(Material.CAMPFIRE, 2);
            items[8] = new ItemStack(Material.COW_SPAWN_EGG, 3);
            items[9] = new ItemStack(Material.NETHER_STAR, 1);

            contents = items;
        }

        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);
        for (int i = 0; i < startingIndex; i++) {
            for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
                inv.setItem(itemstacks, contents[(itemstacks + ItemIndex) % contents.length]);
            }
            ItemIndex++;
        }

        ItemStack item = new ItemStack(Material.HOPPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§l↓");
        item.setItemMeta(meta);
        inv.setItem(4, item);
    }
    public void spin(final Player player) {
        uhkapelausgui = this;
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_AQUA + "§lOnnea peliin");
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);

        Random r = new Random();
        double seconds = 7.0 + (12.0 - 7.0) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean done = false;
            @Override
            public void run() {
                if (done)
                    return;
                ticks++;
                delay += 1 /(20 *seconds);
                if (ticks > delay * 10) {
                    ticks = 0;

                    for (int itemstacks = 9; itemstacks < 18; itemstacks++)
                        inv.setItem(itemstacks, contents[(itemstacks + ItemIndex) % contents.length]);

                    ItemIndex++;

                    if (delay>= .5) {
                        done = true;
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                ItemStack item = inv.getItem(13);
                                player.getInventory().addItem(item);
                                player.updateInventory();
                                player.closeInventory();
                                cancel();
                            }
                        }.runTaskLater(Survival.getPlugin(Survival.class), 50);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(Survival.plugin, 1, 1);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!invs.contains(e.getInventory())) {
                return;
        }
       e.setCancelled(true);
        return;
    }
}
