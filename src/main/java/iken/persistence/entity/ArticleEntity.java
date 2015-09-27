package iken.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ken on 15/9/1.
 */
@Entity
@Table(name = "article", schema = "", catalog = "mydb")
public class ArticleEntity {
    private int id;
    private int siteid;
    private String link;
    private String title;
    private String article;
    private Timestamp createtime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "siteid")
    public int getSiteid() {
        return siteid;
    }

    public void setSiteid(int siteid) {
        this.siteid = siteid;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "article")
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleEntity that = (ArticleEntity) o;

        if (id != that.id) return false;
        if (siteid != that.siteid) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (article != null ? !article.equals(that.article) : that.article != null) return false;
        return !(createtime != null ? !createtime.equals(that.createtime) : that.createtime != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + siteid;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id=" + id +
                ", siteid=" + siteid +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", article='" + article.substring(0,20) + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
