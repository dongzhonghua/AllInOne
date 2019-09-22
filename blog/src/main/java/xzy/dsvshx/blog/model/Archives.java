package xzy.dsvshx.blog.model;

public class Archives {
    private Integer id;

    private String archivename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchivename() {
        return archivename;
    }

    public void setArchivename(String archivename) {
        this.archivename = archivename == null ? null : archivename.trim();
    }
}