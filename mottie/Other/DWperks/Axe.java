package com.mottie.Other.DWperks;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Axe {

    public Axe(Player p) {

        p.setWalkSpeed((float)0.174);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999999, 0, false));

    }
}
