package com.pkmn.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.pkmn.db.entity.Forme;
import com.pkmn.db.entity.Pokemon;
import com.pkmn.db.entity.Species;
import com.pkmn.db.entity.definitions.Nature;
import com.pkmn.db.entity.definitions.Stat;
import com.pkmn.dto.FormeDto;
import com.pkmn.dto.PokemonDto;
import com.pkmn.dto.SpeciesDto;
import com.pkmn.resource.common.ExpandedProperties;
import com.pkmn.service.util.StatCalculator;

@ApplicationScoped
public class DtoBuilder {

    @Inject
    private CdiWorker cdiWorker;

    @Inject
    private EntityManager em;

    public SpeciesDto buildSpecies(Species species) {

        SpeciesDto dto = new SpeciesDto();

        dto.setId(String.valueOf(species.getId()));
        dto.setName(species.getName());

        List<FormeDto> defaultForme = cdiWorker.doReturningWork(FormeServiceImpl.class, (service) -> {
            return service.getDefaultFormeDtos(species.getId(), ExpandedProperties.none());
        });

        dto.setDefaultForme(defaultForme);

        List<FormeDto> formes = cdiWorker.doReturningWork(FormeServiceImpl.class, (service) -> {
            return service.getFormeDtos(species.getId(), ExpandedProperties.none());
        });
        dto.setFormes(formes);

        return dto;
    }

    public FormeDto buildForme(Forme forme) {
        FormeDto dto = new FormeDto();

        dto.setId(forme.getId());
        dto.setName(forme.getName());
        dto.setBaseHp(forme.getBaseHp());
        dto.setBaseAtk(forme.getBaseAtk());
        dto.setBaseDef(forme.getBaseDef());
        dto.setBaseSpAtk(forme.getBaseSpAtk());
        dto.setBaseSpDef(forme.getBaseSpDef());
        dto.setBaseSpd(forme.getBaseSpd());

        if (forme.getPrimaryType() != null) {
            dto.setPrimaryType(forme.getPrimaryType().toString());
        }
        if (forme.getSecondaryType() != null) {
            dto.setSecondaryType(forme.getSecondaryType().toString());
        }
        return dto;

    }

    public PokemonDto buildPokemon(Pokemon pokemon) {
        PokemonDto dto = new PokemonDto();
        dto.setId(pokemon.getId());
        dto.setNickname(pokemon.getNickname());

        Integer level = pokemon.getLevel();
        dto.setLevel(level);

        Forme forme = em.find(Forme.class, pokemon.getForme().getId());
        Species species = em.find(Species.class, forme.getSpecies().getId());

        Nature nature = pokemon.getNature();

        dto.setHp(StatCalculator.calculateHP(forme.getBaseHp(), pokemon.getHpIv(), pokemon.getHpEv(), level));

        dto.setAtk(StatCalculator.calculateStat(Stat.ATTACK, nature, forme.getBaseAtk(), pokemon.getAtkIv(), pokemon.getAtkEv(),
                        level));
        dto.setDef(StatCalculator.calculateStat(Stat.DEFENSE, nature, forme.getBaseDef(), pokemon.getDefIv(), pokemon.getDefEv(),
                        level));
        dto.setSpAtk(StatCalculator.calculateStat(Stat.SPECIAL_ATTACK, nature, forme.getBaseSpAtk(), pokemon.getSpAtkIv(),
                        pokemon.getSpAtkEv(), level));
        dto.setSpDef(StatCalculator.calculateStat(Stat.SPECIAL_DEFENSE, nature, forme.getBaseSpDef(), pokemon.getSpDefIv(),
                        pokemon.getSpDefEv(), level));
        dto.setSpd(StatCalculator.calculateStat(Stat.SPEED, nature, forme.getBaseSpd(), pokemon.getSpdIv(), pokemon.getSpdEv(),
                        level));

        dto.setNature(nature.toString());

        dto.setSpecies(SpeciesDto.buildSpeciesDto(String.valueOf(species.getId()), species.getName()));

        dto.setCurrentForme(forme.getName());

        if (forme.getPrimaryType() != null) {
            dto.setPrimaryType(forme.getPrimaryType().toString());
        }
        if (forme.getSecondaryType() != null) {
            dto.setSecondaryType(forme.getSecondaryType().toString());
        }

        return dto;
    }
}
