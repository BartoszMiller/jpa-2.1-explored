package explored.bootstrap;

import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

public class BootstrapUsingPersistenceXml {

    private EntityManager em = Context.getEntityManager();

    @Test
    public void initEntityManagerUsingPersistenceClass() {
        assertNotNull(em);
    }
}
