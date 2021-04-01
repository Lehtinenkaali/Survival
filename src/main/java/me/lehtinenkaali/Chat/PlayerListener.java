package me.lehtinenkaali.Chat;

import me.lehtinenkaali.Survival;
import org.bukkit.event.Listener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {

    public Survival plugin;

    public PlayerListener(Survival plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String g = this.plugin.perms.getPrimaryGroup(p);
        if (p.hasPermission("Core.color"))
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        String format = "";
        if (this.plugin.getConfig().getConfigurationSection("groups." + g) != null) {
            format = this.plugin.getConfig().getString("groups." + g + ".format");
        } else {
            format = this.plugin.getConfig().getString("default_format");
        }
        format = format.replace("{PLAYER_NAME}", p.getName());
        format = format.replace("{DISPLAY_NAME}", "%s");
        format = format.replace("{WORLD}", p.getWorld().getName());
        format = replaceVault(p, format);
        format = ChatColor.translateAlternateColorCodes('&', format);
        format = format.replace("%", "%%");
        format = format.replace("{MESSAGE}", "%2$s");
        e.setFormat(format);
    }

    public String replaceVault(Player p, String message) {
        String holders = message;
        String rank = null;
        String prefix = null;
        String suffix = null;
        prefix = this.plugin.chat.getPlayerPrefix(p);
        suffix = this.plugin.chat.getPlayerSuffix(p);
        rank = this.plugin.perms.getPrimaryGroup(p);
        holders = holders.replace("{PREFIX}", prefix);
        holders = holders.replace("{SUFFIX}", suffix);
        holders = holders.replace("{RANK}", rank);
        if (this.plugin.eco != null)
            holders = holders.replace("{RAHA}", Double.toString(this.plugin.eco.getBalance(p.getName())));
        return holders;
    }
}

