package com.pkmn.testutils;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.NgCdiRunner;
import org.testng.annotations.BeforeMethod;

import com.pkmn.db.EntityManagerDelegate;
import com.pkmn.db.EntityManagerStore;
import com.pkmn.db.TransactionInterceptor;
import com.pkmn.service.impl.PokemonInitialiser;

@AdditionalClasses({ TransactionInterceptor.class, EntityManagerStore.class, EntityManagerDelegate.class })
public abstract class AbstractServiceTest extends NgCdiRunner {
    @Inject
    private PokemonInitialiser initialiser;

    @BeforeMethod
    public void setupInit() {
        initialiser.init();
    }
}
