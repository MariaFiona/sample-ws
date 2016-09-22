package com.pkmn.db;

import java.util.Stack;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EntityManagerStore {

    final Logger logger = LoggerFactory.getLogger(EntityManagerStore.class);

    private EntityManagerFactory emf;

    private ThreadLocal<Stack<EntityManager>> emStackThreadLocal = new ThreadLocal<Stack<EntityManager>>();

    public void init(@Observes ContainerInitialized containerInitialized) {
        PersistenceProvider prov = new HibernatePersistenceProvider();
        emf = prov.createEntityManagerFactory("manager", null);
    }

    public EntityManager get() {
        logger.debug("Getting the current entity manager");
        final Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
        if (entityManagerStack == null || entityManagerStack.isEmpty()) {
            /*
             * if nothing is found, we return null to cause a NullPointer exception in the business code. This leeds to a nicer
             * stack trace starting with client code.
             */

            logger.warn("No entity manager was found. Did you forget to mark your method as transactional?");

            return null;
        } else
            return entityManagerStack.peek();
    }

    /**
     * Creates an entity manager and stores it in a stack. The use of a stack allows to implement transaction with a 'requires
     * new' behaviour.
     *
     * @return the created entity manager
     */
    public EntityManager createAndRegister() {
        logger.debug("Creating and registering an entity manager");
        Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
        if (entityManagerStack == null) {
            entityManagerStack = new Stack<EntityManager>();
            emStackThreadLocal.set(entityManagerStack);
        }

        final EntityManager entityManager = emf.createEntityManager();
        entityManagerStack.push(entityManager);
        return entityManager;
    }

    /**
     * Removes an entity manager from the thread local stack. It needs to be created using the {@link #createAndRegister()}
     * method.
     *
     * @param entityManager
     *            - the entity manager to remove
     * @throws IllegalStateException
     *             in case the entity manager was not found on the stack
     */
    public void unregister(EntityManager entityManager) {
        logger.debug("Unregistering an entity manager");
        final Stack<EntityManager> entityManagerStack = emStackThreadLocal.get();
        if (entityManagerStack == null || entityManagerStack.isEmpty())
            throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");

        if (entityManagerStack.peek() != entityManager)
            throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");
        entityManagerStack.pop();
    }
}
