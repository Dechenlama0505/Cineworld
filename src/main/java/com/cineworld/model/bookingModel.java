package com.cineworld.model;

public class bookingModel {

    private int bookingId;
    private String bookingName; 
    private String bookingDate;
    private String seatNumber;
    private String movie;
    private String time;

    public bookingModel() {
    }

    public bookingModel(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public bookingModel(int bookingId, String bookingName, String bookingDate, String seatNumber, String movie, String time) { // Changed to bookingName
        this.bookingId = bookingId;
        this.bookingName = bookingName; // Changed to bookingName
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.movie = movie;
        this.time = time;
    }
    
    public bookingModel( String bookingName, String bookingDate, String seatNumber, String movie, String time) { // Changed to bookingName
        super();
        this.bookingName = bookingName; // Changed to bookingName
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.movie = movie;
        this.time = time;
    }

    // Getters and setters for all fields

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingName() { // Changed to getBookingName
        return bookingName;
    }

    public void setBookingName(String bookingName) { // Changed to setBookingName
        this.bookingName = bookingName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
