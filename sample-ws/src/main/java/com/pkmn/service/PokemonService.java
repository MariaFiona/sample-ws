package com.pkmn.service;

import com.pkmn.dto.PokemonDto;
import com.pkmn.exception.NotFoundException;
import com.pkmn.exception.ValidationException;

public interface PokemonService {

    public PokemonDto getPokemonDto(String pokemonId) throws NotFoundException, ValidationException;

    public PokemonDto createPokemon(String nickname, Integer level, Long speciesId) throws ValidationException;

    public void nicknamePokemon(String pokemonId, String newNickname) throws ValidationException, NotFoundException;
}
