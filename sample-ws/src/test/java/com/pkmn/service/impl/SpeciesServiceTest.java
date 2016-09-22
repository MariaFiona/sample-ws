package com.pkmn.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.util.List;

import javax.inject.Inject;

import org.hamcrest.Matcher;
import org.jglue.cdiunit.AdditionalClasses;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pkmn.db.EntityManagerDelegate;
import com.pkmn.db.EntityManagerStore;
import com.pkmn.db.TransactionInterceptor;
import com.pkmn.db.entity.definitions.PokemonType;
import com.pkmn.dto.SpeciesDto;
import com.pkmn.testutils.AbstractServiceTest;

@AdditionalClasses({ TransactionInterceptor.class, EntityManagerStore.class, EntityManagerDelegate.class, FormeServiceImpl.class })
public class SpeciesServiceTest extends AbstractServiceTest {

    @Inject
    private SpeciesServiceImpl speciesService;

    @Test
    public void testGetSpeciesByName() {

        List<SpeciesDto> list = speciesService.getSpeciesDtos("Bulbasaur", null);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getName(), "Bulbasaur");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetSpeciesByNameSearch() {

        List<SpeciesDto> list = speciesService.getSpeciesDtos("clef", null);
        assertThat(list, containsInAnyOrder(isNamed("Clefairy"), isNamed("Clefable"), isNamed("Cleffa")));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetSpeciesByNameAndType() {

        List<SpeciesDto> list = speciesService.getSpeciesDtos("Br", PokemonType.PSYCHIC);

        list.forEach(x -> System.out.println(x.getName()));

        assertThat(list,
                        containsInAnyOrder(isNamed("Kadabra"), isNamed("Bronzong"), isNamed("Slowbro"), isNamed("Bronzor"),
                                        isNamed("Abra")));
    }

    /**
     * Tests getting a list of species by type.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetSpeciesByType() {

        List<SpeciesDto> list = speciesService.getSpeciesDtos(null, PokemonType.WATER);
        assertThat(list,
                        hasItems(isNamed("Squirtle"), isNamed("Blastoise"), isNamed("Marill"), isNamed("Relicanth"),
                                        isNamed("Crawdaunt"), isNamed("Greninja")));
    }

    private static Matcher<SpeciesDto> isNamed(String name) {
        return hasProperty("name", equalTo(name));
    }
}
