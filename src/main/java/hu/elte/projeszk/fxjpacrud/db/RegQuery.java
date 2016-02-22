package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;

public class RegQuery extends DefaultDao<UserEntity> {

    public RegQuery() {
        super(UserEntity.class);
    }

    public void addUser(UserEntity user) {
        create(user);
    }

}
