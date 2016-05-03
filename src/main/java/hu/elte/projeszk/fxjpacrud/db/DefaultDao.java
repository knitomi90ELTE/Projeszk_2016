package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.PersistentEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Gergő
 * @param <T>
 */
public class DefaultDao<T extends PersistentEntity> implements GenericDao<T> {

    private final boolean isRemote = true;

    /**
     *
     */
    protected Class<T> CLASS;

    /**
     *
     */
    protected EntityManagerFactory EMF;

    /**
     *
     * @param CLASS
     */
    public DefaultDao(Class<T> CLASS) {
        this.CLASS = CLASS;
        this.EMF = isRemote ? Persistence.createEntityManagerFactory("LoginFXPU2") : Persistence.createEntityManagerFactory("LoginFXPU");
    }

    /**
     *
     * @param entity
     */
    @Override
    public void create(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    /**
     *
     * @param entity
     */
    @Override
    public void delete(T entity) {
        EntityManager entityManager = getEntityManager();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    /**
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        return findEntities(true, -1, -1);
    }

    /**
     *
     * @param id 
     * @return
     */
    @Override
    public T findById(Integer id) {
        return getEntityManager().find(CLASS, id);
    }

    /**
     *
     * @return
     */
    protected EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    /**
     *
     * @param all
     * @param firstResult
     * @param maxResult
     * @return
     */
    protected List<T> findEntities(boolean all, int firstResult, int maxResult) {
        EntityManager entityManager = getEntityManager();
        CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(CLASS));
        Query query = entityManager.createQuery(criteriaQuery);
        if (!all) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

}
