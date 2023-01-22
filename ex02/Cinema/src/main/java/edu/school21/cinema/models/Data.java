package edu.school21.cinema.models;

public class Data {
    private String date;
    private String time;
    private String ip;

    public Data(String date, String time, String ip) {
        this.date = date;
        this.time = time;
        this.ip = ip;
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
}
