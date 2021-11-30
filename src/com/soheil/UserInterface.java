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
                                    System.out.print("ENTER CHECK-IN DATE (dd-MM-yyyy): ");

                                    //apply the courent date format in try and catch
                                    try {
                                    TimeZone zone = TimeZone.getTimeZone("UTC");
                                    Calendar cal =  Calendar.getInstance(zone);
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
                                    System.out.println("current date: " + dateFormat.format(cal.getTime()));
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                                    LocalDate checkIn = LocalDate.parse(scan(), formatter);

                                    //does not allow the user to make a booking before the current date
                                    if (checkIn.isBefore(LocalDate.now())) {
                                        System.out.println("\n********* INVALID DATE. TRY AGAIN! *********\n");
                                        break;
                                    }

                                    //checkout date
                                    System.out.print("ENTER CHECK-OUT DATE (dd-MM-yyyy): ");
                                    //apply the courent date format
                                    cal.clear(Calendar.YEAR);

                                    LocalDate checkOut = LocalDate.parse(scan(), formatter);
                                    //does not allow the user to make a booking before the check in date

                                    if (checkOut.isBefore(checkIn)) {
                                        System.out.println("\n********* INVALID DATE. TRY AGAIN! *********\n");
                                        break;

                                    }

                                    //cheack if the room is available for this time period
                                    System.out.println("\n----------------- ROOMS AVAILABLE BETWEEN THIS TIME-----------------\n");
                                    hotelplaza.checkAvailableDays(checkIn, checkOut);
                                    hotelplaza.OccupiedRoom(checkIn, checkOut);


                                    //cheack if the room is available
                                    System.out.print("SHOW ROOMS TYPE: "+"\n");
                                    //show listofrooms isOccupied = false
                                    hotelplaza.showListOfRooms();
                                    System.out.print("DO YOU LIKE TO SEE ALL DETAILS OF All ROOMS? (Y/N): ");
                                    String answer = scan();
                                    if(answer.equalsIgnoreCase("Y")){
                                        hotelplaza.getListOfRooms().forEach(System.out::println);
                                    }

                                    System.out.print("ENTER ROOM TYPE NAME: ");
                                    String roomType = scan();
                                    hotelplaza.checkRoomType(roomType);


                                    System.out.print("ENTER ROOM NUMBER: ");
                                    int numberOfRooms = Integer.parseInt(scan());
                                    //it should be valid integer
                                    if (!(numberOfRooms == 11)&&!(numberOfRooms==12)&&!(numberOfRooms==13)&&!(numberOfRooms==21)&&!(numberOfRooms==22)&&!(numberOfRooms==23)) {
                                        System.out.println("\n********* INVALID ROOM NUMBER. TRY AGAIN! *********\n");
                                        break;
                                    }
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
                                    //it should be a valid integer
                                    if(guestPhonenumber < 0){
                                        System.out.println("\n***************** INVALID INPUT. TRY AGAIN! *****************\n");
                                        break;
                                    }



                                    hotelplaza.registerGuest(guestFullname, guestAddress, guestPhonenumber);
                                    System.out.println("\n----------------- GUEST REGISTERED SUCCESSFULLY -----------------");
                                    System.out.println("\n----------------- LIST OF GUEST -----------------");
                                    //hotelplaza.getListOfRegisteredGuests().forEach(System.out::println);
                                    hotelplaza.makeBooking( roomType, numberOfRooms, numberOfNights,checkIn,checkOut,
                                            hotelplaza.registerGuest(guestFullname, guestAddress, guestPhonenumber));

                                    List<Booking> bookings = hotelplaza.getListOfBookings();

                                    for(int i = 0; i < bookings.size(); i++) {
                                        System.out.println(bookings.get(i));
                                    }

                                    System.out.println("\n----------------- BOOKING MADE SUCCESSFULLY -----------------");

                                    System.out.println("\n----------------- PRINTING BILL -----------------");
                                    double price = hotelplaza.totalPriceBooking(numberOfNights,numberOfRooms,internet);
                                    //print bill in pdf as pdf file
                                    System.out.println("\n----------------- TOTAL PRICE: "+price+"DKK -----------------");
                                    hotelplaza.printBill(price,numberOfNights,numberOfRooms,internet,checkIn,
                                            checkOut,guestFullname,guestAddress,guestPhonenumber);

                                    System.out.println("\n----------------- BILL PRINTED SUCCESSFULLY -----------------");









                                    //available rooms in the hotel
                                    System.out.println("\n----------------- AVAILABLE ROOMS -----------------");
                                    //update list of rooms
                                    hotelplaza.updateListOfRooms(numberOfRooms,checkIn,checkOut);
                                    //print chekavailable days



                                    //show only isocupide  false rooms
                                    //print only list of rooms name and number
                                    hotelplaza.showListOfRooms();
                                    System.out.println("\n----------------- ROOMS UPDATED SUCCESSFULLY -----------------");

//avoid double booking in the same room
                                    System.out.println("\n----------------- CHECKING IF ROOM IS BOOKED -----------------");
                                    System.out.println("\n----------------- NUMBER OF GUESTS-----------------");
                                    //print number of guests

                                    int numberofguest = hotelplaza.numberOfGuests();
                                    System.out.println(numberofguest);
                                    //print number of guests


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
                                    //Main.serialize( hotelplaza,"Database.ser");

                                    break;
                                    }
                                    catch (Exception e) {
                                        System.out.println("\n----------------- ERROR -----------------");
                                        System.out.println("\n----------------- PLEASE TRY AGAIN -----------------");

                                        System.exit(0);

                                    }

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
                                    //Main.serialize( hotelplaza,"Database.ser");

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
                                    //Main.serialize( hotelplaza,"Database.ser");

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
                                    //Main.serialize( hotelplaza,"Database.ser");





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
                                    //Main.serialize( hotelplaza,"Database.ser");



                                    // code block
                                    break;
                                case "6":     // REGISTER NEW STAFF MEMBER
                                    System.out.println("\n----------------- REGISTER NEW STAFF MEMBER -----------------");
                                    System.out.println("WHITCH STAFF TYPE DO YOU WANT TO REGISTER? ");
                                    System.out.println("1. ACCOUNTANT" );
                                    System.out.println("2. CLEANINGLADY");
                                    System.out.println("3. DIRECTOR");
                                    System.out.println("4. RECEPTIONIST");
                                    System.out.println("5. EXIT");
                                    String staffType = scan();
                                    switch (staffType) {
                                        case "1":
                                            System.out.println("ENTER NEW ACCOUNTANT'S TITLE: ");
                                            String staffTitle1 = scan();
                                            System.out.println("ENTER NEW ACCOUNTANT'S FIRST NAME: ");
                                            String staffFirstname1 = scan();
                                            System.out.println("ENTER NEW ACCOUNTANT'S LAST NAME: ");
                                            String staffLastname1 = scan();
                                            System.out.println("ENTER NEW ACCOUNTANT'S PHONENUMBER: ");
                                            int staffPhonenumber1 = Integer.parseInt(scan());
                                            System.out.println("ENTER NEW ACCOUNTANT'S SALARY: ");
                                            Double staffSalary1 = Double.parseDouble(scan());
                                            hotelplaza.registerNewAccountant( staffTitle1, staffFirstname1, staffLastname1, staffPhonenumber1, staffSalary1);
                                            hotelplaza.getListOfStaff().forEach(System.out::println);
                                            //System.out.println("\n----------------- NEW ACCOUNTANT REGISTERED SUCCESSFULLY -----------------");
                                            break;
                                        case "2":
                                            System.out.println("ENTER NEW CLEANINGLADY'S TITLE: ");
                                            String staffTitle2 = scan();
                                            System.out.println("ENTER NEW CLEANINGLADY'S FIRST NAME: ");
                                            String staffFirstname2 = scan();
                                            System.out.println("ENTER NEW CLEANINGLADY'S LAST NAME: ");
                                            String staffLastname2 = scan();
                                            System.out.println("ENTER NEW CLEANINGLADY'S PHONENUMBER: ");
                                            int staffPhonenumber2 = Integer.parseInt(scan());
                                            System.out.println("ENTER NEW CLEANINGLADY'S SALARY: ");
                                            Double staffSalary2 = Double.parseDouble(scan());
                                            hotelplaza.registerCleaningLady( staffTitle2, staffFirstname2, staffLastname2, staffPhonenumber2, staffSalary2);
                                            hotelplaza.getListOfStaff().forEach(System.out::println);
                                            System.out.println("\n----------------- NEW CLEANINGLADY REGISTERED SUCCESSFULLY -----------------");
                                            break;
                                        case "3":
                                            System.out.println("ENTER NEW DIRECTOR'S TITLE: ");
                                            String staffTitle3 = scan();
                                            System.out.println("ENTER NEW DIRECTOR'S FIRST NAME: ");
                                            String staffFirstname3 = scan();
                                            System.out.println("ENTER NEW DIRECTOR'S LAST NAME: ");
                                            String staffLastname3 = scan();
                                            System.out.println("ENTER NEW DIRECTOR'S PHONENUMBER: ");
                                            int staffPhonenumber3 = Integer.parseInt(scan());
                                            System.out.println("ENTER NEW DIRECTOR'S SALARY: ");
                                            Double staffSalary3 = Double.parseDouble(scan());
                                            System.out.println("ENTER USERNAME FOR NEW DIRECTOR: ");
                                            String staffUsername3 = scan();
                                            System.out.println("ENTER PASSWORD FOR NEW DIRECTOR: ");
                                            String staffPassword3 = scan();
                                            hotelplaza.registerNewDirector( staffTitle3, staffFirstname3, staffLastname3, staffPhonenumber3, staffSalary3, staffUsername3, staffPassword3);
                                            hotelplaza.getListOfStaff().forEach(System.out::println);
                                            System.out.println("\n----------------- NEW DIRECTOR REGISTERED SUCCESSFULLY -----------------");
                                            break;
                                        case "4":
                                            System.out.println("ENTER NEW RECEPTIONIST'S TITLE: ");
                                            String staffTitle4 = scan();
                                            System.out.println("ENTER NEW RECEPTIONIST'S FIRST NAME: ");
                                            String staffFirstname4 = scan();
                                            System.out.println("ENTER NEW RECEPTIONIST'S LAST NAME: ");
                                            String staffLastname4 = scan();
                                            System.out.println("ENTER NEW RECEPTIONIST'S PHONENUMBER: ");
                                            int staffPhonenumber4 = Integer.parseInt(scan());
                                            System.out.println("ENTER NEW RECEPTIONIST'S SALARY: ");
                                            Double staffSalary4 = Double.parseDouble(scan());
                                            System.out.println("ENTER USERNAME FOR NEW RECEPTIONIST: ");
                                            String staffUsername4 = scan();
                                            System.out.println("ENTER PASSWORD FOR NEW RECEPTIONIST: ");
                                            String staffPassword4 = scan();
                                            hotelplaza.registerNewReception( staffTitle4, staffFirstname4, staffLastname4, staffPhonenumber4, staffSalary4, staffUsername4, staffPassword4);
                                            hotelplaza.getListOfStaff().forEach(System.out::println);
                                            System.out.println("\n----------------- NEW RECEPTIONIST REGISTERED SUCCESSFULLY -----------------");





                                            break;
                                        case "5":
                                            //going back to main menu
                                            break;
                                    }
                                    //Main.serialize( hotelplaza,"Database.ser");
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


