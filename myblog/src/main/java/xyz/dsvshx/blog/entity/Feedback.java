package xyz.dsvshx.blog.entity;

public class Feedback {
    private Integer id;

    private String contactinfo;

    private Integer personid;

    private String feedbackdate;

    private String feedbackcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo == null ? null : contactinfo.trim();
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(String feedbackdate) {
        this.feedbackdate = feedbackdate == null ? null : feedbackdate.trim();
    }

    public String getFeedbackcontent() {
        return feedbackcontent;
    }

    public void setFeedbackcontent(String feedbackcontent) {
        this.feedbackcontent = feedbackcontent == null ? null : feedbackcontent.trim();
    }
}