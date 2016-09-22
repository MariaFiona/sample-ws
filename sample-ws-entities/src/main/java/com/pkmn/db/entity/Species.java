package com.pkmn.db.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "species")
public class Species {

    public Species() {
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    private List<Forme> formes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Forme> getFormes() {
        return formes;
    }

    public void setFormes(List<Forme> formes) {
        this.formes = formes;
    }

    // public Set<PokemonForme> getFormes() {
    // return formes;
    // }
    //
    // public void setFormes(Set<PokemonForme> formes) {
    // this.formes = formes;
    // }

}
