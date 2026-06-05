package nattrn_2;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * HotelSystem hanterar all affärslogik för ett enkelt hotellbokningssystem.
 *
 * Klassen ansvarar för:
 * - hantering av användare (registrering och inloggning)
 * - hantering av rum (lägg till, ändra, ta bort och lista rum)
 * - hantering av bokningar (skapa, avboka och visa bokningar)
 *
 * All data lagras i minneslistor under programkörning.
 */

public class HotelSystem {
    //Lista över alla registrerade användare
    private List<User> users = new ArrayList<>();
    //Lista över alla rum
    private List<Room> rooms = new ArrayList<>();
    //Lista över alla bokningar
    private List<Booking> bookings = new ArrayList<>();
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Registrerar en ny användare om användarnamnet inte redan finns.
     *
     * @param username unikt användarnamn
     * @param role användarroll (gäst eller receptionist)
     * @return true om användaren skapades, annars false
     */
    public boolean registerUser(String username, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        User newUser = new User(username, role);

        users.add(newUser);
        return true;
    }

    /**
     * Lägger till ett nytt rum i systemet.
     * Kontrollerar att rumsnumret inte redan finns.
     */
    public boolean addRoom(int roomNumber, int beds, String roomType) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return false;
            }
        }

        Room newRoom = new Room(roomNumber, beds, roomType);

        rooms.add(newRoom);
        return true;
    }

    /**
     * Uppdaterar information om ett befintligt rum.
     * Används endast av receptionist.
     */
    public boolean changeRoom(int roomNumber, int beds, String roomType) {
        for (Room room : rooms) {
            if(roomNumber == room.getRoomNumber()) {
                room.setBeds(beds);
                room.setRoomType(roomType);
                return true;
            }
        }
        return false;
    }

    /**
     * Tar bort ett rum från systemet.
     * Returnerar false om rummet inte finns.
     */
    public boolean removeRoom(int roomNumber) {
            for (int i = 0; i < rooms.size(); i++) {
                if (rooms.get(i).getRoomNumber() == roomNumber) {
                    rooms.remove(i);
                    return true;
                }
            }
            return false;
    }

    /**
     * Skapar en bokning om rummet är ledigt under vald period.
     *
     * Regler:
     * - ett rum får inte dubbelbokas
     * - datum får inte överlappa befintliga bokningar
     *
     * @return true om bokningen skapades, annars false
     */
    public boolean createBooking(User currentUser, Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room)) {
                if (checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn())) {
                    return false;
                }
            }

        }

        Booking newBooking = new Booking(currentUser, room, checkIn, checkOut);

        bookings.add(newBooking);
        return true;
    }

    /**
     * Loggar in en användare baserat på användarnamn.
     * Sätter currentUser om inloggning lyckas.
     */
    public boolean logIn(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                this.currentUser = user;
                return true;
            }
        }

        this.currentUser = null;
        return false;
    }

    /**
     * Returnerar alla bokningar
     */
    public List<Booking> showAllBookings() {
        return bookings;
    }

    /**
     * Returnerar alla bokningar som tillhör inloggad användare.
     */
    public List<Booking> showUserBookings() {
        List<Booking> myBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getGuest().equals(currentUser)) {
                myBookings.add(booking);
            }
        }
        return myBookings;
    }

    /**
     * Avbokar en bokning om den tillhör inloggad användare.
     */
    public boolean cancelBooking(User currentUser, int roomNumber) {
        for (int i = 0; i < bookings.size(); i++) {

            Booking booking = bookings.get(i);

            if (booking.getRoom().getRoomNumber() == roomNumber &&
                    booking.getGuest().equals(currentUser)) {

                bookings.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returnerar lista över alla rum i systemet.
     */
    public List<Room> listAllRooms() {
        return rooms;
    }

    /**
     * Testdata - testdatan tog jag fram med AI
     * */
    public void initTestData() {
        // Användare (3 st)
        registerUser("admin", "receptionist");
        registerUser("anna", "guest");
        registerUser("erik", "guest");

        // Rum (10 st)
        addRoom(101, 1, "single");
        addRoom(102, 1, "single");
        addRoom(103, 2, "double");
        addRoom(104, 2, "double");
        addRoom(105, 1, "single");
        addRoom(106, 2, "double");
        addRoom(107, 1, "single");
        addRoom(108, 2, "double");
        addRoom(109, 1, "single");
        addRoom(110, 2, "double");

        // Bokningar (5 st)
        createBooking(users.get(1), rooms.get(0),
                LocalDate.parse("2026-06-10"),
                LocalDate.parse("2026-06-12"));

       createBooking(users.get(1), rooms.get(2),
               LocalDate.parse("2026-06-11"),
               LocalDate.parse("2026-06-13"));

        createBooking(users.get(2), rooms.get(4),
                LocalDate.parse("2026-06-14"),
                LocalDate.parse("2026-06-15"));

        createBooking(users.get(1), rooms.get(6),
                LocalDate.parse("2026-06-16"),
                LocalDate.parse("2026-06-18"));

        createBooking(users.get(2), rooms.get(8),
                LocalDate.parse("2026-06-19"),
                LocalDate.parse("2026-06-20"));
    }
}




