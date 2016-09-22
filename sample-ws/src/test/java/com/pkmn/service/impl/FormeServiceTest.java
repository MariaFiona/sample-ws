package com.pkmn.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pkmn.db.EntityManagerDelegate;
import com.pkmn.db.EntityManagerStore;
import com.pkmn.db.TransactionInterceptor;
import com.pkmn.dto.FormeDto;
import com.pkmn.resource.common.ExpandedProperties;
import com.pkmn.testutils.AbstractServiceTest;

@AdditionalClasses({ TransactionInterceptor.class, EntityManagerStore.class, EntityManagerDelegate.class })
public class FormeServiceTest extends AbstractServiceTest {

    @Inject
    private FormeServiceImpl formeService;

    @Test
    public void testGetDefaultForme() {
        List<FormeDto> defaultForme = formeService.getDefaultFormeDtos(1L, ExpandedProperties.none());

        Assert.assertEquals(defaultForme.get(0).getName(), "Bulbasaur");
    }

}
