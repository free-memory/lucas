package iken.domain;

import iken.domain.service.RssServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        assertTrue(serviceImpl.getArticles("1002", 1, 10).size() > 0);

    }

    @Test
    public void testCatchArticles() throws Exception {
        assertTrue(serviceImpl.catchArticles("1002") > -1);
    }

    public void testAddArticle() throws Exception {

    }

    @Test
    public void testDate() throws Exception {
        String strDate = "Mon, 14 Sep 2015 15:55:24 +0800";
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Timestamp tm = new Timestamp(df.parse(strDate).getTime());
        System.out.println("Date = " + tm);
    }

    @Test
    public void testGetSites() throws Exception {
        assertTrue(serviceImpl.getSites().size() > 0);
    }
}