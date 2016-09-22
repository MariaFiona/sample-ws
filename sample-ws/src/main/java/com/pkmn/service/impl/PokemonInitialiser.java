package com.pkmn.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pkmn.db.Transactional;
import com.pkmn.db.entity.Effectiveness;
import com.pkmn.db.entity.Forme;
import com.pkmn.db.entity.Species;
import com.pkmn.db.entity.definitions.Multiplier;
import com.pkmn.db.entity.definitions.PokemonType;

@ApplicationScoped
public class PokemonInitialiser {

    private Logger logger = LoggerFactory.getLogger(PokemonInitialiser.class);

    @Inject
    private EntityManager em;

    private ClassLoader loader;

    public PokemonInitialiser() {

    }

    @Transactional
    public void init() {

        try {
            loader = Thread.currentThread().getContextClassLoader();
            createTypeChart();
            createSpecies();
            createFormes();
        } catch (Exception e) {
            logger.error("Pokemon initialiser failed." + e);
            throw new ExceptionInInitializerError(e);
        }

    }

    private void createSpecies() throws IOException {
        logger.info("Loading species from species.csv");
        InputStream is = loader.getResourceAsStream("species.csv");
        List<String> species = IOUtils.readLines(is, StandardCharsets.UTF_8);
        for (String spString : species) {
            String[] tok = StringUtils.split(spString, ',');
            Species currentSpecies = new Species();
            currentSpecies.setId(Long.parseLong(tok[0]));
            currentSpecies.setName(tok[1]);
            em.persist(currentSpecies);

        }
    }

    private void createFormes() throws IOException {
        logger.info("Loading formes from combinedformes.csv");
        InputStream is = loader.getResourceAsStream("combinedformes.csv");
        List<String> formes = IOUtils.readLines(is, StandardCharsets.UTF_8);

        for (String spString : formes) {
            String[] tok = StringUtils.split(spString, ',');

            Forme forme = new Forme();
            forme.setId(UUID.randomUUID().toString());
            forme.setName(tok[1]);
            forme.setPrimaryType(PokemonType.valueOf(tok[9].toUpperCase()));
            if (tok.length > 10) {
                forme.setSecondaryType(PokemonType.valueOf(tok[10].toUpperCase()));
            }

            forme.setIsDefaultForme(Boolean.parseBoolean(tok[2]));
            forme.setBaseHp(Integer.parseInt(tok[3]));
            forme.setBaseAtk(Integer.parseInt(tok[4]));
            forme.setBaseDef(Integer.parseInt(tok[5]));
            forme.setBaseSpAtk(Integer.parseInt(tok[6]));
            forme.setBaseSpDef(Integer.parseInt(tok[7]));
            forme.setBaseSpd(Integer.parseInt(tok[8]));
            forme.setSpecies(em.find(Species.class, Long.parseLong(tok[0])));

            em.persist(forme);
        }

    }

    private void createTypeChart() throws IOException {
        logger.debug("Loading type effectivess chart from type_chart.csv");
        InputStream is = loader.getResourceAsStream("type_chart.csv");
        List<String> chart = IOUtils.readLines(is, StandardCharsets.UTF_8);

        for (int i = 0; i < chart.size(); i++) {
            String[] eff = StringUtils.split(chart.get(i), ',');
            for (int j = 0; j < eff.length; j++) {
                Effectiveness e = new Effectiveness();
                e.setAttackingType(PokemonType.values()[i]);
                e.setDefendingType(PokemonType.values()[j]);
                e.setMultiplier(Multiplier.valueOf(eff[j]));
                em.persist(e);
            }
        }

    }

}
