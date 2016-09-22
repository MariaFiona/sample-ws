package com.pkmn.db.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pkmn.db.entity.definitions.Nature;

/**
 * Entity representing a single pokemon.
 *
 */
@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "level")
    private Integer level;

    @Column(name = "hp_iv")
    private Integer hpIv;

    @Column(name = "atk_iv")
    private Integer atkIv;

    @Column(name = "def_iv")
    private Integer defIv;

    @Column(name = "sp_atk_iv")
    private Integer spAtkIv;

    @Column(name = "sp_def_iv")
    private Integer spDefIv;

    @Column(name = "spd_iv")
    private Integer spdIv;

    @Column(name = "hp_ev")
    private Integer hpEv;

    @Column(name = "atk_ev")
    private Integer atkEv;

    @Column(name = "def_ev")
    private Integer defEv;

    @Column(name = "sp_atk_ev")
    private Integer spAtkEv;

    @Column(name = "sp_def_ev")
    private Integer spDefEv;

    @Column(name = "spd_ev")
    private Integer spdEv;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Forme forme;

    @Column(name = "nature")
    private Nature nature;

    public Pokemon() {
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

    public Integer getHpIv() {
        return hpIv;
    }

    public void setHpIv(Integer hpIv) {
        this.hpIv = hpIv;
    }

    public Integer getAtkIv() {
        return atkIv;
    }

    public void setAtkIv(Integer atkIv) {
        this.atkIv = atkIv;
    }

    public Integer getDefIv() {
        return defIv;
    }

    public void setDefIv(Integer defIv) {
        this.defIv = defIv;
    }

    public Integer getSpAtkIv() {
        return spAtkIv;
    }

    public void setSpAtkIv(Integer spAtkIv) {
        this.spAtkIv = spAtkIv;
    }

    public Integer getSpDefIv() {
        return spDefIv;
    }

    public void setSpDefIv(Integer spDefIv) {
        this.spDefIv = spDefIv;
    }

    public Integer getSpdIv() {
        return spdIv;
    }

    public void setSpdIv(Integer spdIv) {
        this.spdIv = spdIv;
    }

    public Integer getHpEv() {
        return hpEv;
    }

    public void setHpEv(Integer hpEv) {
        this.hpEv = hpEv;
    }

    public Integer getAtkEv() {
        return atkEv;
    }

    public void setAtkEv(Integer atkEv) {
        this.atkEv = atkEv;
    }

    public Integer getDefEv() {
        return defEv;
    }

    public void setDefEv(Integer defEv) {
        this.defEv = defEv;
    }

    public Integer getSpAtkEv() {
        return spAtkEv;
    }

    public void setSpAtkEv(Integer spAtkEv) {
        this.spAtkEv = spAtkEv;
    }

    public Integer getSpDefEv() {
        return spDefEv;
    }

    public void setSpDefEv(Integer spDefEv) {
        this.spDefEv = spDefEv;
    }

    public Integer getSpdEv() {
        return spdEv;
    }

    public void setSpdEv(Integer spdEv) {
        this.spdEv = spdEv;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Forme getForme() {
        return forme;
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

}
