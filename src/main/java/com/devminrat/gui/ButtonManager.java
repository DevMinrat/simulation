package com.devminrat.gui;

import javax.swing.*;
import java.awt.*;

public class ButtonManager {

    private static Button nextTurnButton;
    private static Button runLoopButton;

    private static Button pauseLoopButton;
    private static JLabel infoField;

    public static void initButtons() {
        nextTurnButton = new Button("Next turn");
        runLoopButton = new Button("Run loop simulation");
        pauseLoopButton = new Button("Pause simulation");
        infoField = new JLabel("Turn information");

        setAllButtonSize(150, 30);
    }

    private static void setAllButtonSize(int width, int height) {
        Dimension size = new Dimension(width, height);
        nextTurnButton.setPreferredSize(size);
        runLoopButton.setPreferredSize(size);
        pauseLoopButton.setPreferredSize(size);
        infoField.setPreferredSize(new Dimension(250,30));
    }

    public static void setButtonsEnabled(boolean b) {
        nextTurnButton.setEnabled(b);
        runLoopButton.setEnabled(b);;
    }

    public static Button getNextTurnButton() {
        return nextTurnButton;
    }

    public static Button getRunLoopButton() {
        return runLoopButton;
    }

    public static Button getPauseLoopButton() {
        return pauseLoopButton;
    }

    public static JLabel getInfoField() {
        return infoField;
    }

    public static void setTextIntoInfoField(String info) {
        infoField.setText(info);
    }

}
