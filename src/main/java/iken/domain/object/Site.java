package iken.domain.object;

/**
 * Created by ken on 15/9/1.
 */
public class Site {
    private int siteId;
    private String name;
    private String siteUrl;

    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", name='" + name + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                '}';
    }

    public int getSiteid() {
        return siteId;
    }

    public void setSiteid(int id) {
        this.siteId = id;
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
