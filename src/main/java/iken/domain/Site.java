package iken.domain;

/**
 * Created by ken on 15/9/1.
 */
public class Site {
    private int id;
    private String name;
    private String siteUrl;

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
}
