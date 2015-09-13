package iken.domain;

import java.util.List;

/**
 * Created by ken on 15/8/27.
 */
public interface RssService {
    /**
     * Get all articles
     *
     * @return
     */
    List<Article> getArticles();


    /**
     * Fetch new articles by url
     *
     * @param siteID
     * @return
     */
    int catchArticles(String siteID);


    /**
     * Create a new article
     *
     * @return
     */
    int addArticle(Article article);
}
