package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Theatre {
    private final String[][] hall;
    private int soldTickets = 0;
    private int currentIncome;

    public Theatre(int rows, int seats) {
        hall = new String[rows][seats];
        fillHall();
    }

    public void printHall() {
        System.out.println("Cinema:");
        String firstRow = "  " + IntStream.rangeClosed(1, hall[0].length)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(firstRow);

        for (int i = 0; i < hall.length; i++) {
            System.out.println(i + 1 + " " + String.join(" ", hall[i]));
        }
    }

    public void buyTicket() {
        boolean ticketSold = false;
        while (!ticketSold) {
            System.out.println("Enter a row number:");
            Scanner scanner = new Scanner(System.in);
            int row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();
            if (wrongCoordinates(row, seat)) {
                System.out.println("Wrong input!");
                continue;
            }

            if (ticketAlreadySold(row, seat)) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            int ticketPrice = calculateSeatCost(row, seat);
            System.out.println("Ticket price: $" + ticketPrice);
            ticketSold = true;
            soldTickets++;
            currentIncome += ticketPrice;
        }

    }

    private boolean wrongCoordinates(int row, int seat) {
        return row > hall.length || seat > hall[0].length || row <= 0 || seat <= 0;
    }

    private boolean ticketAlreadySold(int row, int seat) {
        return !hall[row - 1][seat - 1].equals("S");
    }

    private int calculateCost() {
        if (hall.length * hall[0].length <= 60) {
            return 10 * hall.length * hall[0].length;
        } else {
            int firstHalf = hall.length / 2;
            return firstHalf * hall[0].length * 10 + (hall.length - firstHalf) * hall[0].length * 8;
        }
    }

    private void fillHall() {
        for (int i = 0; i < hall.length; i++) {
            for (int j = 0; j < hall[0].length; j++) {
                hall[i][j] = "S";
            }
        }
    }

    private int calculateSeatCost(int row, int seat) {
        int size = hall[0].length * hall.length;
        hall[row - 1][seat - 1] = "B";
        if (size <= 60) {
            return 10;
        } else {
            int half = hall.length / 2;
            return row <= half ? 10 : 8;
        }
    }

    public void showStatistics() {
        int seats = hall.length * hall[0].length;
        int totalIncome = calculateCost();
        double percentage = soldTickets == 0 ? 0 : (double) soldTickets / seats * 100;
        System.out.println("Number of purchased tickets: " + soldTickets);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}
