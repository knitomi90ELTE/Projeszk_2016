package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;

/**
 * Regisztráció megvalósítása.
 * 
 * @author Gergő
 */
public class RegQuery extends DefaultDao<UserEntity> {

    /**
     * DAO létrehozása, az ősosztály konstruktorával.
     */
    public RegQuery() {
        super(UserEntity.class);
    }

    /**
     * Felhasználó hozzáadása az adatbázishoz.
     * 
     * @param user új felhasználói entitás
     */
    public void addUser(UserEntity user) {
        create(user);
    }

}
