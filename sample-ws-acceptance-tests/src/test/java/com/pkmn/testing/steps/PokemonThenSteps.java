package com.pkmn.testing.steps;

import org.jbehave.core.annotations.Then;
import org.testng.Assert;

import com.pkmn.testing.runner.PokemonStoryData;

public class PokemonThenSteps extends BaseSteps {

    public PokemonThenSteps(PokemonStoryData data) {
        super(data);
    }

    @Then("The status code of the response is $statusCode")
    public void thenResponseStatus(int statusCode) {
        Assert.assertEquals(data.getResponse().getStatusCode(), statusCode);
    }

    @Then("The response has property '$key' set to '$value'")
    public void thenResponseStatus(String key, String value) {
        Assert.assertEquals(data.getResponse().jsonPath().getString(key), value);
    }

    @Then("The Pokemon has a '$property' of '$value'")
    public void thenResponseProperty(String property, String value) {
        Assert.assertEquals(data.getResponse().jsonPath().getString(property), value);
    }

}
