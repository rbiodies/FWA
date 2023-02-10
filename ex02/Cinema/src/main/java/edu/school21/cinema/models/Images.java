package edu.school21.cinema.models;

public class Images {
    private Long id;
    private User user;
    private String fileName;
    private String size;
    private String mime;
    private String uniqueName;

    public Images(Long id, User user, String fileName, String size, String mime, String uniqueName) {
        this.id = id;
        this.user = user;
        this.fileName = fileName;
        this.size = size;
        this.mime = mime;
        this.uniqueName = uniqueName;
    }

    public Images() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", user=" + user +
                ", fileName='" + fileName + '\'' +
                ", size='" + size + '\'' +
                ", mime='" + mime + '\'' +
                ", uniqueName='" + uniqueName + '\'' +
                '}';
    }
}
