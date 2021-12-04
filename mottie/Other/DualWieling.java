package com.mottie.Other;

import com.mottie.Main;
import com.mottie.Other.DWperks.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class DualWieling implements Listener {

    FileConfiguration config = Main.plugin.getConfig();

    @EventHandler
    public void onHotbarSwitch(PlayerSwapHandItemsEvent e) {
        check(e.getPlayer());
    }

    @EventHandler
    public void onHotbarSwitch2(PlayerItemHeldEvent e) {
        check(e.getPlayer());
    }

    @EventHandler
    public void onOffhand(PlayerMoveEvent e) {
        check(e.getPlayer());
    }


    public void check(Player p) {
        Material mainHand = p.getInventory().getItemInMainHand().getType();
        Material offHand = p.getInventory().getItemInOffHand().getType();
        if (mainHand == Material.NETHERITE_SWORD || mainHand == Material.DIAMOND_SWORD || mainHand == Material.GOLDEN_SWORD ||mainHand == Material.IRON_SWORD ||mainHand == Material.STONE_SWORD || mainHand == Material.WOODEN_SWORD) {
            if (offHand == Material.NETHERITE_SWORD || offHand == Material.DIAMOND_SWORD || offHand == Material.GOLDEN_SWORD ||offHand == Material.IRON_SWORD ||offHand == Material.STONE_SWORD || offHand == Material.WOODEN_SWORD) {
                if (config.getString("Dual Wielding Perks." + ".Sword").equalsIgnoreCase("true")) {
                    new Sword(p);
                }
            }
            if (offHand == Material.NETHERITE_AXE || offHand == Material.DIAMOND_AXE || offHand == Material.GOLDEN_AXE ||offHand == Material.IRON_AXE ||offHand == Material.STONE_AXE || offHand == Material.WOODEN_AXE) {
                if (config.getString("Dual Wielding Perks." + ".Axe").equalsIgnoreCase("true")) {
                    new Axe(p);
                }
            }
            if (offHand == Material.SHIELD) {
                if (config.getString("Dual Wielding Perks." + ".Shield").equalsIgnoreCase("true")) {
                    new Shield(p);
                }
            }
            if (offHand == Material.BOW) {
                if (config.getString("Dual Wielding Perks." + ".Bow").equalsIgnoreCase("true")) {
                    new Bow(p);
                }
            }
            if (offHand == Material.TRIDENT) {
                if (config.getString("Dual Wielding Perks." + ".Trident").equalsIgnoreCase("true")) {
                    new Trident(p);
                }
            }
            if (offHand == Material.TOTEM_OF_UNDYING) {
                if (config.getString("Dual Wielding Perks." + ".Totem").equalsIgnoreCase("true")) {
                    new Totem(p);
                }
            }

        } else{
            new ResetAll(p);
        }
    }
}
