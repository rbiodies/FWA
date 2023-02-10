package edu.school21.cinema.models;

public class Data {
    private Long id;
    private User user;
    private String date;
    private String time;
    private String ip;

    public Data(Long id, User user, String date, String time, String ip) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.time = time;
        this.ip = ip;
    }

    public Data() {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", time=" + time +
                ", ip='" + ip + '\'' +
                '}';
    }
}
