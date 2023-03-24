package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get screen room size from user
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();
        System.out.println();
        // declare screenRoom
        int[][] screenRoom = new int[rows][columns];

//------------------------------------------------------------------
        // Show menu method
        showMenu(); // show menu
        // while there is input do ...
        while (scanner.hasNext()) {
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1 -> {
                    showSeats(screenRoom);
                    showMenu();
                }
                case 2 -> {
                    buyTicket(screenRoom);
                    showMenu();
                }
                case 3 -> {
                    statistics(screenRoom);
                    showMenu();
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown number entered");
            }
        }

    }

    // Show menu
    private static void showMenu (){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    // method: Show cinema room array
    public static void showSeats(int[][] screenRoom) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= screenRoom[0].length; i++) { // print columns header
            System.out.print(" " + i);
        }
        System.out.println(); // start on newline
        for (int i = 0; i < screenRoom.length; i++) {
            System.out.print(i + 1); // print row number
            for (int j = 0; j < screenRoom[i].length; j++) {
                if (screenRoom[i][j] == 0) {
                    System.out.print(" S"); // print available seats
                } else {
                    System.out.print(" B"); // print booked seats
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // method: Buy a ticket
    public static void buyTicket(int[][] screenRoom) {
        // Select seat
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowSelected = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int columnSelected = scanner.nextInt();
        if (rowSelected < 0 || rowSelected > screenRoom.length) {
            System.out.println("Wrong input!");
            buyTicket(screenRoom);
        }
        if (columnSelected < 0 || columnSelected > screenRoom[0].length) {
            System.out.println("Wrong input!");
            buyTicket(screenRoom);
        }
        if (screenRoom[rowSelected - 1][columnSelected - 1] == 1) {
            System.out.println("That ticket has already been purchased");
            buyTicket(screenRoom);
        } else {
            screenRoom[rowSelected - 1][columnSelected - 1] = 1; // Change value of selected seat in screenRoom array to 1
            // Calculate ticket price
            int rows = screenRoom.length;
            int columns = screenRoom[0].length;

            if(rows * columns <= 60){
                System.out.println("\nTicket price: $10");
            } else if (rows * columns > 60){
                int rowsFront = rows / 2;
                if (rowSelected <= rowsFront) {
                    System.out.println("\nTicket price: $10");
                } else {
                    System.out.println("\nTicket price: $8");
                }
            }
        }
    }

    public static void statistics(int[][] screenRoom) {
        // Calculate tickets sold
        int ticketsSold = 0; // Set counter to initial value zero
        for (int i = 0; i < screenRoom.length; i++) {
            for (int j = 0; j < screenRoom[i].length; j++) {
                ticketsSold += screenRoom[i][j];
            }
        }
        System.out.println("Number of purchased tickets: " + ticketsSold);

        // Calculate percentage sold
        int rows = screenRoom.length;
        int columns = screenRoom[0].length;
        double rowsD = screenRoom.length + .0;
        double columnsD = screenRoom[0].length + .0;
        double percentageSold = (ticketsSold / (rowsD * columnsD)) * 100;
        System.out.printf("Percentage: %.2f%%%n", percentageSold);

        // Calculate current income
        if (rows * columns <= 60) {
            int currentIncome = ticketsSold * 10;
            System.out.println("Current income: $" + currentIncome);
        } else if (rows * columns > 60) {
            int ticketFront = 0;
            for (int i = 0; i < rows / 2; i++){
                for (int j = 0; j < screenRoom[0].length ; j++) {
                    if (screenRoom[i][j] == 1) {
                        ticketFront += 1;
                    }
                }
            }
            int ticketBack = ticketsSold - ticketFront;
            int currentIncome = (ticketFront * 10) + (ticketBack * 8);
            System.out.println("Current income: $" + currentIncome);
        }

        // Calculate total income possible
        if (rows * columns <= 60) {
            int totalIncome = rows * columns * 10;
            System.out.println("Total income: $" + totalIncome);
        } else if (rows * columns > 60) {
            int rowsFront = rows / 2;
            int rowsBack = rows - rowsFront;
            int totalIncome = (rowsFront * columns * 10) + (rowsBack * columns * 8);
            System.out.println("Total income: $" + totalIncome);
        }
    }
}