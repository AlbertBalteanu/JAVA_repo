package lab9.JPA.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lab9.JPA.EntityManagerFactoryManager;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractRepository<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractRepository.class.getName());
    protected final EntityManagerFactory emf;
    protected final Class<T> entityClass;
    
    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.emf = EntityManagerFactoryManager.getInstance().getEntityManagerFactory();
    }
    
    public T create(T entity) {
        EntityManager em = emf.createEntityManager();
        
        long startTime = System.currentTimeMillis();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            
            long endTime = System.currentTimeMillis();
            LOGGER.log(Level.INFO, "Created entity {0} in {1}ms", 
                      new Object[]{entityClass.getSimpleName(), (endTime - startTime)});
            
            return entity;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.log(Level.SEVERE, "Error creating entity: " + e.getMessage(), e);
            throw e;
        } finally {
            em.close();
        }
    }
    
    public T findById(int id) {
        EntityManager em = emf.createEntityManager();
        
        long startTime = System.currentTimeMillis();
        try {
            T entity = em.find(entityClass, id);
            
            long endTime = System.currentTimeMillis();
            LOGGER.log(Level.INFO, "Found entity {0} by id in {1}ms", 
                      new Object[]{entityClass.getSimpleName(), (endTime - startTime)});
            
            return entity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding entity by id: " + e.getMessage(), e);
            throw e;
        } finally {
            em.close();
        }
    }

    public List<T> findByName(String name) {
        EntityManager em = emf.createEntityManager();
        
        long startTime = System.currentTimeMillis();
        try {
            String queryName = entityClass.getSimpleName() + ".findByName";
            TypedQuery<T> query = em.createNamedQuery(queryName, entityClass);
            query.setParameter("name", name);
            List<T> result = query.getResultList();
            
            long endTime = System.currentTimeMillis();
            LOGGER.log(Level.INFO, "Found {0} entities of type {1} by name ''{2}'' in {3}ms", 
                      new Object[]{result.size(), entityClass.getSimpleName(), name, (endTime - startTime)});
            
            return result;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding entities by name: " + e.getMessage(), e);
            throw e;
        } finally {
            em.close();
        }
    }
}