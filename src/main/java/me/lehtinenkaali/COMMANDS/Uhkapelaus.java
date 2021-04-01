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
            player.sendMessage("§1Sinulta poistettiin 10 emeraldia onnistuneesti!");
            UhkapelausGUI.uhkapelausgui.spin(player);
            return true;
        } else {
            player.sendMessage("§1Tarvitset 10 emeraldia suorittaaksesi komennon.");
        }
        return false;
    }
}
