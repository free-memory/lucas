package iken.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ken on 15/8/27.
 */
public class RssServiceImplTest extends TestCase {
    private RssServiceImpl serviceImpl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        serviceImpl = new RssServiceImpl();
    }

    @Test
    public void testGetArticles() throws Exception {
        assertTrue(serviceImpl.getArticles().size() == 2);

    }

    public void testGetArticles1() throws Exception {

    }

    @Test
    public void testCatchArticles() throws Exception {
        assertTrue(serviceImpl.catchArticles("1001") > -1);
    }

    public void testAddArticle() throws Exception {

    }
}