package com.devminrat.utils;

import com.devminrat.Coordinates;
import com.devminrat.Field;
import com.devminrat.FieldConsoleRender;
import com.devminrat.entities.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Actions {

    public static void nextTurn(Field field, FieldConsoleRender cr) {
        //TODO: need to refactoring this. Use DAO or similar
        System.out.println("====================================================  NEW STEP  =============================================================");
        var entities = field.getEntities();
        System.out.println(entities.values());
        LinkedHashMap<Coordinates, Entity> copyEntities = new LinkedHashMap<>(entities);
        System.out.println(copyEntities.equals(entities));

        for (Entity entity : entities.values()) {
            if (entity instanceof Creature) {
                System.out.println(1);
                copyEntities = ((Creature) entity).makeMove(copyEntities);
                field.setEntities(copyEntities);
                cr.printField(copyEntities);

                addEntitiesIfNeeded(field);
            }
        }
        System.out.println(entities.values());
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
