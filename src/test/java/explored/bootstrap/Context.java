package explored.bootstrap;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Context {

    private Context() {
    }

    private final static EntityManager em = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }
}
