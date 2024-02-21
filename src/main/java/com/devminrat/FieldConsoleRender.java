package com.devminrat;

import com.devminrat.entities.Entity;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.devminrat.Field.*;

public class FieldConsoleRender {
    public final static String ANSI_RESET = "\u001b[0m";
    public final static String CELL_COLOR_1 = "\u001b[48;5;23;38;5;23m";
    public final static String CELL_COLOR_2 = "\u001b[48;5;64;38;5;64m";

    public void printField(LinkedHashMap<Coordinates, Entity> entities) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
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
