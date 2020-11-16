package battleship;

import java.util.Scanner;

public class UserMenu {

    UserMenu() {
        Field field1 = new Field();
        Field field2 = new Field();
        Operations operations = new Operations();
        player(field1, "Player 1");
        player(field2, "Player 2");
        operations.game(field1.field, field2.field);
    }

    void player(Field field, String name) {
        System.out.println("\n" + name + ", place your ships on the game field\n");
        field.printField();
        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        field.shipPlacer("Aircraft Carrier");

        System.out.println("\nEnter the coordinates of the Battleship (4 cells):\n");
        field.shipPlacer("Battleship");

        System.out.println("\nEnter the coordinates of the Submarine (3 cells):\n");
        field.shipPlacer("Submarine");

        System.out.println("\nEnter the coordinates of the Cruiser (3 cells):\n");
        field.shipPlacer("Cruiser");

        System.out.println("\nEnter the coordinates of the Destroyer (2 cells):\n");
        field.shipPlacer("Destroyer");

        System.out.println("\nPress Enter and pass the move to another player\n");
        new Scanner(System.in).nextLine();
    }
}