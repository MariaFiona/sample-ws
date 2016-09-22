package com.pkmn.db.entity.definitions;

import static com.pkmn.db.entity.definitions.Stat.ATTACK;
import static com.pkmn.db.entity.definitions.Stat.DEFENSE;
import static com.pkmn.db.entity.definitions.Stat.SPECIAL_ATTACK;
import static com.pkmn.db.entity.definitions.Stat.SPECIAL_DEFENSE;
import static com.pkmn.db.entity.definitions.Stat.SPEED;

public enum Nature {
    HARDY(null, null),
    LONELY(ATTACK, DEFENSE),
    BRAVE(ATTACK, SPEED),
    ADAMANT(ATTACK, SPECIAL_ATTACK),
    NAUGHTY(ATTACK, SPECIAL_DEFENSE),
    BOLD(DEFENSE, ATTACK),
    DOCILE(null, null),
    RELAXED(DEFENSE, SPEED),
    IMPISH(DEFENSE, SPECIAL_ATTACK),
    LAX(DEFENSE, SPECIAL_DEFENSE),
    TIMID(SPEED, ATTACK),
    HASTY(SPEED, DEFENSE),
    SERIOUS(null, null),
    JOLLY(SPEED, SPECIAL_ATTACK),
    NAIVE(SPEED, SPECIAL_DEFENSE),
    MODEST(SPECIAL_ATTACK, ATTACK),
    MILD(SPECIAL_ATTACK, DEFENSE),
    QUIET(SPECIAL_ATTACK, SPEED),
    BASHFUL(null, null),
    RASH(SPECIAL_ATTACK, SPECIAL_DEFENSE),
    CALM(SPECIAL_DEFENSE, ATTACK),
    GENTLE(SPECIAL_DEFENSE, DEFENSE),
    SASSY(SPECIAL_DEFENSE, SPEED),
    CAREFUL(SPECIAL_DEFENSE, ATTACK),
    QUIRKY(null, null);

    private Stat increased;
    private Stat decreased;

    private Nature(Stat increased, Stat decreased) {
        this.increased = increased;
        this.decreased = decreased;

    }

    public Stat getIncreased() {
        return increased;
    }

    public void setIncreased(Stat increased) {
        this.increased = increased;
    }

    public Stat getDecreased() {
        return decreased;
    }

    public void setDecreased(Stat decreased) {
        this.decreased = decreased;
    }
}
