package me.lehtinenkaali.EVENTIT;

import me.lehtinenkaali.Survival;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        e.setJoinMessage("§eTervetuloa takasin§6 " + p.getDisplayName());

    }
}
