package com.devminrat;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        FieldConsoleRender cr = new FieldConsoleRender();
        Field field = new Field();

        field.setEntities();
        cr.colorizeField();


    }
}