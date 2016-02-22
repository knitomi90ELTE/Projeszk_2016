package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;

public class DaoManager {

    private GenericDao<UserEntity> userDao;

    public GenericDao<UserEntity> getUserDao() {
        if (userDao == null) {
            userDao = new DefaultDao<>(UserEntity.class);
        }
        return userDao;
    }

    private DaoManager() {
    }

    public static DaoManager getInstance() {
        return DaoManagerHolder.INSTANCE;
    }

    private static class DaoManagerHolder {

        private static final DaoManager INSTANCE = new DaoManager();
    }
}
