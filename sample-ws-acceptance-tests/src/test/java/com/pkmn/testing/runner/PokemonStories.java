package com.pkmn.testing.runner;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import com.pkmn.testing.steps.PokemonGivenSteps;
import com.pkmn.testing.steps.PokemonThenSteps;
import com.pkmn.testing.steps.PokemonWhenSteps;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import static org.jbehave.core.reporters.Format.*;

@RunWith(JUnitReportingRunner.class)
public class PokemonStories extends JUnitStories {

    protected EntityManager em;
    private PokemonStoryData data;

    public PokemonStories() {
        PersistenceProvider provider = new HibernatePersistenceProvider();
        EntityManagerFactory factory = provider.createEntityManagerFactory("manager", null);
        this.em = factory.createEntityManager();
        this.data = new PokemonStoryData(em);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration().useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(
                        CONSOLE, TXT, HTML, XML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new PokemonGivenSteps(data), new PokemonWhenSteps(data),
                        new PokemonThenSteps(data));
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("Pokemon.story");
    }

}
