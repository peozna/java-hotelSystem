package nattrn_2;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelSystem {
    private List<User> users = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

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

    public List<Booking> showAllBookings() {
        return bookings;
    }

    public List<Booking> showUserBookings() {
        List<Booking> myBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getGuest().equals(currentUser)) {
                myBookings.add(booking);
            }
        }
        return myBookings;
    }

    public List<Room> listAllRooms() {
        return rooms;
    }
}




