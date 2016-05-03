package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;

/**
 * Singelton osztály Design Pattern-jének megvalósítása.
 * 
 * @author Gergő, Zoltán
 */
public class DaoManager {

    private GenericDao<UserEntity> userDao;

    /**
     * Létrehoz egy DAO objektumot, ha még nem volt létrehozva.
     * 
     * @return userDao a DAO objektum
     */
    public GenericDao<UserEntity> getUserDao() {
        if (userDao == null) {
            userDao = new DefaultDao<>(UserEntity.class);
        }
        return userDao;
    }

    private DaoManager() {
    }

    /**
     * Az egyke példányhoz biztosít hozzáférést.
     * 
     * @return az egyke példányra való referenciájával
     */
    public static DaoManager getInstance() {
        return DaoManagerHolder.INSTANCE;
    }

    private static class DaoManagerHolder {

        private static final DaoManager INSTANCE = new DaoManager();
    }
}
