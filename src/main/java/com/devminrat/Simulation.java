package com.devminrat;

import static com.devminrat.utils.Actions.nextTurn;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        // Map          +
        // Step counter -
        // Field render +
        // actions      -
        //nextTurn() - просимулировать и отрендерить один ход
        //startSimulation() - запустить бесконечный цикл симуляции и рендеринга
        //pauseSimulation() - приостановить бесконечный цикл симуляции и рендеринга

        Field field = new Field();
        field.initEntities();

        FieldConsoleRender cr = new FieldConsoleRender();
        cr.printField(field.getEntities());


        for (int i = 0; i < 50; i++) {
            Thread.sleep(1000);
            nextTurn(field, cr);
        }

    }
}