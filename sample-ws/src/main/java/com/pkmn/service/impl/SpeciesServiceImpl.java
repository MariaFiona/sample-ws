package com.pkmn.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import com.pkmn.db.Transactional;
import com.pkmn.db.entity.Forme;
import com.pkmn.db.entity.Species;
import com.pkmn.db.entity.definitions.PokemonType;
import com.pkmn.dto.SpeciesDto;
import com.pkmn.service.SpeciesService;

@ApplicationScoped
public class SpeciesServiceImpl implements SpeciesService {

    @Inject
    private EntityManager em;

    @Inject
    private DtoBuilder builder;

    @Override
    @Transactional
    public SpeciesDto getSpeciesDto(Long id) {
        Species species = em.find(Species.class, id);
        return builder.buildSpecies(species);
    }

    @Override
    @Transactional
    public List<SpeciesDto> getSpeciesDtos(String name, PokemonType type) {
        List<Species> species = getSpeciesList(name, type);
        List<SpeciesDto> dtos = species.stream().map(spec -> builder.buildSpecies(spec)).collect(Collectors.toList());
        return dtos;
    }

    private List<Species> getSpeciesList(String name, PokemonType type) {
        StringBuffer sql = new StringBuffer("SELECT p from Forme p WHERE p.isDefaultForme IS TRUE ");

        if (StringUtils.isNotBlank(name)) {
            sql.append("AND UPPER(p.species.name) LIKE UPPER(:name) ");
        }

        if (type != null) {

            sql.append(" AND (p.primaryType = :type OR p.secondaryType = :type) ");
        }

        TypedQuery<Forme> query = em.createQuery(sql.toString(), Forme.class);
        if (StringUtils.isNotBlank(name)) {
            query.setParameter("name", "%" + name + "%");
        }

        if (type != null) {
            query.setParameter("type", type);
        }

        List<Forme> formes = query.getResultList();

        ArrayList<Species> specs = new ArrayList<Species>(formes.stream()
                        .collect(Collectors.toMap(forme -> forme.getSpecies().getName(), Forme::getSpecies, (s, a) -> s))
                        .values());

        Collections.sort(specs, new Comparator<Species>() {

            @Override
            public int compare(Species o1, Species o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        return specs;
    }

}
