package iken.domain;

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
    private String articleEntityName = "iken.persistence.entity.ArticleEntity";

    /**
     * Get all articles
     *
     * @return
     */
    @Override
    public List<Article> getArticles() {
        ArrayList<Article> artList = new ArrayList<>();
        List<ArticleEntity> entityList = (List<ArticleEntity>) DBConnectionUtil.getQuery("from " + articleEntityName);
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
        SiteEntity siteEntity = (SiteEntity) DBConnectionUtil.getAObject("iken.persistence.entity.SiteEntity", Integer.valueOf(siteID));
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


}
