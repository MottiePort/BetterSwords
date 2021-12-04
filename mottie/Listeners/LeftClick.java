package com.mottie.Listeners;

import com.mottie.Main;
import com.mottie.Other.DWperks.Totem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class LeftClick implements Listener {
    FileConfiguration config = Main.plugin.getConfig();

    Map<String, Long> cooldowns = new HashMap<String, Long>();
    Map<String, Long> cooldowns2 = new HashMap<String, Long>();

    boolean noDupe = true;
    boolean noSpeed = true;
    boolean noSwim = true;

    Main plugin;
    public LeftClick(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void leftClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();

        if (p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD || p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD || p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD || p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD || p.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD || p.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (p.getInventory().getItemInOffHand().getType() == Material.BOW || p.getInventory().getItemInOffHand().getType() == Material.FIREWORK_ROCKET || p.getInventory().getItemInOffHand().getType() == Material.SHIELD || p.getInventory().getItemInOffHand().getType() == Material.FISHING_ROD || p.getInventory().getItemInOffHand().getType() == Material.FLINT_AND_STEEL || p.getInventory().getItemInOffHand().getType() == Material.TRIDENT || p.getInventory().getItemInOffHand().getType() == Material.CROSSBOW) {
                    return;
                }
                if (p.isSneaking()) {
                    if (noDupe) {
                        noDupe = false;
                        noSpeed = false;
                        noSwim = false;
                        Material offhand = p.getInventory().getItemInMainHand().getType();

                        p.getInventory().getItemInMainHand().setType(Material.SHIELD);
                        p.setInvulnerable(true);


                        new BukkitRunnable() {

                            @Override
                            public void run() {

                                p.getInventory().getItemInMainHand().setType(offhand);
                                p.setInvulnerable(false);
                                noDupe = true;
                                noSpeed = true;
                                noSwim = true;

                            }
                        }.runTaskLater(plugin, 2);
                    }
                }

                if (p.isSprinting()) {
                    if (noSwim) {
                        if (cooldowns.containsKey(p.getName())) {
                            long timeleft = (cooldowns.get(p.getName()) - (System.currentTimeMillis()));
                            if (timeleft > 0) {
                                return;
                            }
                        }
                        if (noSwim) {
                            if (!(p.isFlying() || p.isGliding() || p.isSwimming() || p.isRiptiding())) {
                                cooldowns.put(p.getName(), System.currentTimeMillis() + 2000);

                                p.damage(1);

                                Vector direction = p.getLocation().getDirection();
                                p.setVelocity(direction.multiply(1.2));
                                p.setGliding(true);
                            }
                        }
                    }
                }
            }
        }

        if (p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_AXE || p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE || p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE || p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE || p.getInventory().getItemInMainHand().getType() == Material.STONE_AXE || p.getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (p.isSprinting()) {
                    if (noSwim) {
                        if (cooldowns2.containsKey(p.getName())) {
                            long timeleft = (cooldowns2.get(p.getName()) - (System.currentTimeMillis()));
                            if (timeleft > 0) {
                                return;
                            }
                        }
                        if (noSwim) {
                            if (!(p.isFlying() || p.isGliding() || p.isSwimming() || p.isRiptiding())) {
                                cooldowns2.put(p.getName(), System.currentTimeMillis() + 2000);
                                p.damage(1);

                                Vector direction = p.getLocation().getDirection();
                                p.setVelocity(direction.multiply(-1.2));
                                p.setGliding(true);
                            }
                        }
                    }
                }
            }
        }
    }
}