package Room;

public class RoomOneBed extends Room {
    private boolean hasBalcony;
    private boolean hasTV;
    private boolean hasAirConditioner;
    private boolean hasHairDryer;
    private boolean hasShower;

    public RoomOneBed(String roomType, int roomNumber, int floorNumber, int numberOfBeds, double pricePerNight, boolean hasBalcony, boolean hasTV, boolean hasAirConditioner, boolean hasHairDryer, boolean hasShower) {
        super(roomType, roomNumber, floorNumber, numberOfBeds, pricePerNight);
        this.hasBalcony = hasBalcony;
        this.hasTV = hasTV;
        this.hasAirConditioner = hasAirConditioner;
        this.hasHairDryer = hasHairDryer;
        this.hasShower = hasShower;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }
    public boolean isHasTV() {
        return hasTV;
    }
    public boolean isHasAirConditioner() {
        return hasAirConditioner;
    }
    public boolean isHasHairDryer() {
        return hasHairDryer;
    }
    public boolean isHasShower() {
        return hasShower;
    }
    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }
    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }
    public void setHasAirConditioner(boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
    }
    public void setHasHairDryer(boolean hasHairDryer) {
        this.hasHairDryer = hasHairDryer;
    }
    public void setHasShower(boolean hasShower) {
        this.hasShower = hasShower;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nhasBalcony=" + translate(hasBalcony) +
                "\nhas TV:" + translate(hasTV) +
                "\nhasAirConditioner=" + translate(hasAirConditioner) +
                "\nhasHairDryer=" + translate(hasHairDryer) +
                "\nhasShower=" + translate(hasShower) + "\n";
    }

    //method that check if the Doubleroom is Availabe
    public void checkInOneBed(int numberOfGusts, boolean isOccupied) {
        if (availableOneBed(numberOfGusts, isOccupied) == true) {
            System.out.println("You can check in");
        }
        else{
            System.out.println("You can not check in");
        }
    }
    public boolean availableOneBed(int numberOfGusts, boolean isOccupied) {
        if (numberOfGusts >= 0 && numberOfGusts<=1 && isOccupied == false) {
            return true;
        }
        else{
            System.out.println("This room can not accept more than two person");
        }
        return false;
    }


}
