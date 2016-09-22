package com.pkmn.resource.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.pkmn.dto.PokemonDto;
import com.pkmn.resource.common.ResourceEndpoints;
import com.pkmn.resource.utils.SafeURI;

@XmlRootElement(name = "pokemon")
public class ResponsePokemon extends ResponseObject {
    private String id;
    private String nickname;
    private Integer level;
    private String nature;

    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;

    private ResponseSpecies species;
    private String currentForme;

    private String primaryType;
    private String secondaryType;

    private final String type = "";

    public ResponsePokemon() {
    }

    public ResponsePokemon(PokemonDto dto, String baseUri) {
        super(SafeURI.buildURI(baseUri, ResourceEndpoints.POKEMON_ENDPOINT, dto.getId()), null, dto.getId());

        this.nickname = dto.getNickname();
        this.level = dto.getLevel();
        this.nature = dto.getNature();
        this.hp = dto.getHp();
        this.attack = dto.getAtk();
        this.defense = dto.getDef();
        this.specialAttack = dto.getSpAtk();
        this.specialDefense = dto.getSpDef();
        this.speed = dto.getSpd();

        if (dto.getSpecies() != null) {
            this.species = new ResponseSpecies(dto.getSpecies(), baseUri);
        }

        this.currentForme = dto.getCurrentForme();

        this.primaryType = dto.getPrimaryType();
        this.secondaryType = dto.getSecondaryType();
    }

    public String getType() {
        return type;
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

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public ResponseSpecies getSpecies() {
        return species;
    }

    public void setSpecies(ResponseSpecies species) {
        this.species = species;
    }

    public String getCurrentForme() {
        return currentForme;
    }

    public void setCurrentForme(String currentForme) {
        this.currentForme = currentForme;
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

}
