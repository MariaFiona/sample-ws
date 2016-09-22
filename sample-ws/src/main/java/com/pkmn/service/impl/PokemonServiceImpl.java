package com.pkmn.service.impl;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;

import com.pkmn.db.Transactional;
import com.pkmn.db.entity.Forme;
import com.pkmn.db.entity.Pokemon;
import com.pkmn.db.entity.Species;
import com.pkmn.db.entity.definitions.Nature;
import com.pkmn.dto.FormeDto;
import com.pkmn.dto.PokemonDto;
import com.pkmn.exception.NotFoundException;
import com.pkmn.exception.ValidationException;
import com.pkmn.resource.common.ExpandedProperties;
import com.pkmn.service.PokemonService;

@ApplicationScoped
public class PokemonServiceImpl implements PokemonService {
    @Inject
    private EntityManager em;
    @Inject
    private DtoBuilder dtoBuilder;

    @Inject
    private FormeServiceImpl formeService;

    @Transactional
    @Override
    public PokemonDto createPokemon(String nickname, Integer level, Long speciesId) throws ValidationException {

        Long validSpeciesId = getValidatedSpeciesId(speciesId);

        Species species = em.find(Species.class, validSpeciesId);

        if (StringUtils.isEmpty(nickname)) {
            nickname = species.getName();
        }

        return dtoBuilder.buildPokemon(buildNewPokemon(nickname, getValidatedLevel(level), species));
    }

    private Long getValidatedSpeciesId(Long speciesId) throws ValidationException {
        Long validatedId = null;

        if (speciesId == null) {
            validatedId = (long) ThreadLocalRandom.current().nextInt(1, 722);
        } else if (speciesId < 1 || speciesId > 721) {
            throw new ValidationException("Invalid species id");
        } else {
            validatedId = speciesId;
        }

        return validatedId;

    }

    private Integer getValidatedLevel(Integer level) throws ValidationException {
        Integer validatedLevel = null;

        if (level == null) {
            validatedLevel = 5;
        } else if (level > 100 || level < 1) {
            throw new ValidationException("Invalid level");
        } else {
            validatedLevel = level;
        }

        return validatedLevel;
    }

    private Pokemon buildNewPokemon(String nickname, Integer level, Species species) {
        Pokemon pokemon = new Pokemon();
        pokemon.setLevel(level);
        pokemon.setNickname(nickname);

        pokemon.setHpIv(ThreadLocalRandom.current().nextInt(0, 32));
        pokemon.setAtkIv(ThreadLocalRandom.current().nextInt(0, 32));
        pokemon.setDefIv(ThreadLocalRandom.current().nextInt(0, 32));
        pokemon.setSpAtkIv(ThreadLocalRandom.current().nextInt(0, 32));
        pokemon.setSpDefIv(ThreadLocalRandom.current().nextInt(0, 32));
        pokemon.setSpdIv(ThreadLocalRandom.current().nextInt(0, 32));

        pokemon.setHpEv(0);
        pokemon.setAtkEv(0);
        pokemon.setDefEv(0);
        pokemon.setSpAtkEv(0);
        pokemon.setSpDefEv(0);
        pokemon.setSpdEv(0);

        List<FormeDto> forme = formeService.getDefaultFormeDtos(species.getId(), ExpandedProperties.none());
        FormeDto dto = forme.get(new Random().nextInt(forme.size()));
        Forme formeEntity = em.find(Forme.class, dto.getId());

        pokemon.setForme(formeEntity);

        pokemon.setNature(Nature.values()[new Random().nextInt(Nature.values().length)]);

        pokemon.setId(UUID.randomUUID().toString());

        em.persist(pokemon);
        return pokemon;
    }

    @Override
    @Transactional
    public PokemonDto getPokemonDto(String pokemonId) throws NotFoundException, ValidationException {
        if (StringUtils.isBlank(pokemonId)) {
            throw new ValidationException("Pokemon id is required.");
        }

        Pokemon pokemon = em.find(Pokemon.class, pokemonId);

        if (pokemon == null) {
            throw new NotFoundException("Pokemon does not exist.");
        }

        return dtoBuilder.buildPokemon(pokemon);
    }

    @Override
    @Transactional
    public void nicknamePokemon(String pokemonId, String newNickname) throws ValidationException, NotFoundException {

        if (StringUtils.isBlank(newNickname)) {
            throw new ValidationException("Nickname is invalid!");
        }

        Pokemon pokemon = em.find(Pokemon.class, pokemonId);

        if (pokemon == null) {
            throw new NotFoundException("Pokemon does not exist.");
        }

        pokemon.setNickname(newNickname);
        em.merge(pokemon);

    }

}
