package iken.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ken on 15/9/12.
 */
@Entity
@Table(name = "site", schema = "", catalog = "mydb")
public class SiteEntity {
    private int siteid;
    private String siteurl;
    private String name;
    private Timestamp createdtime;

    @Id
    @Column(name = "siteid")
    public int getSiteid() {
        return siteid;
    }

    public void setSiteid(int siteid) {
        this.siteid = siteid;
    }

    @Basic
    @Column(name = "siteurl")
    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "createdtime")
    public Timestamp getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Timestamp createdtime) {
        this.createdtime = createdtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteEntity that = (SiteEntity) o;

        if (siteid != that.siteid) return false;
        if (siteurl != null ? !siteurl.equals(that.siteurl) : that.siteurl != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(createdtime != null ? !createdtime.equals(that.createdtime) : that.createdtime != null);

    }

    @Override
    public int hashCode() {
        int result = siteid;
        result = 31 * result + (siteurl != null ? siteurl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdtime != null ? createdtime.hashCode() : 0);
        return result;
    }
}
