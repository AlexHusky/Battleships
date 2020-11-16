package battleship;

import java.util.Scanner;

public class Field {
     String[][] field;
     String[][] arrCheck;

    Field() {
        // initializing empty battlefield
        field = new String[11][11];
        field[0] = new String[]{" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        arrCheck = new String[11][11];
        char acc = 'A';
        for (int j = 1; j < 11; j++) {
            field[j][0] = Character.toString(acc);
            acc++;
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                field[i][j] = "~";
            }
        }
    }
    // printing field method
     void printField() {
        for (String[] q : field) {
            for (String q1 : q) {
                System.out.print(q1 + " ");
            }
            System.out.println();
        }
    }

    //ships placing method
    void shipPlacer(String type) {
        boolean flag = true;
        while (flag) {
            String ship = new Scanner(System.in).nextLine();
            System.out.println();

            String regex = "[A-J][1-9]0? [A-J][1-9]0?";
            if (!ship.matches(regex)) {
                System.out.println("Error");
                return;
            }

            String[] shipParts = ship.split(" ");
            StringBuilder strBil1 = new StringBuilder(shipParts[0]);
            strBil1.deleteCharAt(0);
            StringBuilder strBil2 = new StringBuilder(shipParts[1]);
            strBil2.deleteCharAt(0);
            int i1 = Character.getNumericValue(shipParts[0].charAt(0)) - 9;

            int j1 = Integer.parseInt(strBil1.toString());

            int i2 = Character.getNumericValue(shipParts[1].charAt(0)) - 9;

            int j2 = Integer.parseInt(strBil2.toString());

            if (i1 > i2) {
                int temp = i1;
                i1 = i2;
                i2 = temp;
            }
            if (j1 > j2) {
                int temp = j1;
                j1 = j2;
                j2 = temp;
            }

            if (validator(i1, j1, i2, j2, type)) {
                for (int i = i1; i <= i2; i++) {
                    for (int j = j1; j <= j2; j++) {
                        field[i][j] = "O";
                    }
                }
                printField();
                flag = false;
            }

        }


    }
    //game rules checking method
    boolean validator(int i1, int j1, int i2, int j2, String type) {
        int length = 0;
        switch (type) {
            case "Aircraft Carrier":
                length = 5;
                break;
            case "Battleship":
                length = 4;
                break;
            case "Submarine":
            case "Cruiser":
                length = 3;
                break;
            case "Destroyer":
                length = 2;
                break;
            default:
                break;
        }
        boolean res = true;

        boolean flag1 = true;

        //checking for linearity and length of a ship
        if (i1 != i2 && j1 != j2) {
            System.out.println("Error! Wrong ship location! Try again:\n");
            res = false;
            flag1 = false;
        } else if ((i2 - i1) + (j2 - j1) != length - 1) {
            System.out.println("Error! Wrong length of the " + type + "! Try again:\n");
            res = false;
            flag1 = false;
        }

        //checking for placing ships according to each other
        boolean flag2 = false;
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (arrCheck[i][j] == "O") {
                    flag2 = true;
                    break;
                }
            }
        }
        if (flag2) {
            res = false;
            System.out.println("Error! You placed it too close to another one. Try again:\n");
        } else {

            //creating cheking array
            if (flag1) {
                int left;
                int right;
                int top;
                int bottom;
                if (j1 > 1) {
                    left = j1 - 1;
                } else {
                    left = j1;
                }
                if (j2 < 10) {
                    right = j2 + 1;
                } else {
                    right = j2;
                }
                if (i1 > 1) {
                    top = i1 - 1;
                } else {
                    top = i1;
                }

                if (i1 < 10) {
                    bottom = i1 + 1;
                } else {
                    bottom = i1;
                }

                for (int i = top; i <= bottom; i++) {
                    for (int j = left; j <= right; j++) {
                        arrCheck[i][j] = "O";
                    }
                }
            }
        }

        return res;
    }
}