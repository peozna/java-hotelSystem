package nattrn_2;
import java.time.LocalDate;

/**
 * Klassen Booking representerar en bokning i systemet.
 *
 * En bokning innehåller:
 * - gäst (User)
 * - rum (Room)
 * - incheckningsdatum
 * - utcheckningsdatum
 *
 * Den används för att lagra en reservation av ett hotellrum.
 */

public class Booking {
    private User guest;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;

    /**
     * Konstruktor skapar en ny bokning med gäst, rum och tidsperiod.
     *
     * @param guest användaren som bokar
     * @param room rummet som bokas
     * @param checkIn incheckningsdatum
     * @param checkOut utcheckningsdatum
     */
    public Booking (User guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.guest = guest;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public User getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    /**
     * Returnerar en textrepresentation av bokningen.
     */
    @Override
    public String toString() {
        return "Booking " + guest +
                " | Room: " + room +
                " | Check In: " + checkIn +
                " | Check Out:" + checkOut;
    }
}
