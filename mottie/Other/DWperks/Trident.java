package com.mottie.Other.DWperks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Trident {

    public Trident(Player p) {

        p.setWalkSpeed((float)0.300);
        Location loc = p.getLocation();
        if (loc.getBlock().getType() == Material.WATER) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 99999999, 0, false));
        }
    }
}
