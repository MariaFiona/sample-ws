package com.pkmn.testing.runner;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.jayway.restassured.response.Response;

public class PokemonStoryData {

    private EntityManager em;
    private Response response;
    private String id;
    private Map<Object, Object> registry = new HashMap<Object, Object>();

    public PokemonStoryData(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Map<Object, Object> getRegistry() {
        return registry;
    }

    public void setRegistry(Map<Object, Object> registry) {
        this.registry = registry;
    }

    public void register(Object key, Object value) {
        registry.put(key, value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
