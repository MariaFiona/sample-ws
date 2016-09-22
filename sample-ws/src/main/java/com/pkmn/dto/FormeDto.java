package com.pkmn.dto;

public class FormeDto extends Dto {
    private SpeciesDto species;

    private String primaryType;
    private String secondaryType;

    private Integer baseHp;
    private Integer baseAtk;
    private Integer baseDef;
    private Integer baseSpAtk;
    private Integer baseSpDef;
    private Integer baseSpd;

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

    public SpeciesDto getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesDto species) {
        this.species = species;
    }

    public static FormeDto buildFormeDto(String id, String name) {
        FormeDto dto = new FormeDto();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }

}
