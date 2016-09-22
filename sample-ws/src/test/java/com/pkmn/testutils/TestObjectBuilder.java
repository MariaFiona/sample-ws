package com.pkmn.testutils;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.pkmn.db.Transactional;
import com.pkmn.db.entity.Forme;
import com.pkmn.db.entity.Pokemon;
import com.pkmn.db.entity.Species;
import com.pkmn.db.entity.definitions.Nature;

@ApplicationScoped
public class TestObjectBuilder {

    @Inject
    private EntityManager em;

    public TestObjectBuilder() {

    }

    @Transactional
    public Pokemon newPokemon(Species species, String nickname, int level) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(UUID.randomUUID().toString());

        pokemon.setNickname(nickname);
        pokemon.setLevel(level);

        pokemon.setHpIv(ThreadLocalRandom.current().nextInt(1, 32));
        pokemon.setAtkIv(ThreadLocalRandom.current().nextInt(1, 32));
        pokemon.setDefIv(ThreadLocalRandom.current().nextInt(1, 32));
        pokemon.setSpAtkIv(ThreadLocalRandom.current().nextInt(1, 32));
        pokemon.setSpDefIv(ThreadLocalRandom.current().nextInt(1, 32));
        pokemon.setSpdIv(ThreadLocalRandom.current().nextInt(1, 32));

        pokemon.setHpEv(0);
        pokemon.setAtkEv(0);
        pokemon.setDefEv(0);
        pokemon.setSpAtkEv(0);
        pokemon.setSpDefEv(0);
        pokemon.setSpdEv(0);

        pokemon.setNature(Nature.values()[new Random().nextInt(Nature.values().length)]);

        pokemon.setForme(getDefaultFormes(species.getId()).get(0));

        em.persist(pokemon);

        return pokemon;

    }

    @Transactional
    public Pokemon newPokemon(Species species, String nickname, int level, Nature nature, int[] ivs, int[] evs) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(UUID.randomUUID().toString());

        pokemon.setLevel(level);

        pokemon.setNickname(nickname);

        pokemon.setHpIv(ivs[0]);
        pokemon.setAtkIv(ivs[1]);
        pokemon.setDefIv(ivs[2]);
        pokemon.setSpAtkIv(ivs[3]);
        pokemon.setSpDefIv(ivs[4]);
        pokemon.setSpdIv(ivs[5]);

        pokemon.setHpEv(ivs[0]);
        pokemon.setAtkEv(ivs[1]);
        pokemon.setDefEv(ivs[2]);
        pokemon.setSpAtkEv(ivs[3]);
        pokemon.setSpDefEv(ivs[4]);
        pokemon.setSpdEv(ivs[5]);

        pokemon.setNature(nature);

        pokemon.setForme(getDefaultFormes(species.getId()).get(0));

        em.persist(pokemon);

        return pokemon;
    }

    @Transactional
    public Species getSpecies(Long speciesId) {
        return em.find(Species.class, speciesId);
    }

    private List<Forme> getDefaultFormes(Long speciesId) {
        TypedQuery<Forme> query = em.createQuery(
                        "SELECT p from Forme p WHERE p.species.id = :speciesId AND p.isDefaultForme IS TRUE ", Forme.class);
        query.setParameter("speciesId", speciesId);
        List<Forme> formes = query.getResultList();
        return formes;
    }
}
