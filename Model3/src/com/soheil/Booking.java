package com.soheil;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Booking implements Serializable {
    // To avoid error when deserializing after changes made in class that implements Serializable interface
    static final long serialVersionUID = 43L;

    // Attributes
    private String roomType;
    private int roomNumber;
    private int numberOfNights;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest registeredGuest;

    // Constructor
    public Booking(String roomType, int roomNumber, int numberOfNights, LocalDate startDate, LocalDate endDate, Guest registeredGuest) {
        setRoomType(roomType);
        setRoomNumber(roomNumber);
        setNumberOfNights(numberOfNights);
        setStartDate(startDate);
        setEndDate(endDate);
        setRegisteredGuest(registeredGuest);
    }

    // getters
    public String getRoomType() {
        return roomType;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public int getNumberOfNights() {
        return numberOfNights;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public Guest getRegisteredGuest() {
        return registeredGuest;
    }
    // setters
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public void setRoomNumber(int recordRoomNumber) {
        this.roomNumber = recordRoomNumber;
    }
    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setRegisteredGuest(Guest registeredGuest) {
        this.registeredGuest = registeredGuest;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "roomType='" + roomType + '\'' +
                ", roomNumber=" + roomNumber +
                ", numberOfNights=" + numberOfNights +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", registeredGuest=" + registeredGuest +
                '}';
    }

    // toString
}

