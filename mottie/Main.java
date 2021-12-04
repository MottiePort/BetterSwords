package com.mottie;

import com.mottie.Listeners.LeftClick;
import com.mottie.Other.DualWieling;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class Main extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    public static Main plugin;

    public void loadConfig() {

        config.addDefault("Dual Wielding Perks." + ".Axe", true);
        config.addDefault("Dual Wielding Perks." + ".Bow", true);
        config.addDefault("Dual Wielding Perks." + ".Shield", true);
        config.addDefault("Dual Wielding Perks." + ".Sword", true);
        config.addDefault("Dual Wielding Perks." + ".Totem", true);
        config.addDefault("Dual Wielding Perks." + ".Trident", true);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onEnable() {

        plugin = this;

        loadConfig();

        this.getServer().getPluginManager().registerEvents(this, this);

        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        if (board.getTeam("bow") == null) {
            Scoreboard board2 = Bukkit.getScoreboardManager().getMainScoreboard();
            Team team = board2.registerNewTeam("bow");
            team.setColor(ChatColor.GRAY);

        }

        getServer().getPluginManager().registerEvents(new LeftClick(this), this);
        getServer().getPluginManager().registerEvents(new DualWieling(), this);


    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onRodLand(ProjectileHitEvent e) {
        if(!(e.getEntityType() == EntityType.FISHING_HOOK)) {
            return;
        }
        for(Entity entity : Bukkit.getWorld(e.getEntity().getLocation().getWorld().getName()).getNearbyEntities(e.getEntity().getLocation(), 0.2, 0.2, 0.2)) {
            if(!(entity instanceof Player)) {
                continue;
            }
            FishHook hook = (FishHook) e.getEntity();
            Player rodder = (Player) hook.getShooter();
            Player player = (Player) entity;
            if(player.getName().equalsIgnoreCase(rodder.getName())) {
                continue;
            }
            player.damage(0.2);
            Location loc = player.getLocation().add(0, 0.5, 0);
            player.teleport(loc);
            player.setVelocity(rodder.getLocation().getDirection().multiply(+0.4));
            hook.remove();
            rodder.updateInventory();
            return;
        }
    }
}
