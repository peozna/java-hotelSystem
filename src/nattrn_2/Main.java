package nattrn_2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Main-klassen startar programmet och hanterar användarens menyflöde.
 * Här sker inloggning, registrering samt navigation till hotellsystemets funktioner
 * såsom rumshantering, bokningar och sökning.
 *
 * För att kunna logga in måste du skriva ett av dessa användarnamn, admin, anna, eller erik.
 */

public class Main {

    public static void main(String[] args) {
        HotelSystem hs = new HotelSystem();
        hs.initTestData();  // Initierar testdata för att simulera filinläsning.
        // Detta gör systemet direkt testbart utan extern filhantering.
        boolean runningFlag = true;

        Scanner input = new Scanner(System.in);
        String choice;
        String loggedChoice;

        while (runningFlag == true) {
            System.out.println("[1] Log in");
            System.out.println("[2] Register user");
            System.out.println("[3] Exit");

            choice = input.nextLine();

            // Hanterar inloggning av användare.
            // Om inloggningen lyckas öppnas en meny för inloggade funktioner.
            if(choice.equals("1")) {
                System.out.println("Enter username: ");
                String username = input.nextLine();

                boolean answer = hs.logIn(username);

                    if(answer==false) {
                        System.out.println("Username not found");
                    } else {
                        boolean loggedIn = true;
                        // Meny för inloggade användare.
                        // Här kan användaren hantera rum, bokningar och sökfunktioner.
                        while (loggedIn == true) {
                            System.out.println("[1] List all rooms");
                            System.out.println("[2] Show room");
                            System.out.println("[3] Search for room");
                            System.out.println("[4] Create booking");
                            System.out.println("[5] Cancel booking");
                            System.out.println("[6] View my bookings");
                            System.out.println("[7] Log out");

                            if (hs.getCurrentUser().getRole().equals("receptionist")) {
                                System.out.println("[8] Add room");
                                System.out.println("[9] Edit room");
                                System.out.println("[10] Remove room");
                                System.out.println("[11] View all bookings");
                            }

                            loggedChoice = input.nextLine();

                            switch(loggedChoice) {
                                case "1":  // Visar alla tillgängliga rum i systemet.
                                    List <Room> rooms = hs.listAllRooms();
                                    if(rooms.isEmpty()) {
                                        System.out.println("No rooms found!");
                                    } else {
                                        for(Room room : rooms) {
                                            System.out.println(room);
                                        }
                                    }
                                    break;

                                case "2":  // Söker och visar detaljer för ett specifikt rum baserat på rumsnummer.
                                    System.out.println("Enter roomnumber");
                                    int roomChoice = Integer.parseInt(input.nextLine());
                                    boolean found = false;

                                    for (Room room : hs.listAllRooms()) {
                                        if (room.getRoomNumber() == roomChoice) {
                                            System.out.println(room);
                                            found = true;
                                        }
                                    }
                                    if (found == false) {
                                        System.out.println("Room not found.");
                                    }
                                    break;

                                // Söker efter rum baserat på typ eller antal bäddar.
                                // Användaren väljer sökmetod i en undermeny.
                                case "3":
                                    System.out.println("[1]Search for room type: ");
                                    System.out.println("[2]Search for number of beds: ");

                                    choice = input.nextLine();

                                    if(choice.equals("1")) {
                                        boolean foundType = false;
                                        System.out.println("Search for room type: ");
                                        String type = input.nextLine();

                                        for (Room room : hs.listAllRooms()) {
                                            if(type.equals(room.getRoomType())) {
                                                System.out.print(room);
                                                foundType = true;
                                            }
                                        }
                                        if (foundType == false) {
                                            System.out.println("Room type not found!");
                                        }

                                    } else if (choice.equals("2")) {
                                        boolean foundBeds = false;
                                        System.out.println("Search for number of beds: ");
                                        int number = Integer.parseInt(input.nextLine());

                                        for (Room room : hs.listAllRooms()) {
                                            if(number == room.getBeds()) {
                                                System.out.print(room);
                                                foundBeds = true;
                                            }
                                        }
                                        if (foundBeds == false) {
                                            System.out.println("Room not found!");
                                        }
                                    }
                                    break;

                                // Skapar en bokning för inloggad användare.
                                // Kontrollerar först att rummet existerar och att datum är giltiga.
                                // Bokningen skickas sedan till HotelSystem för validering.
                                case "4":
                                    Room roomBook = null;
                                    LocalDate checkIn;
                                    LocalDate checkOut;
                                    System.out.println("Enter roomnumber you wish to book: ");
                                    int chosenRoom = Integer.parseInt(input.nextLine());

                                    for (Room room : hs.listAllRooms()) {
                                        if(chosenRoom == room.getRoomNumber()) {
                                            roomBook = room;
                                        }
                                    }

                                    if(roomBook == null) {
                                        System.out.println("Room not found");
                                        break;
                                    }

                                    System.out.println("Enter check-in date, format YYYY-MM-DD: ");
                                    checkIn = LocalDate.parse(input.nextLine());
                                    System.out.println("Enter check-out date, format YYYY-MM-DD: ");
                                    checkOut = LocalDate.parse(input.nextLine());

                                    if(hs.createBooking(hs.getCurrentUser(), roomBook, checkIn, checkOut) == true) {
                                        System.out.println("Booking successful!");
                                    } else {
                                        System.out.println("Booking failed!");
                                    }
                                    break;

                                // Avbokar en befintlig bokning för inloggad användare baserat på rumsnummer.
                                case "5":
                                    System.out.println("Enter roomnumber to cancel booking: ");
                                    int roomCancel = Integer.parseInt(input.nextLine());

                                    if(hs.cancelBooking(hs.getCurrentUser(), roomCancel) == true) {
                                        System.out.println("Booking cancelled!");
                                    } else {
                                        System.out.println("Error!");
                                    }
                                    break;

                                case "6": // Visar alla bokningar tillhörande den inloggade användaren.
                                    List<Booking> myBookings = hs.showUserBookings();
                                    if(myBookings.isEmpty()) {
                                        System.out.println("No bookings!");
                                    } else {
                                        for (Booking booking : myBookings) {
                                            System.out.println(booking);
                                        }
                                    }
                                    break;

                                case "7": // Loggar ut användaren och återgår till startmeny
                                    loggedIn = false;
                                    break;

                                case "8":
                                    int newRoomNum;
                                    int numOfBeds;
                                    String type;
                                    System.out.println("Enter new roomnumber: ");
                                    newRoomNum = Integer.parseInt(input.nextLine());

                                    System.out.println("Enter number of beds: ");
                                    numOfBeds = Integer.parseInt(input.nextLine());

                                    System.out.println("Enter room type: ");
                                    type = input.nextLine();

                                    if (!hs.addRoom(newRoomNum, numOfBeds, type)) {
                                        System.out.println("Room elready exist!");
                                    } else {
                                        System.out.println("Room added!");
                                    }
                                    break;

                                // Uppdaterar information om ett befintligt rum.
                                // Ändrar antal bäddar och rumstyp baserat på rumsnummer.
                                case "9":
                                    System.out.println("Enter room number: ");
                                    int roomNum = Integer.parseInt(input.nextLine());

                                    System.out.println("Enter bed count: ");
                                    int newBedCount = Integer.parseInt(input.nextLine());

                                    System.out.println("Enter room type: ");
                                    String newType = input.nextLine();

                                    if(!hs.changeRoom(roomNum, newBedCount, newType)) {
                                        System.out.println("Error!");
                                    } else {
                                        System.out.println("Changes successful!");
                                    }
                                    break;

                                    // Tar bort ett rum från systemet baserat på rumsnummer.
                                // Om rummet inte finns visas ett felmeddelande.
                                case "10":
                                    System.out.println("Enter room number you wish to remove: ");
                                    int remRoom = Integer.parseInt(input.nextLine());

                                    if(!hs.removeRoom(remRoom)) {
                                        System.out.println("Room not found!");
                                    } else {
                                        System.out.println("Room removal successful");
                                    }

                                break;

                                // Visar alla bokningar i systemet (endast för receptionist).
                                // Om inga bokningar finns visas ett meddelande.
                                case "11":
                                    List<Booking> allBookings = hs.showAllBookings();
                                    if(allBookings.isEmpty()) {
                                        System.out.println("No bookings!");
                                    } else {
                                        for (Booking booking : allBookings) {
                                            System.out.println(booking);
                                        }
                                    }

                                break;
                            }
                        }
                    }

            }

            //Start meny - registera en användare.
        if (choice.equals("2")) {
            System.out.println("Choose username: ");
            String newUser = input.nextLine();

            System.out.print("Enter role: ");
            String newRole = input.nextLine();

            if(!hs.registerUser(newUser, newRole)) {
                System.out.println("Username already taken: ");
            } else {
                System.out.println("Registration successful!");
            }
        }

        //Start meny avsluta programmet.
        if (choice.equals("3")) {
            runningFlag = false;
        }


        }

    }
}
