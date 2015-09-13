package iken.persistence;

import iken.persistence.entity.UserEntity;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by ken on 15/8/26.
 */
public class DBConnectionUtilTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetQuery() throws Exception {

    }

    @Test
    public void testGetData() throws Exception {

    }

    @Test
    public void testUpdateOrAddData() throws Exception {

    }

    @Test
    public void testGetAUser() throws Exception {
        UserEntity user = (UserEntity) DBConnectionUtil.getAObject("iken.persistence.entity.UserEntity", 107);
        System.out.println(" User's name =" + user.getName());
        assertNotNull(user.getName());
    }

    @Test
    public void testDeleteData() throws Exception {
        assertTrue(DBConnectionUtil.deleteData("iken.persistence.entity.UserEntity", 109));
    }
}