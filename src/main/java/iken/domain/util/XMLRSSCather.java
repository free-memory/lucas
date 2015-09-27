package iken.domain.util;

import iken.domain.object.Article;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 15/9/1.
 */
public class XMLRSSCather {

    /**
     * Get Article from remote web site
     *
     * @param siteURL
     * @param siteId
     * @return
     */
    public static List<Article> catchArticleFromXML(String siteURL, int siteId) {
        List<Article> articles = new ArrayList<>();

        try {
            Document document = getDocumentFromSite(siteURL);
            Element rootElement = document.getRootElement();
            for (Element itemElement : (List<Element>) rootElement.element("channel").elements("item")) {
                Article article = new Article();
                article.setArticle(itemElement.element("description").getStringValue());
                article.setLink(itemElement.element("link").getStringValue());
                article.setTitle(itemElement.element("title").getStringValue());
                article.setSiteid(siteId);
                article.setCreatetimeByString(itemElement.element("pubDate").getStringValue());

                articles.add(article);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    /**
     * Get XML Document from remote web site
     *
     * @param siteURL
     * @return
     */
    public static Document getDocumentFromSite(String siteURL) throws IOException, DocumentException {
        Document document = null;
        SAXReader reader = new SAXReader();
        URL url = new URL(siteURL);
        HttpURLConnection httpConn = null;

        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        InputStream in = httpConn.getInputStream();
        DataInputStream dis = new DataInputStream(in);

        //String content = dis.readLine();
        //System.out.println("Content = " + content);
        document = reader.read(in);
        System.out.print("XML = " + document.asXML().substring(0,100));
        //document = DocumentHelper.parseText(content);
        return document;
    }

}
