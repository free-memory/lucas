package iken.domain;

import iken.domain.object.User;
import iken.domain.service.UserServiceImpl;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ken on 15/8/26.
 */
public class UserServiceImplTest extends TestCase {

    private UserServiceImpl userServiceImpl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        userServiceImpl = new UserServiceImpl();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testListUser() throws Exception {
        assertTrue(userServiceImpl.listUser().size() == 6);
    }

    @Test
    public void testGetUser() throws Exception {
        assertNotNull(userServiceImpl.getUserByID(101));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        String strRandom = String.valueOf(Math.round(Math.random() * 100));
        user.setId(107);
        user.setName("TestUser" + strRandom);
        user.setAge(Integer.valueOf(strRandom));

        userServiceImpl.updateUser(user);
    }

}