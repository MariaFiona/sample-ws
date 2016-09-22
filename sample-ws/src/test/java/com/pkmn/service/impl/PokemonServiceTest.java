package com.pkmn.service.impl;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pkmn.db.EntityManagerDelegate;
import com.pkmn.db.EntityManagerStore;
import com.pkmn.db.TransactionInterceptor;
import com.pkmn.db.entity.Pokemon;
import com.pkmn.db.entity.definitions.Nature;
import com.pkmn.dto.PokemonDto;
import com.pkmn.exception.NotFoundException;
import com.pkmn.exception.ValidationException;
import com.pkmn.testutils.AbstractServiceTest;
import com.pkmn.testutils.TestObjectBuilder;

@AdditionalClasses({ TestObjectBuilder.class, TransactionInterceptor.class, EntityManagerStore.class,
                EntityManagerDelegate.class, FormeServiceImpl.class })
public class PokemonServiceTest extends AbstractServiceTest {

    @Inject
    private PokemonServiceImpl pokemonService;

    @Inject
    private TestObjectBuilder builder;

    /**
     * Tests creating a pokemon with a given nickname, level, and species.
     * 
     * @throws ValidationException
     */
    @Test
    public void testCreatePokemon() throws ValidationException {

        PokemonDto dto = pokemonService.createPokemon("Sparky", 50, 135L);

        Assert.assertEquals(dto.getNickname(), "Sparky");
        Assert.assertEquals(dto.getLevel(), Integer.valueOf(50));
        Assert.assertEquals(dto.getSpecies().getName(), "Jolteon");

    }

    /**
     * Tests creating a pokemon with no nickname, but level and species. Nickname should be set to species name.
     * 
     * @throws ValidationException
     */
    @Test
    public void testCreatePokemonNoNickname() throws ValidationException {
        PokemonDto dto = pokemonService.createPokemon(null, 50, 135L);
        Assert.assertEquals(dto.getNickname(), "Jolteon");
        Assert.assertEquals(dto.getLevel(), Integer.valueOf(50));
        Assert.assertEquals(dto.getSpecies().getName(), "Jolteon");

    }

    /**
     * Tests creating a pokemon random. Species is random, level is 5, Nickname is species name.
     * 
     * @throws ValidationException
     */
    @Test
    public void testCreatePokemonTotallyRandom() throws ValidationException {
        PokemonDto dto = pokemonService.createPokemon(null, null, null);

        Assert.assertNotNull(dto.getSpecies());
        Assert.assertEquals(dto.getLevel(), Integer.valueOf(5));
        Assert.assertEquals(dto.getNickname(), dto.getSpecies().getName());

    }

    /**
     * Tests creating a pokemon with a species id that does not correspond to an existing species. Should throw
     * ValidationException
     * 
     * @throws ValidationException
     */
    @Test(expectedExceptions = { ValidationException.class })
    public void testCreatePokemonInvalidSpeciesId() throws ValidationException {
        pokemonService.createPokemon(null, 5, -1L);

    }

    /**
     * Tests creating a pokemon with an invalid level. Should throw ValidationException.
     * 
     * @throws ValidationException
     */
    @Test(expectedExceptions = { ValidationException.class })
    public void testCreatePokemonInvalidLevel() throws ValidationException {
        pokemonService.createPokemon(null, 101, null);

    }

    /**
     * Tests getting a pokemon by id.
     * 
     * @throws ValidationException
     * @throws NotFoundException
     */
    @Test
    public void testGetPokemon() throws ValidationException, NotFoundException {

        Pokemon poke = builder.newPokemon(builder.getSpecies(1L), "Leafy", 5, Nature.TIMID, new int[] { 31, 20, 20, 31, 20, 31 },
                        new int[] { 0, 0, 0, 0, 0, 0 });
        PokemonDto dto = pokemonService.getPokemonDto(poke.getId());

        Assert.assertEquals(dto.getNickname(), "Leafy");
        Assert.assertEquals(dto.getSpecies().getName(), "Bulbasaur");
        Assert.assertEquals(dto.getPrimaryType(), "GRASS");
        Assert.assertEquals(dto.getSecondaryType(), "POISON");
        Assert.assertEquals(dto.getNature(), "TIMID");

        Assert.assertEquals(dto.getHp(), (Integer) 21, 1);
        Assert.assertEquals(dto.getAtk(), (Integer) 9, 1);
        Assert.assertEquals(dto.getDef(), (Integer) 10, 1);
        Assert.assertEquals(dto.getSpAtk(), (Integer) 13, 1);
        Assert.assertEquals(dto.getSpDef(), (Integer) 12, 1);
        Assert.assertEquals(dto.getSpd(), (Integer) 12, 1);

    }

    /**
     * Tests getting a pokemon that doesn't exist. Should throw NotFoundException.
     * 
     * @throws ValidationException
     * @throws NotFoundException
     */
    @Test(expectedExceptions = { NotFoundException.class })
    public void testGetPokemonDoesntExist() throws ValidationException, NotFoundException {
        pokemonService.getPokemonDto("notapokeid");

    }
}
