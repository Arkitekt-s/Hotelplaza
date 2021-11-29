package com.soheil;

import Room.Room;
import Staff.Staff;


import java.awt.*;
import java.io.Serializable;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.text.ParseException;
import java.util.List;

public class UserInterface implements Serializable {
    // To avoid error when deserializing after changes made in class that implements Serializable interface
    static final long serialVersionUID = 44L;
    public void displayLogInMenu() {
        System.out.println("=============== The Plaza Hotel - Login MENU ================");
        System.out.println("* PRESS 1: LOG IN");
        System.out.println("* PRESS 2: SHUT DOWN");
        System.out.println("=============================================================");
        System.out.print("ENTER HERE: ");
    }
    public void displayAdminMenu() {
        System.out.println("=============== The Plaza Hotel - Main MENU =================");
        System.out.println("* PRESS 1: MAKE A BOOKING");
        System.out.println("* PRESS 2: UPDATE A BOOKING");
        System.out.println("* PRESS 3: CHANGE GUEST INFO");
        System.out.println("* PRESS 4: CHANGE ROOM PRICE");
        System.out.println("* PRESS 5: CHANGE STAFF INFO");
        System.out.println("* PRESS 6: REGISTER NEW STAFF MEMBER");
        System.out.println("* PRESS 7: LOG OUT");
        System.out.println("=============================================================");
        System.out.print("ENTER HERE: ");
    }
    public void displayUserMenu() {
        System.out.println("=============== The Plaza Hotel - Main MENU =================");
        System.out.println("* PRESS 1: MAKE A BOOKING");
        System.out.println("* PRESS 2: UPDATE A BOOKING");
        System.out.println("* PRESS 3: CHANGE GUEST INFO");
        System.out.println("* PRESS 4: LOG OUT");
        System.out.println("=============================================================");
        System.out.print("ENTER HERE: ");
    }

    public void userInterface(Hotel hotelplaza, Staff loggedInEmployee) {


        boolean quit = false;
        boolean choose1 = true;
        while(choose1) {
            displayLogInMenu();

            // Use try & catch here if user gives a non-int input
            try {

                switch (scan()) {
                    case "1" -> {
                        System.out.print("ENTER USERNAME: ");
                        String userName = scan();
                        System.out.print("ENTER PASSWORD: ");
                        String password = scan();

                        loggedInEmployee = Main.employeeExists(hotelplaza.getListOfStaff(), userName, password);
                        if (loggedInEmployee == null) {
                            System.out.println("\n******** INCORRECT USERNAME OR PASSWORD. TRY AGAIN! *********\n");
                        } else {
                            System.out.println("\n---------------- YOU LOGGED IN SUCCESSFULLY -----------------\n");
                            choose1 = false;
                        }
                    }
                    case "2" -> {
                        quit = true;
                        choose1 = false;
                        System.out.println("\n------------ THE PROGRAM SHUT DOWN SUCCESSFULLY -------------");
                    }
                    default -> System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                }
            } catch (InputMismatchException mismatchException) {
                System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
            }

            ///*
            // Menu 2 only runs if quit = false and choose1 = true
            if (quit == false && choose1 == false) {
                boolean choose2 = true;
                while(choose2) {
                    if(loggedInEmployee.getTitle().equalsIgnoreCase("director")) { // This menu for the director
                        displayAdminMenu();

                        try {

                            switch (scan()) {
                                case "1":
                                    // make a booking

                                    System.out.println("\n----------------- MAKE A BOOKING -----------------\n");



                                    //cheack if the room is available
                                    System.out.print("SHOW ROOMS TYPE: "+"\n");
                                    //show listofrooms isOccupied = false
                                    hotelplaza.showListOfRooms();
                                    System.out.print("DO YOU LIKE TO SEE ALL DETAILS OF THE AVAILABLE ROOMS? (Y/N): ");
                                    String answer = scan();
                                    if(answer.equalsIgnoreCase("Y")){
                                        hotelplaza.getListOfRooms().forEach(System.out::println);
                                    }

                                    System.out.print("ENTER ROOM TYPE NAME: ");
                                    String roomType = scan();
                                    hotelplaza.checkRoomType(roomType);


                                    System.out.print("ENTER ROOM NUMBER: ");
                                    int numberOfRooms = Integer.parseInt(scan());
                                    //cheack if the room is available for the number of person
                                    hotelplaza.checkRoomsBed(numberOfRooms);


                                    //should mach with the room type
                                    System.out.print("ENTER NUMBER OF ADULTS IN ROOM: ");
                                    int numberOfAdults = Integer.parseInt(scan());

                                    if (numberOfAdults != 1 && numberOfAdults != 2 && numberOfAdults != 0) {
                                        System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                                        break;
                                    }





                                    System.out.print("DO YOU NEED INTERNET? (Y/N): ");
                                    String internet = scan();
                                    //invalid input
                                    if(!internet.equalsIgnoreCase("Y") && !internet.equalsIgnoreCase("N")){
                                        System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                                        break;
                                    }


                                    System.out.print("ENTER CHECK-IN DATE (dd/MM/yyyy): ");


                                    //apply the courent date format

                                    TimeZone zone = TimeZone.getTimeZone("UTC");
                                    Calendar cal =  Calendar.getInstance(zone);
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
                                    System.out.println("current date: " + dateFormat.format(cal.getTime()));

                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    LocalDate checkIn = LocalDate.parse(scan(), formatter);

                                    //does not allow the user to make a booking before the current date
                                    if (checkIn.isBefore(LocalDate.now())) {
                                        System.out.println("\n********* INVALID DATE. TRY AGAIN! *********\n");
                                        break;
                                    }


                                    System.out.print("ENTER CHECK-OUT DATE (dd/MM/yyyy): ");
                                    //apply the courent date format
                                    cal.clear(Calendar.YEAR);

                                    LocalDate checkOut = LocalDate.parse(scan(), formatter);
                                    //does not allow the user to make a booking before the check in date

                                    if (checkOut.isBefore(checkIn)) {
                                        System.out.println("\n********* INVALID DATE. TRY AGAIN! *********\n");
                                        break;

                                    }

                                    //calculate the number of nights in check-in and check-out
                                    int numberOfNights = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
                                    System.out.println("NUMBER OF NIGHTS: " + numberOfNights);


                                    System.out.println("\n----------------- REGISTER A NEW GUEST -----------------");
                                    System.out.print("ENTER GUEST'S FULL NAME  ");
                                    String guestFullname = scan();
                                    System.out.print("ENTER GUEST'S ADDRESS: ");
                                    String guestAddress = scan();
                                    System.out.println("ENTER GUEST'S PHONENUMBER: ");
                                    int guestPhonenumber =Integer.parseInt(scan());
                                    hotelplaza.registerGuest(guestFullname, guestAddress, guestPhonenumber);
                                    System.out.println("\n----------------- GUEST REGISTERED SUCCESSFULLY -----------------");
                                    System.out.println("\n----------------- LIST OF GUEST -----------------");
                                    //hotelplaza.getListOfRegisteredGuests().forEach(System.out::println);
                                    hotelplaza.makeBooking( roomType, numberOfRooms, numberOfNights,checkIn,checkOut, hotelplaza.registerGuest(guestFullname, guestAddress, guestPhonenumber));
                                    List<Booking> bookings = hotelplaza.getListOfBookings();

                                    for(int i = 0; i < bookings.size(); i++) {
                                        System.out.println(bookings.get(i));
                                    }
                                    System.out.println("\n----------------- BOOKING MADE SUCCESSFULLY -----------------");

                                    System.out.println("\n----------------- PRINTING BILL -----------------");
                                    double price = hotelplaza.totalPriceBooking(numberOfNights,numberOfRooms,internet);
                                    System.out.println("\n----------------- TOTAL PRICE: "+price+"DKK -----------------");
                                    System.out.println("\n----------------- BILL PRINTED SUCCESSFULLY -----------------");









                                    //available rooms in the hotel
                                    System.out.println("\n----------------- AVAILABLE ROOMS -----------------");
                                    //update list of rooms
                                    hotelplaza.updateListOfRooms(numberOfRooms);

                                    //show only isocupide  false rooms
                                    //print only list of rooms name and number
                                    hotelplaza.showListOfRooms();
                                    System.out.println("\n----------------- ROOMS UPDATED SUCCESSFULLY -----------------");

//avoid double booking in the same room
                                    System.out.println("\n----------------- CHECKING IF ROOM IS BOOKED -----------------");

                                    //if booking made successfully, the user is asked to make another booking
                                    System.out.println("\nDO YOU WANT TO MAKE ANOTHER BOOKING? (Y/N)");
                                    String answer1 = scan();

                                    if (answer1.equalsIgnoreCase("y")) {
                                        break;
                                    }
                                    else if (answer.equalsIgnoreCase("n")) {
                                        System.out.println("\n----------------- THANK YOU FOR YOUR BOOKING -----------------");
                                        System.out.println("\n----------------- GOODBYE -----------------");

                                    }

                                    break;

                                case "2":  // UPDATE A BOOKING
                                    System.out.println("\n----------------- UPDATE A BOOKING -----------------");
                                    System.out.println("\n----------------- ENTER ROOM NUMBER-----------------");
                                    //type room number
                                    int roomNumber = Integer.parseInt(scan());

                                    //CHENGE _BOOKING:
                                    System.out.println("\n----------------- CHANGE BOOKING -----------------");
                                    //PRINT INFORMATION ABOUT BOOKING FOUND PERSON
                                    //change booking list and extend booking checkout date
                                    //check in is same as before information about guest

                                    System.out.println("\n----------------- ENTER NEW CHECK-OUT DATE (dd/MM/yyyy): -----------------");
                                    TimeZone zoneUpdate = TimeZone.getTimeZone("UTC");
                                    Calendar calUpdate =  Calendar.getInstance(zoneUpdate);
                                    LocalDate newCheckoutDate = LocalDate.parse(scan(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    calUpdate.clear(Calendar.YEAR);

                                    //does not allow the user to make a booking before the check in date

                                    if (newCheckoutDate.isBefore(LocalDate.now())) {
                                        System.out.println("\n********* INVALID DATE. TRY AGAIN! *********\n");
                                        break;

                                    }

                                    //calculate the number of nights in check-in and check-out
                                    int numberOfNights1 = (int) ChronoUnit.DAYS.between(LocalDate.now(), newCheckoutDate);
                                    System.out.println("NUMBER OF NIGHTS: " + numberOfNights1);
                                    System.out.print("DO YOU NEED INTERNET? (Y/N): ");
                                    String internetUpdate = scan();
                                    //invalid input
                                    if(!internetUpdate.equalsIgnoreCase("Y") && !internetUpdate.equalsIgnoreCase("N")){
                                        System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                                        break;
                                    }
                                    hotelplaza.changeBooking(roomNumber, newCheckoutDate);
                                    //print new Bill again
                                    System.out.println("\n----------------- PRINTING BILL -----------------");

                                    double priceUpdate = hotelplaza.totalPriceBooking(numberOfNights1,roomNumber,internetUpdate);

                                    System.out.println("\n----------------- TOTAL PRICE: "+(priceUpdate)+"DKK -----------------");


                                    //price update minus the old price
                                    System.out.println("\n----------------- BILL PRINTED SUCCESSFULLY -----------------");


                                    //print information about booking
                                    System.out.println("\n----------------- BOOKING UPDATED SUCCESSFULLY -----------------");

                                    break;


                                    // code block


                                case "3":   // CHANGE GUEST INFO * working
                                    System.out.println("\n----------------- CHANGE GUEST INFO -----------------");
                                    System.out.println("ENTER GUEST'S PHONENUMBER: ");
                                    int guestPhonenumber2 = Integer.parseInt(scan());
                                    System.out.println("ENTER NEW GUEST'S FULL NAME: ");
                                    String guestFullname2 = scan();
                                    System.out.println("ENTER NEW GUEST'S ADDRESS: ");
                                    String guestAddress2 = scan();
                                    //print updated guest info
                                    hotelplaza.changeGuestInfo(guestPhonenumber2, guestFullname2, guestAddress2);
                                    //print guest info
                                    System.out.println("\n----------------- GUEST INFO UPDATED SUCCESSFULLY -----------------");

                                    // code block
                                    break;
                                case "4":    // change Room price
                                    System.out.println("\n----------------- CHANGE ROOM PRICE -----------------");
                                    System.out.println("ENTER ROOM NUMBER: ");
                                    //this method of parseInt will return int becouse scanner is a bad class.
                                    int roomNumber2 = Integer.parseInt(scan());

                                    System.out.println("ENTER NEW PRICE: ");
                                    //this method of parseDouble will return int becouse scanner is a bad class.
                                    double roomPrice2 =Double.parseDouble(scan());
                                    hotelplaza.changeRoomPrice(roomNumber2, roomPrice2);
                                    System.out.println("\n----------------- ROOM PRICE CHANGED SUCCESSFULLY -----------------");
                                    System.out.println("\n-----------------DETAILS OF ALL ROOMS:-----------------");
                                    hotelplaza.showListOfRooms2();





                                    // code block
                                    break;
                                case "5":    // CHANGE STAFF INFO
                                    System.out.println("\n----------------- CHANGE STAFF INFO -----------------");
                                    System.out.println("ENTER STAFF'S PHONENUMBER: ");
                                    int staffPhonenumber = Integer.parseInt(scan());
                                    System.out.println("ENTER NEW STAFF'S FIRST NAME: ");
                                    String staffFirstname = scan();
                                    System.out.println("ENTER NEW STAFF'S LAST NAME: ");
                                    String staffLastname = scan();
                                    System.out.println("ENTER NEW STAFF'S TITLE: ");
                                    String staffTitle = scan();
                                    System.out.println("ENTER NEW STAFF'S SALARY: ");
                                    Double staffSalary = Double.parseDouble(scan());
                                    hotelplaza.changeStaffInfo(staffPhonenumber, staffFirstname, staffLastname, staffTitle, staffSalary);



                                    // code block
                                    break;
                                case "6":     // REGISTER NEW STAFF MEMBER
                                    System.out.println("\n----------------- REGISTER NEW STAFF MEMBER -----------------");
                                    System.out.println("WHITCH STAFF TYPE DO YOU WANT TO REGISTER? ");
                                    System.out.println("1. ACCOUNTANT" );
                                    System.out.println("2. CLEANINGLADY");
                                    System.out.println("3. DIRECTOR");
                                    System.out.println("4. RECEPTIONIST");
                                    System.out.println("ENTER STAFF'S FULL NAME: ");
                                    String staffFullname = scan();
                                    System.out.println("ENTER STAFF'S PHONENUMBER: ");
                                    int staffPhonenumber2 = Integer.parseInt(scan());
                                    System.out.println("ENTER STAFF'S TITLE: ");
                                    String staffTitle2 = scan();
                                    System.out.println("ENTER STAFF'S SALARY: ");
                                    Double staffSalary2 = Double.parseDouble(scan());




                                    // code block
                                    break;
                                case "7":     // LOG OUT
                                    choose2 = false;
                                    choose1 = true;
                                    System.out.println("\n---------------- YOU LOGGED OUT SUCCESSFULLY ----------------\n");
                                    break;
                                default:
                                    System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                            }
                        } catch (InputMismatchException mismatchException) {
                            System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                        }

                    }
                    else { // This menu for the receptionist
                        displayUserMenu();

                        try {
                            switch (scan()) {
                                case "1":
                                    // MAKE A BOOKING





                                    // code block
                                    break;

                                case "2": // UPDATE A BOOKING
                                    // code block
                                    break;
                                case "3": // CHANGE GUEST INFO * working - Emie
                                    // code block
                                    break;
                                case "4": // LOG OUT
                                    choose2 = false;
                                    choose1 = true;
                                    System.out.println("\n---------------- YOU LOGGED OUT SUCCESSFULLY ----------------\n");
                                    break;
                                default:
                                    System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                            }
                        } catch (InputMismatchException mismatchException) {
                            System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                        }
                    }

                }
            }
        }
    }
    //creat method for Scanner for out work becouse scanner is not working well.
    public static String scan(){
        Scanner scan= new Scanner(System.in);
        String input=scan.nextLine();
        return input;
    }

}


