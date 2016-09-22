package com.pkmn.db.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.pkmn.db.entity.definitions.PokemonType;

public class EffectivenessPK implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1395331677949826329L;

    private PokemonType attackingType;
    private PokemonType defendingType;

    public EffectivenessPK() {
    }

    public EffectivenessPK(PokemonType attackingType, PokemonType defendingType) {
        this.attackingType = attackingType;
        this.defendingType = defendingType;
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

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);

    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
