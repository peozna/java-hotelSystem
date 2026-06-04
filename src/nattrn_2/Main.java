package nattrn_2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HotelSystem hs = new HotelSystem();
        boolean runningFlag = true;

        Scanner input = new Scanner(System.in);
        String choice;

        while (runningFlag == true) {
            System.out.println("[1] Log in");
            System.out.println("[2] Register user");
            System.out.println("[3] Exit");

            choice = input.nextLine();

            if(choice.equals("1")) {
                System.out.print("Enter username");
                String username = input.nextLine();

                boolean answer = hs.logIn(username);

                    if(answer==false) {
                        System.out.print("Username not found");
                    } else {
                        boolean loggedIn = true;
                        while (loggedIn == true) {
                            System.out.println("[1] List all rooms");
                            System.out.println("[2] Show room");
                            System.out.println("[3] Search for room");
                            System.out.println("[4] Create booking");
                            System.out.println("[5] Cancel booking");
                            System.out.println("[6] View my bookings");
                            System.out.println("[7] Log out");

                            choice = input.nextLine();

                            switch(choice) {
                                case "1":
                                        for(Room room : hs.listAllRooms()) {
                                            System.out.println(room);
                                        }

                                    break;
                            }
                        }
                    }

            }



        }

    }
}
