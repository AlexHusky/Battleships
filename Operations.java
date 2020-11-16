package battleship;

import java.util.Scanner;

public class Operations {
    String[][] emptyField1;
    String[][] emptyField2;

    Operations() {
        this.emptyField1 = new String[11][11];
        this.emptyField2 = new String[11][11];

    }

    void game(String[][] field1, String[][] field2) {
        emptyField(emptyField1);
        emptyField(emptyField2);

        while (!sunkCheck(field1) || !sunkCheck(field2)) {
            player1(field1, field2);
            if (sunkCheck(field1) || sunkCheck(field2)) {
                break;
            }
            player2(field1, field2);
        }
    }

    void player1(String[][] field1, String[][] field2) {

        printField(emptyField2);
        System.out.println("---------------------");
        printField(field1);
        System.out.println("\nPlayer 1, it's your turn:\n");
        shot(field2, emptyField2);

    }

    void player2(String[][] field1, String[][] field2) {

        printField(emptyField1);
        System.out.println("---------------------");
        printField(field2);
        System.out.println("\nPlayer 2, it's your turn:\n");
        shot(field1, emptyField1);

    }

    // taking a shot
     void shot(String[][] field, String[][] emptyField) {

        boolean flag = true;

        while (flag) {
            String shot = new Scanner(System.in).nextLine();
            System.out.println();

            String regex = "[A-Z][0-9][0-9]?";
            if (!shot.matches(regex)) {
                System.out.println("Error");
                return;
            }
            int i = Character.getNumericValue(shot.charAt(0)) - 9;
            int j = Integer.parseInt(new StringBuilder(shot).deleteCharAt(0).toString());

            if (j > 10 || i > 10) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else if (field[i][j].equals("O")) {

                emptyField[i][j] = "X";
                field[i][j] = "X";
                printField(emptyField);
                if (!sunkAshipSheck(field, i, j)) {
                    System.out.println("\nYou hit a ship!");
                    System.out.println("\nPress Enter and pass the move to another player\n...");
                    new Scanner(System.in).nextLine();
                } else if (!sunkCheck(field)) {
                    System.out.println("\nYou sank a ship!");
                    System.out.println("\nPress Enter and pass the move to another player");
                    new Scanner(System.in).nextLine();
                } else {
                    System.out.println("\nYou sank the last ship. You won. Congratulations!");
                }

                flag = false;

            } else {
                if(!field[i][j].equals("X")) {
                    field[i][j] = "M";
                    emptyField[i][j] = "M";
                }

                printField(emptyField);

                System.out.println("\nYou missed!");
                System.out.println("\nPress Enter and pass the move to another player\n...");
                new Scanner(System.in).nextLine();
                flag = false;
            }
        }
    }

    //creating empty field
    void emptyField(String[][] emptyField) {
        emptyField[0] = new String[]{" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        char acc = 'A';
        for (int j = 1; j < 11; j++) {
            emptyField[j][0] = Character.toString(acc);
            acc++;
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                emptyField[i][j] = "~";
            }
        }

    }

    // printing empty field
     void printField(String[][] field) {

        for (String[] q : field) {
            for (String q1 : q) {
                System.out.print(q1 + " ");
            }
            System.out.println();
        }
    }

    // Cheking if all the ships are sunk
     boolean sunkCheck(String[][] field) {
        boolean res = true;
        for (String[] q : field) {
            for (String q1 : q) {
                if (q1.equals("O")) {
                    res = false;
                    break;
                }
            }
            if(!res) {
                break;
            }
        }
        return res;
    }

    // Checking if a ship is sunk
    static boolean sunkAshipSheck(String[][] field, int n, int m) {
        boolean res = true;

        int left;
        int right;
        int top;
        int bottom;
        if (m > 1) {
            left = m - 1;
        } else {
            left = m;
        }
        if (m < 10) {
            right = m + 1;
        } else {
            right = m;
        }
        if (n > 1) {
            top = n - 1;
        } else {
            top = n;
        }

        if (n < 10) {
            bottom = n + 1;
        } else {
            bottom = n;
        }

        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                if (field[i][j].equals("O")) {
                    res = false;
                    break;
                }
            }
            if (!res) {
                break;
            }
        }
        return res;
    }
}