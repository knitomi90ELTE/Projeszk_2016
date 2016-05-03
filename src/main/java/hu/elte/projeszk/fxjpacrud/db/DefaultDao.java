package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.PersistentEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Megvalósjtja az általános DAO-t (Data Access Object).
 * 
 * @author Gergő, Zoltán
 * @param <T> az oszály generikus paramétere amely a PersistentEntity
 *            osztályból származik.
 */
public class DefaultDao<T extends PersistentEntity> implements GenericDao<T> {

    private final boolean isRemote = true;

    /**
     * Az entitás osztályának tárolása.
     */
    protected Class<T> CLASS;

    /**
     * Az entitásokat létrehozó EntityManagerFactory.
     */
    protected EntityManagerFactory EMF;

    /**
     * Beállítja az entitás osztályát.
     * Attól függően, hogy távoli vagy lokális host-ot használunk, EntityManagerFactory-t is.
     * 
     * @param CLASS entitás osztálya
     */
    public DefaultDao(Class<T> CLASS) {
        this.CLASS = CLASS;
        this.EMF = isRemote ? Persistence.createEntityManagerFactory("LoginFXPU2") : Persistence.createEntityManagerFactory("LoginFXPU");
    }

    /**
     * Új entitiás felvétele az adatbázisba.
     * 
     * @param entity létrehozandó új entitás
     */
    @Override
    public void create(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Entitás frissítése.
     * 
     * @param entity frissítendő entitás
     */
    @Override
    public void update(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Entitás törlése az adatbázisból.
     * 
     * @param entity törlendő entitás
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
     * Megkeresi az adatbázisban szereplő összes entitás.
     * @return összes entitás
     */
    @Override
    public List<T> findAll() {
        return findEntities(true, -1, -1);
    }

    /**
     * Megfelelő azonosítójú elem keresése.
     * 
     * @param id keresett azonosítójú entitás
     * @return keresett elem
     */
    @Override
    public T findById(Integer id) {
        return getEntityManager().find(CLASS, id);
    }

    /**
     * Visszatér a létrehozott entitás kezlővel.
     * 
     * @return az entitás kezelővel
     */
    protected EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    /**
     * Az összes adat lekérdezése az adatbázisból, egy intervallum között.
     * 
     * @param all visszatérés az összes elemmel
     * @param firstResult mettől listázza az eredményt
     * @param maxResult meddig listázza az eredményt
     * @return a keresett listával
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
