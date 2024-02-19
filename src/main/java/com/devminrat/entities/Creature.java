package com.devminrat.entities;

import com.devminrat.Coordinates;

import java.util.HashMap;
import java.util.LinkedList;

import static com.devminrat.utils.EntityPathFinder.findPathToTarget;

public abstract class Creature extends Entity {
    private int health = 20;

    public Creature(Coordinates position, String sprite) {
        super(position, sprite);
    }

    public HashMap<Coordinates, Entity> makeMove(HashMap<Coordinates, Entity> entities) {
        var path = getPathToTarget(entities);
        entities.remove(this.getPosition());
        entities.put(path.peek(), this);
        this.setPosition(path.peek());

        return entities;
    }

    public LinkedList<Coordinates> getPathToTarget(HashMap<Coordinates, Entity> entities) {
        return findPathToTarget(this, entities);
    }

    public void eat(Coordinates coordinates) {
        go(coordinates);
        setHealth(this.health + 5);
    }

    public void go(Coordinates coordinates) {
        this.setPosition(coordinates);
        setHealth(this.health - 1);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
