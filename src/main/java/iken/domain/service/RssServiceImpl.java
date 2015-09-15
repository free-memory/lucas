package iken.domain.service;

import iken.domain.object.Article;
import iken.domain.object.Site;
import iken.domain.util.XMLRSSCather;
import iken.persistence.DBConnectionUtil;
import iken.persistence.entity.ArticleEntity;
import iken.persistence.entity.SiteEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 15/8/27.
 */
public class RssServiceImpl implements RssService {
    public static final String ENTITY_SITE_ENTITY = "iken.persistence.entity.SiteEntity";
    private String articleEntityName = "iken.persistence.entity.ArticleEntity";

    /**
     * Get all articles
     *
     * @return
     */
    @Override
    public List<Article> getArticles(String siteId) {
        ArrayList<Article> artList = new ArrayList<>();
        String sql = "from " + articleEntityName + " as article where article.siteid = " + siteId;
        List<ArticleEntity> entityList = (List<ArticleEntity>) DBConnectionUtil.getQuery(sql);
        for (ArticleEntity entity : entityList) {
            Article article = new Article();
            BeanUtils.copyProperties(entity, article);
            artList.add(article);
        }

        System.out.println("Entity List =" + artList);
        return artList;
    }

    /**
     * Fetch new articles by url
     *
     * @param siteID
     * @return
     */
    @Override
    public int catchArticles(String siteID) {
        SiteEntity siteEntity = (SiteEntity) DBConnectionUtil.getAObject(ENTITY_SITE_ENTITY, Integer.valueOf(siteID));
        System.out.println("SiteEntity =" + siteEntity);
        List<Article> articleList = new ArrayList<>();

        if (siteEntity != null) {
            articleList = XMLRSSCather.catchArticleFromXML(siteEntity.getSiteurl(), Integer.valueOf(siteID));

            for (Article article : articleList) {
                System.out.println("Artilce = " + article);
                addArticle(article);
            }
        }

        return articleList.size();
    }

    /**
     * Create a new article
     *
     * @param article
     * @return
     */
    @Override
    public int addArticle(Article article) {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(article, articleEntity);

        DBConnectionUtil.addData(articleEntity);
        return articleEntity.getId();
    }

    /**
     * Get all sites
     *
     * @return
     */
    @Override
    public List<Site> getSites() {
        ArrayList<Site> siteList = new ArrayList<>();
        List<SiteEntity> entityList = (List<SiteEntity>) DBConnectionUtil.getQuery("from " + ENTITY_SITE_ENTITY);
        for (SiteEntity siteEntity : entityList) {
            Site site = new Site();
            BeanUtils.copyProperties(siteEntity, site);
            siteList.add(site);
        }

        System.out.println("Entity List =" + siteList);
        return siteList;
    }


}
