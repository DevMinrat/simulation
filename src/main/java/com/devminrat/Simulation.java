package com.devminrat;

import com.devminrat.gui.GridPanel;

import javax.swing.*;
import java.awt.*;

import static com.devminrat.utils.Actions.nextTurn;

public class Simulation {
    public static void main(String[] args) {
        Field field = new Field();
        field.initEntities();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GridPanel gridPanel = new GridPanel(Field.ROWS, Field.COLS, 50, field.getEntities());

            Button button = new Button("repaint");
            button.setSize(120, 3);
            button.addActionListener(e -> {
                nextTurn(field, gridPanel);
            });

            frame.add(gridPanel, BorderLayout.CENTER);
            frame.add(button, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            gridPanel.createBoardImage();

//            nextTurn(field, gridPanel);
        });


//        FieldConsoleRender cr = new FieldConsoleRender();
//        cr.printField(field.getEntities());
//
//
//        for (int i = 0; i < 50; i++) {
//            nextTurn(field, cr);
//        }

    }
}