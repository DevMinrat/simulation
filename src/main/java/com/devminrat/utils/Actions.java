package com.devminrat.utils;

import com.devminrat.Coordinates;
import com.devminrat.Field;
import com.devminrat.FieldConsoleRender;
import com.devminrat.entities.Creature;
import com.devminrat.entities.Entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Actions {

    public static void nextTurn(Field field, FieldConsoleRender cr) {
        HashMap<Coordinates, Entity> copyEntities = new HashMap<>(field.getEntities());
        for (Entity entity : field.getEntities().values()) {
            if (entity instanceof Creature) {
                copyEntities = ((Creature) entity).makeMove(copyEntities);
                cr.printField(copyEntities);
                field.setEntities(copyEntities);
            }
        }
    }
}
