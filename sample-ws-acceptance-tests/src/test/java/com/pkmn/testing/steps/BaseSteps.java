package com.pkmn.testing.steps;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.specification.RequestSpecification;
import com.pkmn.db.entity.Forme;
import com.pkmn.db.entity.Species;
import com.pkmn.testing.runner.PokemonStoryData;

public class BaseSteps {
    private Logger logger = LoggerFactory.getLogger(BaseSteps.class);
    protected PokemonStoryData data;
    protected EntityManager em;

    public BaseSteps(PokemonStoryData data) {
        this.data = data;
        this.em = data.getEm();
    }

    protected Species getSpecies(EntityManager em, String name) {
        TypedQuery<Species> query = em.createQuery("SELECT s FROM Species s WHERE s.name=:name ", Species.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    protected List<Forme> getDefaultFormes(EntityManager em, Long speciesId) {
        TypedQuery<Forme> query = em.createQuery(
                        "SELECT p from Forme p WHERE p.species.id = :speciesId AND p.isDefaultForme IS TRUE ", Forme.class);
        query.setParameter("speciesId", speciesId);
        List<Forme> formes = query.getResultList();
        return formes;
    }

    protected RequestSpecification getRequest() {
        return RestAssured
                        .given()
                        .config(RestAssuredConfig.config().encoderConfig(
                                        EncoderConfig.encoderConfig().defaultContentCharset(StandardCharsets.UTF_8.name())))
                        .baseUri("http://localhost:8080/pkmn");
    }

    @BeforeStory
    public void beforeStory() {
        data.setRegistry(new HashMap<Object, Object>());

    }

    @BeforeScenario
    public void beforeScenario() {
        data.setId(UUID.randomUUID().toString());

    }

    @AfterStory
    public void afterStory() {
        em.getTransaction().begin();
        for (Entry<Object, Object> object : data.getRegistry().entrySet()) {
            logger.debug("Removing object {}", object.getKey());
            if (em.find(object.getClass(), object.getKey()) != null) {
                em.remove(object.getValue());
            }
        }

        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

}
