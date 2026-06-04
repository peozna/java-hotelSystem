package nattrn_2;

public class Room {
    private int roomNumber;
    private int beds;
    private String roomType;

    public Room(int roomNumber, int beds, String roomType) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getBeds() {
        return beds;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber +
                " | Beds: " + beds +
                " | Type: " + roomType;
    }
}
