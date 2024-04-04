package com.devminrat.utils;

import com.devminrat.Coordinates;
import com.devminrat.Field;
import com.devminrat.entities.*;
import com.devminrat.gui.GridPanel;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.devminrat.gui.ButtonManager.*;

public class Actions {
    public static AtomicReference<SwingWorker<Void, Void>> turnWorker = new AtomicReference<>();

    public static void nextTurn(Field field, GridPanel gridPanel) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                var entities = field.getEntities();
                LinkedHashMap<Coordinates, Entity> copyEntities = new LinkedHashMap<>(entities);

                for (Entity entity : entities.values()) {
                    if (entity instanceof Creature creature) {
                        if (creature.isForDeletion()) return null;
                        for (int i = 0; i < creature.getSpeed(); i++) {
                            copyEntities = creature.makeMove(copyEntities);
                            field.setEntities(copyEntities);

                            addEntitiesIfNeeded(field);

                            gridPanel.updateEntities(copyEntities);

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                setButtonsEnabled(true);
                getPauseLoopButton().setEnabled(true);
            }
        };
        turnWorker.set(worker);
        worker.execute();
    }


    private static void addEntitiesIfNeeded(Field field) {
        var foodCount = 0;
        var herbivoreCount = 0;
        var predatorCount = 0;

        for (Entity entity : field.getEntities().values()) {
            if (entity.getClass().equals(Food.class)) {
                foodCount++;
            } else if (entity.getClass().equals(Herbivore.class)) {
                herbivoreCount++;
            } else if (entity.getClass().equals(Predator.class)) {
                predatorCount++;
            }
        }

        if (foodCount == 1) {
            field.addFood(5);
        } else if (herbivoreCount == 0) {
            field.addHerbivores(5);
        } else if (predatorCount == 0) {
            field.addPredators(5);
        }

    }
}
