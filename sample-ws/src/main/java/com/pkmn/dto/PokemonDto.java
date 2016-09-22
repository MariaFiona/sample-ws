package com.pkmn.dto;

public class PokemonDto {
    private String id;
    private String nickname;

    private Integer level;

    private Integer hp;
    private Integer atk;
    private Integer def;
    private Integer spAtk;
    private Integer spDef;
    private Integer spd;

    private String nature;
    private SpeciesDto species;
    private String currentForme;

    private String primaryType;
    private String secondaryType;

    public PokemonDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(Integer spAtk) {
        this.spAtk = spAtk;
    }

    public Integer getSpDef() {
        return spDef;
    }

    public void setSpDef(Integer spDef) {
        this.spDef = spDef;
    }

    public Integer getSpd() {
        return spd;
    }

    public void setSpd(Integer spd) {
        this.spd = spd;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public SpeciesDto getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesDto species) {
        this.species = species;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public String getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(String secondaryType) {
        this.secondaryType = secondaryType;
    }

    public String getCurrentForme() {
        return currentForme;
    }

    public void setCurrentForme(String currentForme) {
        this.currentForme = currentForme;
    }

}
