package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;

/**
 *
 * @author Gergő
 */
public class DaoManager {

    private GenericDao<UserEntity> userDao;

    /**
     *
     * @return userDao 
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
     *
     * @return
     */
    public static DaoManager getInstance() {
        return DaoManagerHolder.INSTANCE;
    }

    private static class DaoManagerHolder {

        private static final DaoManager INSTANCE = new DaoManager();
    }
}
