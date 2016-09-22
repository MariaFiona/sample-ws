package com.pkmn.resource.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.pkmn.dto.FormeDto;
import com.pkmn.resource.common.ResourceEndpoints;
import com.pkmn.resource.utils.SafeURI;

@XmlRootElement(name = "forme")
public class ResponseForme extends ResponseObject {
    private final String type = "forme";

    private String primaryType;
    private String secondaryType;

    private Integer baseHitPoints;
    private Integer baseAttack;
    private Integer baseDefense;
    private Integer baseSpecialAttack;
    private Integer baseSpecialDefense;
    private Integer baseSpeed;

    public ResponseForme() {

    }

    public ResponseForme(FormeDto dto, String baseUri) {
        super(SafeURI.buildURI(baseUri, ResourceEndpoints.FORME_ENDPOINT, dto.getId()), dto.getName(), dto.getId());

        this.primaryType = dto.getPrimaryType();
        this.secondaryType = dto.getSecondaryType();
        this.baseHitPoints = dto.getBaseHp();
        this.baseAttack = dto.getBaseAtk();
        this.baseDefense = dto.getBaseDef();
        this.baseSpecialAttack = dto.getBaseSpAtk();
        this.baseSpecialDefense = dto.getBaseSpDef();
        this.baseSpeed = dto.getBaseSpd();

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

    public Integer getBaseHitPoints() {
        return baseHitPoints;
    }

    public void setBaseHitPoints(Integer baseHitPoints) {
        this.baseHitPoints = baseHitPoints;
    }

    public Integer getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(Integer baseAttack) {
        this.baseAttack = baseAttack;
    }

    public Integer getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(Integer baseDefense) {
        this.baseDefense = baseDefense;
    }

    public Integer getBaseSpecialAttack() {
        return baseSpecialAttack;
    }

    public void setBaseSpecialAttack(Integer baseSpecialAttack) {
        this.baseSpecialAttack = baseSpecialAttack;
    }

    public Integer getBaseSpecialDefense() {
        return baseSpecialDefense;
    }

    public void setBaseSpecialDefense(Integer baseSpecialDefense) {
        this.baseSpecialDefense = baseSpecialDefense;
    }

    public Integer getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(Integer baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public String getType() {
        return type;
    }

}
