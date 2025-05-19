package com.cineworld.model;

public class movieModel {
    private int id;
    private String title;
    private String status;
    private int seatsBooked;
    private int duration;        // previously movieTime
    private String genres;
    private String posterUrl;

    // Constructors
    public movieModel() {
    }

    public movieModel(int id, String title, String status, int seatsBooked, int duration, String genres, String posterUrl) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.seatsBooked = seatsBooked;
        this.duration = duration;
        this.genres = genres;
        this.posterUrl = posterUrl;
    }

    public movieModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public movieModel(int id, String title, String genres, String posterUrl) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.posterUrl = posterUrl;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
