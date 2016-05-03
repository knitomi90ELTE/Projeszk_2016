/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import hu.elte.projeszk.fxjpacrud.db.DaoManager;
import hu.elte.projeszk.fxjpacrud.db.DefaultDao;
import hu.elte.projeszk.fxjpacrud.db.GenericDao;
import hu.elte.projeszk.fxjpacrud.db.LoginQuery;
import hu.elte.projeszk.fxjpacrud.db.RegQuery;
import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import hu.elte.projeszk.fxjpacrud.security.PasswordHash;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Knizner Tamás
 */
public class UserDaoTest {
    
    private UserEntity TesztElek;
    private LoginQuery loginQuery;
    private RegQuery regQuery;
    private final GenericDao<UserEntity> userDao = DaoManager.getInstance().getUserDao();
    
    @Before
    public void setup() {
        System.out.println("Setup testcase in UserDaoTest");
        TesztElek = new UserEntity(null, "Teszt Elek", "teszt@elek.hu", "tesztelek");
        regQuery = new RegQuery();
        loginQuery = new LoginQuery();
    }

    @Test
    public void TestUserDao() throws NoSuchAlgorithmException, InvalidKeySpecException {
        
        Assert.assertNotNull(TesztElek);
        Assert.assertNotNull(regQuery);
        Assert.assertNotNull(loginQuery);
        TesztElek.setPassword(PasswordHash.createHash(TesztElek.getPassword()));
        regQuery.addUser(TesztElek);
        Assert.assertEquals(TesztElek, loginQuery.findUser(TesztElek.getEmail()));
        
        TesztElek = loginQuery.findUser(TesztElek.getEmail());
        TesztElek.setEmail("teszt@elek2.hu");
        userDao.update(TesztElek);
        Assert.assertEquals("teszt@elek2.hu", loginQuery.findUser(TesztElek.getEmail()).getEmail());
        
        userDao.delete(TesztElek);
        Assert.assertNull(loginQuery.findUser(TesztElek.getEmail()));
    }

}
