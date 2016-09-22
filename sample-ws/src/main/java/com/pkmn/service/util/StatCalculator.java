package com.pkmn.service.util;

import com.pkmn.db.entity.definitions.Nature;
import com.pkmn.db.entity.definitions.Stat;

public class StatCalculator {

    public StatCalculator() {

    }

    public static int calculateHP(int baseHp, int hpIv, int hpEv, int level) {
        double hp = 0;

        if (baseHp == 1) {
            // It's Shedinja
            return 1;
        }
        hp = Math.floor((((2 * baseHp) + hpIv + (((double) hpEv) / 4)) * level) / (double) 100) + level + 10;
        return (int) hp;
    }

    public static int calculateStat(Stat stat, Nature nature, int baseStat, int iv, int ev, int level) {
        double natureMultiplier = 1;

        if (stat.equals(nature.getIncreased())) {
            natureMultiplier = 1.1;
        } else if (stat.equals(nature.getDecreased())) {
            natureMultiplier = 0.9;
        }

        double calculatedStat = Math.floor((Math.floor((((2 * baseStat) + iv + ((double) ev / (double) 4)) * level)
                        / ((double) 100)) + 5)
                        * natureMultiplier);
        return (int) calculatedStat;
    }
}
