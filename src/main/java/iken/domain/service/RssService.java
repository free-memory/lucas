package iken.domain.service;

import iken.domain.object.Article;
import iken.domain.object.Site;

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
    List<Article> getArticles(String siteId);


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

    /**
     * Get all sites
     *
     * @return
     */
    List<Site> getSites();

}
