package me.lehtinenkaali.COMMANDS;

import me.lehtinenkaali.EVENTIT.UhkapelausGUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Uhkapelaus implements CommandExecutor {

    public Uhkapelaus(UhkapelausGUI uhkapelausgui) {
        this.uhkapelausgui = uhkapelausgui;
    }

    public UhkapelausGUI uhkapelausgui;



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Haha");
            return true;
        }
        Player player = (Player) sender;
        ItemStack fee = new ItemStack(Material.EMERALD);
        fee.setAmount(10);
        if (player.getInventory().getItemInMainHand().isSimilar(fee)) {
            player.getInventory().removeItem(fee);
            uhkapelausgui.spin(player);
            return true;
        }
        return false;
    }
}
