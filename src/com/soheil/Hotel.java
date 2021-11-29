package com.soheil;

import Room.*;
import Room.RoomDoubleBed;
import Room.RoomSuite;
import Room.RoomOneBed;
import Staff.*;



import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Date;

public class Hotel implements Serializable {
  // To avoid error when deserializing after changes made in class that implements Serializable
  // interface
  static final long serialVersionUID = 45L;

  // Attributes
  private String name;
  private ArrayList<Room> listOfRooms;
  private ArrayList<Staff> listOfStaff;
  private ArrayList<Booking> listOfBookings;
  private ArrayList<Guest> listOfRegisteredGuests;
  private int numberOfGuests;

  // Constructor
  public Hotel(String name) {
    setName(name);
    listOfRooms = new ArrayList<Room>();
    listOfStaff = new ArrayList<Staff>();
    listOfBookings = new ArrayList<Booking>();
    listOfRegisteredGuests = new ArrayList<Guest>();
    numberOfGuests = 0;
  }

  // getters
  public String getName() {
    return name;
  }

  public ArrayList<Room> getListOfRooms() {
    return listOfRooms;
  }

  public ArrayList<Staff> getListOfStaff() {
    return listOfStaff;
  }

  public ArrayList<Booking> getListOfBookings() {
    return listOfBookings;
  }

  public ArrayList<Guest> getListOfRegisteredGuests() {
    return listOfRegisteredGuests;
  }

  public int getNumberOfGuests() {
    return numberOfGuests;
  }
  // setters
  public void setName(String name) {
    this.name = name;
  }

  public void setListOfRooms(ArrayList<Room> listOfRooms) {
    this.listOfRooms = listOfRooms;
  }

  public void setListOfStaff(ArrayList<Staff> listOfStaff) {
    this.listOfStaff = listOfStaff;
  }

  public void setListOfBookings(ArrayList<Booking> listOfBookings) {
    this.listOfBookings = listOfBookings;
  }

  public void setListOfRegisteredGuests(ArrayList<Guest> listOfRegisteredGuest) {
    this.listOfRegisteredGuests = listOfRegisteredGuest;
  }

  public void setNumberOfGuests(int numberOfGuest) {
    this.numberOfGuests = numberOfGuest;
  }

  // toString

  // Create a booking
  public Booking makeBooking(
      String roomType,
      int roomNumber,
      int numberOfNights,
      LocalDate startDate,
      LocalDate endDate,
      Guest guest) {
    Booking newBooking =
        new Booking(roomType, roomNumber, numberOfNights, startDate, endDate, guest);
    listOfBookings.add(newBooking);
    return newBooking;
  }

  // register a guest
  public Guest registerGuest(String fullName, String address, int phoneNumber) {
    Guest registeredGuest = new Guest(fullName, address, phoneNumber);
    // add registeredGuest to list of registered guests
    listOfRegisteredGuests.add(registeredGuest);
    return registeredGuest;
  }

  // price for booking each time
  public double totalPriceBooking(int numberOfNights, int roomNumber, String internetAccess) {
    for (int i = 0; i < listOfRooms.size(); i++) {
      if (listOfRooms.get(i).getRoomNumber() == roomNumber) {
        if (internetAccess.equalsIgnoreCase("y")) {
          return listOfRooms.get(i).getPricePerNight() * numberOfNights + 100;
        }
        return listOfRooms.get(i).getPricePerNight() * numberOfNights;
      }
    }
    return -1;
  }

  // Determine how many guests in the hotel
  public void numberOfGuests() {}

  // the update booking here
  public void updateBooking() {}
  // CHANGE GUEST INFORMATION
  public Hotel changeGuestInfo(int phoneNumber, String fullName, String address) {
    for (int i = 0; i < listOfRegisteredGuests.size(); i++) {
      if (listOfRegisteredGuests.get(i).getPhoneNumber() == phoneNumber) {
        listOfRegisteredGuests.get(i).setFullName(fullName);
        listOfRegisteredGuests.get(i).setAddress(address);
      }
      System.out.println("Guest information has been updated");
      System.out.println(listOfRegisteredGuests.get(i));
    }
    return null;
  }

  // CHANGE STAFF INFO
  public Hotel changeStaffInfo(
      int phoneNumber, String firstName, String LastName, String title, double Salary) {
    for (int i = 0; i < listOfStaff.size(); i++) {
      if (listOfStaff.get(i).getPhoneNumber() == phoneNumber) {
        listOfStaff.get(i).setTitle(title);
        listOfStaff.get(i).setFirstName(firstName);
        listOfStaff.get(i).setLastName(LastName);
        listOfStaff.get(i).setSalary(Salary);
        System.out.println("Staff information has been changed");
        System.out.println(listOfStaff.get(i).toString());
      }
    }
    return null;
  }
  // REGISTER NEW STAFFAccount
  public Staff registerNewAccountant(
      String title, String firstname, String Lastname, int phoneNumnber, double salery) {
    StaffAccountant newStaffA =
        new StaffAccountant(title, firstname, Lastname, phoneNumnber, salery);
    listOfStaff.add(newStaffA);
    return newStaffA;
  }
  // register NEW STAFFAccount
  public Staff registerCleaningLady(
      String title, String firstname, String Lastname, int phoneNumnber, double salery) {
    StaffCleaningLady newStaffC =
        new StaffCleaningLady(title, firstname, Lastname, phoneNumnber, salery);
    listOfStaff.add(newStaffC);
    return newStaffC;
  }
  // register NEW STAFFAccount
  public StaffUser registerNewDirector(
      String title,
      String firstname,
      String Lastname,
      int phoneNumnber,
      double salery,
      String password,
      String username) {
    StaffDirector newStaffM =
        new StaffDirector(title, firstname, Lastname, phoneNumnber, salery, password, username);
    listOfStaff.add(newStaffM);
    return newStaffM;
  }
  // register NEW STAFFAccount
  public StaffUser registerNewReception(
      String title,
      String firstname,
      String Lastname,
      int phoneNumnber,
      double salery,
      String password,
      String username) {
    StaffReceptionist newStaffD =
        new StaffReceptionist(title, firstname, Lastname, phoneNumnber, salery, password, username);
    listOfStaff.add(newStaffD);
    return newStaffD;
  }

  // cheack available doublebeds
  public String checkRoomType(String roomtype) {
    for (int i = 0; i < listOfRooms.size(); i++) {
      if (listOfRooms.get(i).getRoomType() == roomtype) {
        return listOfRooms.get(i).getRoomType();
      }
    }
    return null;
  }


  // room isoccupied false depend to bookings check in and check out




  public Hotel updateListOfRooms(int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
    for (int i = 0; i < listOfRooms.size(); i++) {
      //setisOccupide to false after check out

      if (listOfRooms.get(i).getRoomNumber() == roomNumber && listOfRooms.get(i).getIsOccupied() == false) {
        listOfRooms.get(i).setIsOccupied(true);
        System.out.println("R00M NUMBER" + roomNumber + " HAS BEEN BOOKED"+"\n"+"CHECK IN DATE: "+checkInDate+"\n"+"CHECK OUT DATE: "+checkOutDate);
      }
    }
    return null;
  }
  //show calender of booking








  // show list of room getisOccupied false

  public void showListOfRooms() {
    for (int i = 0; i < listOfRooms.size(); i++) {
      if (listOfRooms.get(i).getIsOccupied() == false) {
        // false means the room is available
        System.out.println(
            listOfRooms.get(i).getRoomType()
                + " |ROOM NUMBER "
                + listOfRooms.get(i).getRoomNumber()
                + " |IS OCCUPIED "
                + translate2(listOfRooms.get(i).getIsOccupied()));
      }
    }
  }

  public void showListOfRooms2() {
    for (int i = 0; i < listOfRooms.size(); i++) {
      if (listOfRooms.get(i).getIsOccupied() == false) {
        // false means the room is available
        System.out.println(listOfRooms.get(i).toString());
      }
    }
  }
  // translate false/true to yes or no
  public String translate2(boolean trueOrFalse) {
    return trueOrFalse ? "Yes" : "No";
  }
  // change room Price
  public void changeRoomPrice(int roomNumber, double price) {
    for (int i = 0; i < listOfRooms.size(); i++) {
      if (listOfRooms.get(i).getRoomNumber() == roomNumber) {
        listOfRooms.get(i).setPricePerNight(price);
        System.out.println("Room price has been changed");
      }
    }
  }
  // check each room not accept more than two person or one person
  public String checkRoomsBed(int numberOfRooms) {
    if (numberOfRooms == 13 || numberOfRooms == 23 || numberOfRooms == 12 || numberOfRooms == 22) {
      System.out.println("You can check in only two person");
    } else if (numberOfRooms == 11 || numberOfRooms == 21) {
      System.out.println("You can check in only one person");
    } else {
      System.out.println("Your input is not valid");
    }
    return null;
  }

  // find Guest by phone number
  public Guest findGuestByPhoneNumber(int guestPhoneNumber) {
    for (int i = 0; i < listOfRegisteredGuests.size(); i++) {
      if (listOfRegisteredGuests.get(i).getPhoneNumber() == guestPhoneNumber) {
        return listOfRegisteredGuests.get(i);
      }
    }
    return null;
  }

  public Hotel changeBooking(int roomNumber, LocalDate newCheckoutDate) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRoomNumber() == roomNumber) {
        listOfBookings.get(i).setEndDate(newCheckoutDate);
        System.out.println("Booking has been changed");
        System.out.println("Booking has been changed");
      }
    }
    return null;
  }

  public void printBill(
      double price,
      int numberOfNights,
      int numberOfRooms,
      String internet,
      LocalDate checkIn,
      LocalDate checkOut,
      String guestFullname,
      String guestAddress,
      int guestPhonenumber) {
    // print bill in pdf format
    // print bill in text format

    System.out.println("Number of nights: " + numberOfNights);
    System.out.println("Number of rooms: " + numberOfRooms);
    System.out.println("Internet: " + internet+"price per night: " + "100,00 DKK");
    System.out.println("Check in: " + checkIn);
    System.out.println("Check out: " + checkOut);
    System.out.println("Guest fullname: " + guestFullname);
    System.out.println("Guest address: " + guestAddress);
    System.out.println("Guest phonenumber: " + guestPhonenumber);
    System.out.println("Total price: " + (price));
  }
  //isocupide according to check in and check out
  public Hotel OccupiedRoom(LocalDate checkIn, LocalDate checkOut) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getStartDate().isAfter(checkIn)
          && listOfBookings.get(i).getEndDate().isBefore(checkOut)) {
        listOfRooms.get(i).setIsOccupied(true);

      }
    }
    return null;
  }




  // room can book in available days FOR EACH ROOM



  public Hotel checkAvailableDays(LocalDate checkIn, LocalDate checkOut) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (OccupiedRoom(checkIn, checkOut)== null) {
        System.out.println(
            "Room number "
                + listOfBookings.get(i).getRoomNumber()
                                 + " is available for the period ");
      }
    }
    return null;
  }




}