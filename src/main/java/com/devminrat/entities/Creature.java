package com.devminrat.entities;

import com.devminrat.Coordinates;

import java.util.LinkedList;

import static com.devminrat.Field.entities;
import static com.devminrat.utils.EntityPathFinder.findPathToTarget;

public abstract class Creature extends Entity {
    private int health = 20;

    public Creature(Coordinates position, String sprite) {
        super(position, sprite);
    }

    public void makeMove() {
        var path = getPathToTarget(this.getPosition());

        entities.remove(this.getPosition());
        entities.put(path.peek(), this);
        this.setPosition(path.peek());

        //TODO: if we have a path to target, when we make a move we should check Node in front of it, because it might not be free.
        // If the Node not free, we should recalculate path.
        // As for predator we should recalculate path each move, because herbivore moves too.
    }

    public LinkedList<Coordinates> getPathToTarget(Coordinates coordinates) {
        return findPathToTarget(this);
    }

    public void eat(Coordinates coordinates, int health) {
        this.setPosition(coordinates);
        setHealth(health);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
