package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.PersistentEntity;
import java.util.List;

/**
 * Interface amely az alapvető adatbázis műveletek deklarációi.
 * 
 * @author Gergő
 * @param <T>
 */
public interface GenericDao<T extends PersistentEntity> {

    /**
     * Új entitás felvétele.
     *
     * @param entity a létrehozandó T típusú entitás
     */
    public void create(T entity);

    /**
     *  Entitás frissítése.
     * 
     * @param entity a frissítendő T típusú entitás
     */
    public void update(T entity);

    /**
     *  Entitás törlése.
     * 
     * @param entity a törlendő T típusu entitás
     */
    public void delete(T entity);

    /**
     * Entitások keresése.
     * 
     * @return visszatér az entitások listájával
     */
    public List<T> findAll();

    /**
     * Azonosító alpján keresés.
     * 
     * @param id az entitás egyedi azonosítója
     * @return megfelelő azonosítóval rendelkező entitás
     */
    public T findById(Integer id);

}
