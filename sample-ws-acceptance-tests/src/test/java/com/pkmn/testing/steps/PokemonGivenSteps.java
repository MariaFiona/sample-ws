package com.pkmn.testing.steps;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;

import org.jbehave.core.annotations.Given;

import com.pkmn.db.entity.Pokemon;
import com.pkmn.db.entity.Species;
import com.pkmn.db.entity.definitions.Nature;
import com.pkmn.testing.runner.PokemonStoryData;

public class PokemonGivenSteps extends BaseSteps {

    public PokemonGivenSteps(PokemonStoryData data) {
        super(data);
    }

    @Given("A Pokemon of species '$speciesName' and level $level")
    public void setPokemon(String speciesName, int level) {
        em.getTransaction().begin();
        buildNewPokemon(em, speciesName, data.getId(), level);
        em.getTransaction().commit();
    }

    private Pokemon buildNewPokemon(EntityManager em, String speciesName, String id, int level) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(id);

        Species species = getSpecies(em, speciesName);

        pokemon.setNickname(species.getName());
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

        pokemon.setForme(getDefaultFormes(em, species.getId()).get(0));
        em.persist(pokemon);
        return pokemon;
    }

}
