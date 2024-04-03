package com.devminrat;

import com.devminrat.gui.MainFrame;

public class Simulation {
    public static void main(String[] args) {
        Field field = new Field();
        field.initEntities();

        new MainFrame(field);

    }
}