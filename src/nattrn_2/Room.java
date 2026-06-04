package nattrn_2;

/**
 * Klassen Room representerar ett hotellrum i systemet.
 *
 * Ett rum innehåller:
 * - rumsnummer (unik identifierare)
 * - antal bäddar
 * - rumstyp (t.ex. enkelrum eller dubbelrum)
 */

public class Room {
    private int roomNumber;
    private int beds;
    private String roomType;

    /**
     * Konstruktor skapar ett nytt rum med angivna värden.
     *
     * @param roomNumber unikt rumsnummer
     * @param beds antal bäddar i rummet
     * @param roomType typ av rum (enkelrum/dubbelrum)
     */

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

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * Returnerar en textrepresentation av rummet.
     */
    @Override
    public String toString() {
        return "Room " + roomNumber +
                " | Beds: " + beds +
                " | Type: " + roomType;
    }
}
