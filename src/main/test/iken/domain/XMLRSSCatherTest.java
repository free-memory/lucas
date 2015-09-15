package iken.domain;

import iken.domain.util.XMLRSSCather;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ken on 15/9/6.
 */
public class XMLRSSCatherTest extends TestCase {

    @Test
    public void testCatchArticleFromXML() throws Exception {
        assertNotNull(XMLRSSCather.catchArticleFromXML("http://blog.sina.com.cn/rss/1323880257.xml", 1001));
    }

    @Test
    public void testGetDocumentFromSite() throws Exception {

    }
}