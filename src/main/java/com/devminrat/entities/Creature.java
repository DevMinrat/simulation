package com.devminrat.entities;

import com.devminrat.Coordinates;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static com.devminrat.utils.EntityPathFinder.findPathToTarget;

public abstract class Creature extends Entity {
    private int health = 20;

    public Creature(Coordinates position, String sprite) {
        super(position, sprite);
    }

    public LinkedHashMap<Coordinates, Entity> makeMove(LinkedHashMap<Coordinates, Entity> entities) {
        var target = checkTargetAround(entities, this);

        if (target != null) {
            eat(entities, target);
        } else {
            var path = getPathToTarget(entities);

            if (path != null)
                go(entities, path.peek());
        }

        return entities;
    }

    private LinkedList<Coordinates> getPathToTarget(LinkedHashMap<Coordinates, Entity> entities) {
        return findPathToTarget(this, entities);
    }

    private void eat(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        go(entities, coordinates);
        setHealth(this.health + 5);
    }

    private void go(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        entities.remove(this.getPosition());
        entities.put(coordinates, this);
        this.setPosition(coordinates);
        setHealth(this.health - 1);
    }

    private static Coordinates checkTargetAround(LinkedHashMap<Coordinates, Entity> entities, Creature creature) {
        Class<? extends Entity> target = creature.getClass().equals(Herbivore.class) ? Food.class : Herbivore.class;
        Coordinates position = creature.getPosition();
        int row = position.X;
        int col = position.Y;
        var top = new Coordinates(row - 1, col);
        var bottom = new Coordinates(row + 1, col);
        var left = new Coordinates(row, col - 1);
        var right = new Coordinates(row, col + 1);
        Coordinates[] coordinatesAround = {top, bottom, left, right};

        for (Coordinates coord : coordinatesAround) {
            if (entities.containsKey(coord) && entities.get(coord).getClass().equals(target))
                return coord;
        }

        return null;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }


}
