package com.pkmn.testing.steps;

import org.jbehave.core.annotations.When;

import com.pkmn.testing.runner.PokemonStoryData;

public class PokemonWhenSteps extends BaseSteps {

    public PokemonWhenSteps(PokemonStoryData data) {
        super(data);
    }

    @When("The user retrieves the Pokemon")
    public void whenUserRetrievesPokemon() {
        data.setResponse(getRequest().get("/pokemon/" + data.getId()));
    }

    @When("The user retrieves the Pokemon with id '$id'")
    public void whenUserRetrievesPokemon(String id) {
        data.setResponse(getRequest().get("/pokemon/" + id));
    }

}
