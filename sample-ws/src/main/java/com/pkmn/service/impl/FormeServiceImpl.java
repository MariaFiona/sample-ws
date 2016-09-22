package com.pkmn.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.pkmn.db.Transactional;
import com.pkmn.db.entity.Forme;
import com.pkmn.dto.FormeDto;
import com.pkmn.resource.common.ExpandedProperties;
import com.pkmn.service.FormeService;

@ApplicationScoped
public class FormeServiceImpl implements FormeService {

    @Inject
    private EntityManager em;

    @Inject
    private DtoBuilder builder;

    @Override
    @Transactional
    public List<FormeDto> getFormeDtos(Long speciesId, ExpandedProperties expandedProperties) {
        List<Forme> formes = getFormes(speciesId);

        List<FormeDto> dtos = formes.stream().map(forme -> builder.buildForme(forme))
                        .collect(Collectors.toList());

        return dtos;
    }

    public List<Forme> getFormes(Long speciesId) {
        TypedQuery<Forme> query = em.createQuery("SELECT p FROM Forme p WHERE p.species.id = :speciesId ", Forme.class);
        query.setParameter("speciesId", speciesId);

        List<Forme> formes = query.getResultList();
        return formes;
    }

    @Override
    @Transactional
    public List<FormeDto> getDefaultFormeDtos(Long speciesId, ExpandedProperties expandedProperties) {
        List<Forme> formes = getDefaultFormes(speciesId);

        List<FormeDto> dtos = formes.stream().map(forme -> builder.buildForme(forme))
                        .collect(Collectors.toList());

        return dtos;
    }

    private List<Forme> getDefaultFormes(Long speciesId) {
        TypedQuery<Forme> query = em.createQuery(
                        "SELECT p from Forme p WHERE p.species.id = :speciesId AND p.isDefaultForme IS TRUE ", Forme.class);
        query.setParameter("speciesId", speciesId);
        List<Forme> formes = query.getResultList();
        return formes;
    }

}
