package xyz.dsvshx.blog.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentLikesRecord {
    private Integer id;

    private Long articleid;

    private Integer pid;

    private Integer likerid;

    private String likedate;

    public CommentLikesRecord(Long articleid, Integer pid, Integer likerid, String likedate) {
        this.articleid = articleid;
        this.pid = pid;
        this.likerid = likerid;
        this.likedate = likedate;
    }


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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
}