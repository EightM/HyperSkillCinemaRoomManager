package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        Theatre theatre = new Theatre(rows, seats);
        theatre.printHall();

        while (true) {
            printMenu();
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    theatre.printHall();
                    break;
                case 2:
                    theatre.buyTicket();
                    break;
                case 3:
                    theatre.showStatistics();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}