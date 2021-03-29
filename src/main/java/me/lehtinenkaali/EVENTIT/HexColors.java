package me.lehtinenkaali.EVENTIT;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexColors implements Listener {
    public final Pattern pattern = Pattern.compile("#[a-fA-f0-9]{6}");

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setMessage(format(e.getMessage()));
    }

    public String format (String msg)   {
        if(Bukkit.getVersion().contains("1.16")){
            Matcher match = pattern.matcher(msg);
            while(match.find()){
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
