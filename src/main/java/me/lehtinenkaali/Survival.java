package me.lehtinenkaali;


import me.lehtinenkaali.COMMANDS.*;
import me.lehtinenkaali.Chat.PlayerListener;
import me.lehtinenkaali.EVENTIT.*;
import me.missionary.board.BoardManager;
import me.missionary.board.settings.BoardSettings;
import me.missionary.board.settings.ScoreDirection;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.IdentityHashMap;
import java.util.Map;

public class Survival extends JavaPlugin {
    public static Economy eco = null;
    private Map<Player, Instant> cropMessage = new IdentityHashMap<>();
    private BoardManager manager;
    public Chat chat = null;
    public Permission perms = null;
    public boolean placeholders = false;


    public Survival(UhkapelausGUI uhkapelausgui) {
        this.uhkapelausgui = uhkapelausgui;
    }

    public UhkapelausGUI uhkapelausgui;


    @Override

    public void onEnable() {

        getLogger().info("§dHohto survival käynnistyy");
        if (!setupEconomy()) {
            getLogger().info("§eSinulta puuttuu vault");
            return;
        }

        manager = new BoardManager(this, BoardSettings.builder().boardProvider(new Scoreboard()).scoreDirection(ScoreDirection.UP).build());

        cropMessage.clear();

        //Komennot
        getCommand("tilastot").setExecutor(new Stats());
        getCommand("pankki").setExecutor(new Bal());
        getCommand("Uhkapelaa").setExecutor(new Uhkapelaus(uhkapelausgui));



        //Eventit
        getServer().getPluginManager().registerEvents(new MobkillEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new SeedProt(cropMessage), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
    }

    public void onDisable() {
        this.chat = null;
        this.perms = null;
        eco = null;
        this.placeholders = false;
    }

    public void setup() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().info("   - Vault not found! Disabling plugin...");
            getServer().getPluginManager().disablePlugin((Plugin)this);
        } else {
            setupPermissions();
            setupChat();
            getLogger().info("   - Vault hooked!");
            if (setupEconomy()) {
                getLogger().info("   - Vault economy hooked!");
                getLogger().info(" ");
            }
            if (setupPlaceHolderAPI()) {
                getLogger().info("   - PlaceHolderAPI hooked!");
                getLogger().info(" ");
            }
            saveDefaultConfig();
            getServer().getPluginManager().registerEvents((Listener)new PlayerListener(this), (Plugin)this);
        }
    }

    public boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        this.chat = (Chat)rsp.getProvider();
        return (this.chat != null);
    }

    public boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        this.perms = (Permission)rsp.getProvider();
        return (this.perms != null);
    }


    public boolean setupPlaceHolderAPI() {
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI"))
            this.placeholders = true;
        return this.placeholders;
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }
    public static Economy getEconomy() {
        return eco;
    }

}
