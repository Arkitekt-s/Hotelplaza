package com.soheil;

import Room.*;
import Staff.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.io.Serializable;

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
  public int numberOfGuests() {
    return listOfRegisteredGuests.size();
  }

  // the update booking here
  public void updateBooking() {}

  // CHANGE GUEST INFORMATION and show the updated information
  public void changeGuestInfo(int phoneNumber, String fullName, String address) {
    for (int i = 0; i < listOfRegisteredGuests.size(); i++) {
      if (listOfRegisteredGuests.get(i).getPhoneNumber() == phoneNumber) {
        listOfRegisteredGuests.get(i).setFullName(fullName);
        listOfRegisteredGuests.get(i).setAddress(address);
        System.out.println(listOfRegisteredGuests.get(i).toString());
      }
    }
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

  public Hotel updateListOfRooms(int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
    for (int i = 0; i < listOfRooms.size(); i++) {
      // setisOccupide to false after check out

      if (listOfRooms.get(i).getRoomNumber() == roomNumber
          && listOfRooms.get(i).getIsOccupied() == false) {
        listOfRooms.get(i).setIsOccupied(true);
        System.out.println(
            "R00M NUMBER"
                + roomNumber
                + " HAS BEEN BOOKED"
                + "\n"
                + "CHECK IN DATE: "
                + checkInDate
                + "\n"
                + "CHECK OUT DATE: "
                + checkOutDate);
      }
    }
    return null;
  }
  // show calender of booking

  // show list of room getisOccupied false

  public void showListOfRooms() {
    for (int i = 0; i < listOfRooms.size(); i++) {

      System.out.println(
          listOfRooms.get(i).getRoomType()
              + " |ROOM NUMBER "
              + listOfRooms.get(i).getRoomNumber()
              + " |PRICE "
              + listOfRooms.get(i).getPricePerNight());
    }
  }

  public void showListOfRooms2() {
    for (int i = 0; i < listOfRooms.size(); i++) {

      // false means the room is available
      System.out.println(listOfRooms.get(i).toString());
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

  // find Guest by phone number (case2)
  public Guest findGuestByPhoneNumber(int guestPhoneNumber) {
    for (int i = 0; i < listOfRegisteredGuests.size(); i++) {
      if (listOfRegisteredGuests.get(i).getPhoneNumber() == guestPhoneNumber) {
        return listOfRegisteredGuests.get(i);
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
    System.out.println("Internet: " + internet + " price per night: " + "100,00 DKK");
    System.out.println("Check in: " + checkIn);
    System.out.println("Check out: " + checkOut);
    System.out.println("Guest fullname: " + guestFullname);
    System.out.println("Guest address: " + guestAddress);
    System.out.println("Guest phonenumber: " + guestPhonenumber);
    System.out.println("Total price: " + (price));
  }
  // print bill for update booking
  public void printBillUpdate(
      int numberOfNights1,
      int numberOfRooms1,
      String internet1,
      LocalDate checkOut1,
      int guestPhonenumber1) {

    System.out.println("Number of nights: " + numberOfNights1);
    System.out.println("Number of rooms: " + numberOfRooms1);
    System.out.println("Internet: " + internet1 + " price per night: " + "100,00 DKK");
    System.out.println("Check out: " + checkOut1);
    System.out.println("Guest phonenumber: " + guestPhonenumber1);
  }

  // check if room is available for one room
  public boolean occupiedRoom(LocalDate checkIn, LocalDate checkOut, int roomNumber) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRoomNumber() == roomNumber) {
        if (listOfBookings.get(i).getStartDate().isBefore(checkOut)
            && listOfBookings.get(i).getEndDate().isAfter(checkIn)) {
          System.out.println("Room is occupied between" + checkIn + " and " + checkOut);
          return true;
        }
      }
    }
    return false;
  }
  // update checkout date
  public boolean updatOoccupiedRoom(
      LocalDate checkIn, LocalDate checkOut, int roomNumber, int phonenumber) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRoomNumber() == roomNumber && listOfBookings.get(i).getRegisteredGuest().getPhoneNumber() != phonenumber) {
        if (listOfBookings.get(i).getStartDate().isBefore(checkOut) && listOfBookings.get(i).getEndDate().isAfter(checkIn)) {
          System.out.println("Room is occupied between" + checkIn + " and " + checkOut);
          return true;
        }
      }
    }
    return false;
  }

  // find checkin date

  public LocalDate findCheckIn(int roomNumber, int guestPhonenumber) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRoomNumber() == roomNumber
          && listOfBookings.get(i).getRegisteredGuest().getPhoneNumber() == guestPhonenumber) {

        return listOfBookings.get(i).getStartDate();
      }
    }
    return null;
  }
  // find check out date
  public LocalDate findCheckout(int roomNumber, int guestPhonenumber) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRoomNumber() == roomNumber
          && listOfBookings.get(i).getRegisteredGuest().getPhoneNumber() == guestPhonenumber) {

        return listOfBookings.get(i).getEndDate();
      }
    }
    return null;
  }
  // find old number of nights
  public int findOldNumberOfNights(int roomNumber, int guestPhonenumber) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRoomNumber() == roomNumber
          && listOfBookings.get(i).getRegisteredGuest().getPhoneNumber() == guestPhonenumber) {

        return listOfBookings.get(i).getNumberOfNights();
      }
    }
    return 0;
  }

  // update guest information in booking in case2
  public void updateBooking(Guest phoneGuest, int roomNumber, int numberOfNights) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRegisteredGuest().getPhoneNumber()
          == phoneGuest.getPhoneNumber()) {
        if (listOfBookings.get(i).getRoomNumber() == roomNumber) {
          listOfBookings.get(i).setNumberOfNights(numberOfNights);
        }
      }
    }
  }

  public void updateCheckout(Guest phoneGuest, int roomNumber, LocalDate newCheckoutDate) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRegisteredGuest().getPhoneNumber() == phoneGuest.getPhoneNumber()
          && (listOfBookings.get(i).getRoomNumber() == roomNumber)) {
        listOfBookings.get(i).setEndDate(newCheckoutDate);
      }
    }
  }
  // check if the user is available
  public boolean checkPhoneNumberInList(int guestPhonenumber) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRegisteredGuest().getPhoneNumber() == guestPhonenumber) {
        return true;
      }
    }
    return false;
  }

  public int findRoomNumberByPhoneNumber(int guestPhonenumber2) {
    for (int i = 0; i < listOfBookings.size(); i++) {
      if (listOfBookings.get(i).getRegisteredGuest().getPhoneNumber() == guestPhonenumber2) {
        return listOfBookings.get(i).getRoomNumber();
      }
    }
    return 0;
  }

  public String findStaffByPhoneNumber(int staffPhonenumber) {
    for (int i = 0; i < listOfStaff.size(); i++) {
      if (listOfStaff.get(i).getPhoneNumber() == staffPhonenumber) {

        return listOfStaff.get(i).getTitle();
      }
    }
    return null;
  }
}

