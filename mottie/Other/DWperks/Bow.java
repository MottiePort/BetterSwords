package com.mottie.Other.DWperks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Bow {

    public Bow(Player p) {

        p.setWalkSpeed((float)0.234);
        p.setGlowing(true);
        p.setInvisible(true);

        Bukkit.getScoreboardManager().getMainScoreboard().getTeam("bow").addEntry(p.getDisplayName());

    }
}
