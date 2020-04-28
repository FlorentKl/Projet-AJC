package formationJpa.context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Context {
    private static EntityManagerFactory singleton = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (singleton == null) {
            singleton = Persistence.createEntityManagerFactory("jacques");
        }

        return singleton;
    }

    public static void destroy() {
        if (singleton != null) {
            singleton.close();
            singleton = null;
        }
    }
}
