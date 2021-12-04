package com.mottie.Other.DWperks;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class ResetAll {

    public ResetAll(Player p) {
        p.setWalkSpeed((float) 0.204);
        p.setMaxHealth(20);

        p.setGlowing(false);
        p.setInvisible(false);
        p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        p.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);

    }
}
