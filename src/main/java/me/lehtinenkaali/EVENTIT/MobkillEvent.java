package me.lehtinenkaali.EVENTIT;

import me.lehtinenkaali.Survival;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class MobkillEvent implements Listener {

    public MobkillEvent(Survival plugin) {
        this.plugin = plugin;
    }

    public Survival plugin;

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        if (e.getEntity() instanceof Mob) {
            Player p = e.getEntity().getKiller();
            if (p == null)
                return;
            Random r = new Random();
            int amount = r.nextInt(10);
            plugin.eco.depositPlayer(p, amount);
            p.sendMessage("§d" + amount + "§5€ §dlisätty tilillesi");
        }


    }

}
