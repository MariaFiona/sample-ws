package com.pkmn.service;

import java.util.List;

import com.pkmn.db.entity.definitions.PokemonType;
import com.pkmn.dto.SpeciesDto;

public interface SpeciesService {
    public SpeciesDto getSpeciesDto(Long id);

    public List<SpeciesDto> getSpeciesDtos(String name, PokemonType type);

}
