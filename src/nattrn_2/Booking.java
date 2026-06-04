package nattrn_2;
import java.time.LocalDate;

public class Booking {
    private User guest;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;

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
}
