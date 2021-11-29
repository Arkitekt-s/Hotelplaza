package Staff;
import java.io.Serializable;


public abstract class Staff implements Serializable {
    // To avoid error when deserializing after changes made in class that implements Serializable interface
    static final long serialVersionUID = 41L;

    // Attributes
    protected String title;
    protected String firstName;
    protected String lastName;
    protected int phoneNumber;
    protected double salary;

    // Constructor
    public Staff(String title, String firstName, String lastName, int phoneNumber, double salary) {
        setTitle(title);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setSalary(salary);
    }

    // getters
    public String getTitle() {
        return title;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public double getSalary() {
        return salary;
    }
    // setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setSalary (double salary) {
        this.salary = salary;
    }

    // toString
    public String toString() {
        return "TITLE: " + title + "\n" + "FIRST NAME: " + firstName + "\n" + "LAST NAME: " +
                lastName + "\n" + "PHONE NUMBER: " + phoneNumber + "\n" + "SALARY: " + salary + " kr.\n";
    }
}