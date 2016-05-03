package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * A bejelentkezést megvalósító osztály.
 *
 * @author Gergő
 */
public class LoginQuery extends DefaultDao<UserEntity> {

    /**
     * DAO létrehozása, az ősosztály konstruktorával
     */
    public LoginQuery() {
        super(UserEntity.class);
    }

    /**
     * Visszatér a felhasználók listájával.
     *
     * @return a felhasználók listájával
     */
    public List<UserEntity> listLogin() {
        EntityManager entityManager = getEntityManager();
        return entityManager.createNamedQuery("UserEntity.findAll", UserEntity.class).getResultList();
    }

    /**
     * Felhasználó megkeresése email cím alapján.
     *
     * @param email kertesett email
     * @return a felhasználó entitással
     */
    public UserEntity findUser(String email) {
        try {
            EntityManager entityManager = getEntityManager();
            return entityManager.createNamedQuery("UserEntity.findByEmail", UserEntity.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

}
