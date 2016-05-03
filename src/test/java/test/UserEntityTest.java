package test;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class UserEntityTest {
    
    @Test
    public void TestUserEntity(){
        UserEntity userEntity = new UserEntity();
        assertNotNull(userEntity);
        userEntity.setName("Arnold");
        assertEquals("Arnold", userEntity.getName());
        userEntity.setEmail("arnold@elte.hu");
        assertEquals("arnold@elte.hu", userEntity.getEmail());
        userEntity.setPassword("password");
        assertEquals("password", userEntity.getPassword());
    }
    
}
