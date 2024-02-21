package com.devminrat.utils;

import com.devminrat.Coordinates;
import com.devminrat.Field;
import com.devminrat.FieldConsoleRender;
import com.devminrat.entities.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Actions {

    public static void nextTurn(Field field, FieldConsoleRender cr) {
        System.out.println("====================================================  NEW STEP  =============================================================");
        LinkedHashMap<Coordinates, Entity> copyEntities = new LinkedHashMap<>(field.getEntities());
        for (Entity entity : field.getEntities().values()) {
            if (entity instanceof Creature) {
                copyEntities = ((Creature) entity).makeMove(new LinkedHashMap<>(copyEntities)); // Передаем копию карты
                field.setEntities(copyEntities);
                System.out.println(entity + " " + entity.getPosition());
                cr.printField(copyEntities);

                addEntitiesIfNeeded(field);
            }
        }
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

        if (foodCount == 0) {
            field.addFood(5);
        } else if (herbivoreCount == 0) {
            field.addHerbivores(5);
        } else if (predatorCount == 0) {
            field.addPredators(5);
        }

    }
}
