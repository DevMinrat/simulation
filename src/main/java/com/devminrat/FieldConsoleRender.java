package com.devminrat;

import static com.devminrat.Field.entities;

public class FieldConsoleRender {
    public final static String ANSI_RESET = "\u001b[0m";
    public final static String CELL_COLOR_1 = "\u001b[48;5;23;38;5;23m";
    public final static String CELL_COLOR_2 = "\u001b[48;5;64;38;5;64m";

    public void colorizeField() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 15; col++) {
                var c = new Coordinates(row, col);

                if ((row + col) % 2 == 0) {
                    if (entities.containsKey(c)) {
                        var e = entities.get(c);
                        System.out.print(CELL_COLOR_1 + " " + e.getSprite() + " " + ANSI_RESET);
                    } else
                        System.out.print(CELL_COLOR_1 + " \uD834\uDD17! " + ANSI_RESET);
                } else {
                    if (entities.containsKey(c)) {
                        var e = entities.get(c);
                        System.out.print(CELL_COLOR_2 + " " + e.getSprite() + " " + ANSI_RESET);
                    } else
                        System.out.print(CELL_COLOR_2 + " \uD834\uDD17! " + ANSI_RESET);
                }
            }

            System.out.println();
        }
    }

}