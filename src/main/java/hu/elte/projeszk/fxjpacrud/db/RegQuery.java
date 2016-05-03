package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;

/**
 *
 * @author Gergő
 */
public class RegQuery extends DefaultDao<UserEntity> {

    /**
     *
     */
    public RegQuery() {
        super(UserEntity.class);
    }

    /**
     *
     * @param user
     */
    public void addUser(UserEntity user) {
        create(user);
    }

}
