package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Gergő
 */
public class LoginQuery extends DefaultDao<UserEntity> {

    /**
     *
     */
    public LoginQuery() {
        super(UserEntity.class);
    }

    /**
     *
     * @return
     */
    public List<UserEntity> listLogin() {
        EntityManager entityManager = getEntityManager();
        return entityManager.createNamedQuery("UserEntity.findAll", UserEntity.class).getResultList();
    }

    /**
     *
     * @param email
     * @return
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
