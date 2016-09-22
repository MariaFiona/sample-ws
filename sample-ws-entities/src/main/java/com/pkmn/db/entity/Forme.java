package com.pkmn.db.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pkmn.db.entity.definitions.PokemonType;

@Entity
@Table(name = "forme")
public class Forme {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Species species;
    //
    // @Column(name = "species_id")
    // private Long speciesId;

    @Column(name = "name")
    private String name;

    @Column(name = "primary_type")
    @Enumerated(EnumType.STRING)
    private PokemonType primaryType;

    @Column(name = "secondary_type")
    @Enumerated(EnumType.STRING)
    private PokemonType secondaryType;

    @Column(name = "base_hp")
    private Integer baseHp;

    @Column(name = "base_atk")
    private Integer baseAtk;

    @Column(name = "base_def")
    private Integer baseDef;

    @Column(name = "base_sp_atk")
    private Integer baseSpAtk;

    @Column(name = "base_sp_def")
    private Integer baseSpDef;

    @Column(name = "base_spd")
    private Integer baseSpd;

    @Column(name = "is_default_forme")
    private Boolean isDefaultForme;

    public Forme() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonType getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(PokemonType primaryType) {
        this.primaryType = primaryType;
    }

    public PokemonType getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(PokemonType secondaryType) {
        this.secondaryType = secondaryType;
    }

    public Integer getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(Integer baseHp) {
        this.baseHp = baseHp;
    }

    public Integer getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(Integer baseAtk) {
        this.baseAtk = baseAtk;
    }

    public Integer getBaseDef() {
        return baseDef;
    }

    public void setBaseDef(Integer baseDef) {
        this.baseDef = baseDef;
    }

    public Integer getBaseSpAtk() {
        return baseSpAtk;
    }

    public void setBaseSpAtk(Integer baseSpAtk) {
        this.baseSpAtk = baseSpAtk;
    }

    public Integer getBaseSpDef() {
        return baseSpDef;
    }

    public void setBaseSpDef(Integer baseSpDef) {
        this.baseSpDef = baseSpDef;
    }

    public Integer getBaseSpd() {
        return baseSpd;
    }

    public void setBaseSpd(Integer baseSpd) {
        this.baseSpd = baseSpd;
    }

    public Boolean getIsDefaultForme() {
        return isDefaultForme;
    }

    public void setIsDefaultForme(Boolean isDefaultForme) {
        this.isDefaultForme = isDefaultForme;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

}
