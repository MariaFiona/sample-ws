package com.pkmn.service.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pkmn.db.entity.definitions.Nature;
import com.pkmn.db.entity.definitions.Stat;

public class StatCalculatorTest {

    /**
     * Tests getting the HP of a Pokemon given its various stats and modifiers.
     * 
     * http://bulbapedia.bulbagarden.net/wiki/Statistic#Example_2
     * 
     * In this example, a level 78 Garchomp with 24 HP IV and 24 HP EV
     */
    @Test
    public void testHP() {
        int hp = StatCalculator.calculateHP(108, 24, 74, 78);
        Assert.assertEquals(hp, 289);
    }

    /**
     * Tests getting the stats of a pokemon given its various stats and modifiers.
     * 
     * See above HP test for details
     */
    @Test
    public void testGetStats() {
        int baseAtk = 130;
        int atkIv = 12;
        int atkEv = 195;

        int calcAtk = StatCalculator.calculateStat(Stat.ATTACK, Nature.ADAMANT, baseAtk, atkIv, atkEv, 78);

        Assert.assertEquals(calcAtk, 280);

        int baseSpAtk = 80;
        int spAtkIv = 16;
        int spAtkEv = 48;

        int calcSpAtk = StatCalculator.calculateStat(Stat.SPECIAL_ATTACK, Nature.ADAMANT, baseSpAtk, spAtkIv, spAtkEv, 78);

        // answer may be off-by-one due to rounding accdg to Bulbapedia
        Assert.assertEquals(calcSpAtk, 136, 1);
    }
}
