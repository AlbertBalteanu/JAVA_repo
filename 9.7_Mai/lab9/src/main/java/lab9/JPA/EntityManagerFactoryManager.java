package lab9.JPA;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityManagerFactoryManager {
    private static final Logger LOGGER = Logger.getLogger(EntityManagerFactoryManager.class.getName());
    private static final String PERSISTENCE_UNIT_NAME = "world_cities_pu";
    private static EntityManagerFactoryManager instance;
    private EntityManagerFactory emf;
    
    private EntityManagerFactoryManager() {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            LOGGER.info("EntityManagerFactory created successfully");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create EntityManagerFactory", e);
        }
    }
    
    public static synchronized EntityManagerFactoryManager getInstance() {
        if (instance == null) {
            instance = new EntityManagerFactoryManager();
        }
        return instance;
    }
    
    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            LOGGER.info("EntityManagerFactory closed");
        }
    }
}
