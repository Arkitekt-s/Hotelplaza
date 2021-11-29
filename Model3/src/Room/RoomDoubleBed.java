package Room;

public class RoomDoubleBed extends Room {
    private boolean hasBalcony;
    private boolean hasSeaView;
    private boolean hasTV;
    private boolean hasAirConditioner;
    private boolean hasHairDryer;
    private boolean hasIron;
    private boolean hasShower;


    public RoomDoubleBed(String roomType, int roomNumber, int floorNumber, int numberOfBeds, double pricePerNight, boolean hasBalcony, boolean hasSeaView, boolean hasTV, boolean hasAirConditioner, boolean hasHairDryer, boolean hasIron, boolean hasShower) {
        super(roomType, roomNumber, floorNumber, numberOfBeds, pricePerNight);
        this.hasBalcony = hasBalcony;
        this.hasSeaView = hasSeaView;
        this.hasTV = hasTV;
        this.hasAirConditioner = hasAirConditioner;
        this.hasHairDryer = hasHairDryer;
        this.hasIron = hasIron;
        this.hasShower = hasShower;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }
    public boolean isHasSeaView() {
        return hasSeaView;
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
    public boolean isHasIron() {
        return hasIron;
    }
    public boolean isHasShower() {
        return hasShower;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }
    public void setHasSeaView(boolean hasSeaView) {
        this.hasSeaView = hasSeaView;
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
    public void setHasIron(boolean hasIron) {
        this.hasIron = hasIron;
    }
    public void setHasShower(boolean hasShower) {
        this.hasShower = hasShower;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nhasBalcony: " + translate(hasBalcony) +
                "\nhasSeaView: " + translate(hasSeaView) +
                "\nhas TV: " + translate(hasTV) +
                "\nhasAirConditioner: " + translate(hasAirConditioner) +
                "\nhasHairDryer: " + translate(hasHairDryer) +
                "\nhasIron: " + translate(hasIron) +
                "\nhasShower: " + translate(hasShower) + "\n";
    }


    //method can gust checkin in doubleroom
    public static void checkInDoubleBed(int numberOfGusts, boolean isOccupied) {
        if (availableDoubleBed(numberOfGusts, isOccupied) == true) {
            System.out.println("You can check in");
        }
        else{
            System.out.println("You can not check in");
        }
    }
    public static boolean availableDoubleBed(int numberOfGusts, boolean isOccupied) {
        if (numberOfGusts >= 0 && numberOfGusts<=2 && isOccupied == false) {
            return true;
        }
        else{
            System.out.println("This room can not accept more than two person");
        }
        return false;
    }
    //method can gust checkin in singleroom


}