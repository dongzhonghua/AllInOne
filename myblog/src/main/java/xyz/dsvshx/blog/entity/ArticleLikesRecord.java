package xyz.dsvshx.blog.entity;

public class ArticleLikesRecord {
    private Integer id;

    private Long articleid;

    private Integer likerid;

    private String likedate;

    private Boolean isread;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public Integer getLikerid() {
        return likerid;
    }

    public void setLikerid(Integer likerid) {
        this.likerid = likerid;
    }

    public String getLikedate() {
        return likedate;
    }

    public void setLikedate(String likedate) {
        this.likedate = likedate == null ? null : likedate.trim();
    }

    public Boolean getIsread() {
        return isread;
    }

    public void setIsread(Boolean isread) {
        this.isread = isread;
    }
}