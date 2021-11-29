package Room;

public class RoomSuite extends Room {
    private boolean hasBalcony;
    private boolean hasSeaView;
    private boolean hasTV;
    private boolean hasAirConditioner;
    private boolean hasMinibar;
    private boolean hasHairDryer;
    private boolean hasIron;
    private boolean hasSafe;
    private boolean hasBathtub;
    private boolean hasShower;

    public RoomSuite(String roomType, int roomNumber, int floorNumber, int numberOfBeds, double pricePerNight, boolean hasBalcony, boolean hasSeaView, boolean hasTV, boolean hasAirConditioner, boolean hasMinibar, boolean hasHairDryer, boolean hasIron, boolean hasSafe, boolean hasBathtub, boolean hasShower) {
        super(roomType, roomNumber, floorNumber, numberOfBeds, pricePerNight);
        this.hasBalcony = hasBalcony;
        this.hasSeaView = hasSeaView;
        this.hasTV = hasTV;
        this.hasAirConditioner = hasAirConditioner;
        this.hasMinibar = hasMinibar;
        this.hasHairDryer = hasHairDryer;
        this.hasIron = hasIron;
        this.hasSafe = hasSafe;
        this.hasBathtub = hasBathtub;
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
    public boolean isHasMinibar() {
        return hasMinibar;
    }
    public boolean isHasHairDryer() {
        return hasHairDryer;
    }
    public boolean isHasIron() {
        return hasIron;
    }
    public boolean isHasSafe() {
        return hasSafe;
    }
    public boolean isHasBathtub() {
        return hasBathtub;
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
    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }
    public void setHasHairDryer(boolean hasHairDryer) {
        this.hasHairDryer = hasHairDryer;
    }
    public void setHasIron(boolean hasIron) {
        this.hasIron = hasIron;
    }
    public void setHasSafe(boolean hasSafe) {
        this.hasSafe = hasSafe;
    }
    public void setHasBathtub(boolean hasBathtub) {
        this.hasBathtub = hasBathtub;
    }
    public void setHasShower(boolean hasShower) {
        this.hasShower = hasShower;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nhasBalcony: " + translate(hasBalcony) +
                "\nhasSeaView: " + translate(hasSeaView) +
                "\nhasTV: " + translate(hasTV) +
                "\nhasAirConditioner: " + translate(hasAirConditioner) +
                "\nhasMinibar: " + translate(hasMinibar) +
                "\nhasHairDryer: " + translate(hasHairDryer) +
                "\nhasIron: " + translate(hasIron ) +
                "\nhasSafe: " + translate(hasSafe) +
                "\nhasBathtub: " + translate(hasBathtub) +
                "\nhasShower: " + translate(hasShower) + "\n";
    }
//show available rooms
//mwthod can cheak roomsuite
public boolean availableSuite(int numberOfGusts, boolean isOccupied) {
    if (numberOfGusts >= 0 && numberOfGusts<=2 && isOccupied == false) {
        return true;
    }
    else{
        System.out.println("This room can not accept more than two person");
    }
    return false;
}
    //method can checkIN gust in room
    public void checkInSuiteBed(int numberOfGusts, boolean isOccupied) {
        if (availableSuite(numberOfGusts, isOccupied) == true) {
            System.out.println("You can check in");
        }
        else{
            System.out.println("You can not check in");
        }
    }


}

