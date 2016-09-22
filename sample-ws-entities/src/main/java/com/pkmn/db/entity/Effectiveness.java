package com.pkmn.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.pkmn.db.entity.definitions.Multiplier;
import com.pkmn.db.entity.definitions.PokemonType;

@Entity
@IdClass(EffectivenessPK.class)
@Table(name = "effectiveness")
public class Effectiveness {

    @Id
    @Column(name = "attacking_type")
    @Enumerated(EnumType.STRING)
    private PokemonType attackingType;
    @Id
    @Column(name = "defending_type")
    @Enumerated(EnumType.STRING)
    private PokemonType defendingType;

    @Column(name = "multiplier")
    @Enumerated(EnumType.STRING)
    private Multiplier multiplier;

    public Effectiveness() {
    }

    public PokemonType getAttackingType() {
        return attackingType;
    }

    public void setAttackingType(PokemonType attackingType) {
        this.attackingType = attackingType;
    }

    public PokemonType getDefendingType() {
        return defendingType;
    }

    public void setDefendingType(PokemonType defendingType) {
        this.defendingType = defendingType;
    }

    public Multiplier getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Multiplier multiplier) {
        this.multiplier = multiplier;
    }

}
