package com.devminrat.utils;

import com.devminrat.Coordinates;
import com.devminrat.Field;
import com.devminrat.FieldConsoleRender;
import com.devminrat.entities.*;

import java.util.*;

public class Actions {

    public static void nextTurn(Field field, FieldConsoleRender cr) {
        System.out.println("====================================================  NEW STEP  =============================================================");
        var entities = field.getEntities();
        LinkedHashMap<Coordinates, Entity> copyEntities = new LinkedHashMap<>(entities);

        for (Entity entity : entities.values()) {
            if (entity instanceof Creature creature) {
                if (creature.isForDeletion()) return;
                System.out.println(creature.getHealth());
                System.out.println(creature.getSpeed());
                System.out.println("------------------------------------------------------------------");
                for (int i = 0; i < creature.getSpeed(); i++) {
                    copyEntities = creature.makeMove(copyEntities);
                    field.setEntities(copyEntities);
                    cr.printField(copyEntities);

                    addEntitiesIfNeeded(field);
                }
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
