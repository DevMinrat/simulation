package com.devminrat.entities;

import com.devminrat.Coordinates;
import com.devminrat.gui.ButtonManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

import static com.devminrat.utils.EntityPathFinder.findPathToTarget;

public abstract class Creature extends Entity implements Removable {
    static Random random = new Random();

    private int health = random.nextInt(10, 31);
    private final int speed = random.nextInt(1, 4);
    public static final int NUTRITIONAL_VALUE = 5;
    private boolean forDeletion = false;

    public Creature(Coordinates position, String sprite) {
        super(position, sprite);
    }

    public abstract LinkedHashMap<Coordinates, Entity> makeMove(LinkedHashMap<Coordinates, Entity> entities);

    LinkedList<Coordinates> getPathToTarget(LinkedHashMap<Coordinates, Entity> entities) {
        return findPathToTarget(this, entities);
    }

    abstract void eat(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates);

    void go(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        entities.remove(this.getPosition());
        entities.put(coordinates, this);
        this.setPosition(coordinates);
        takeDamage(1);

        ButtonManager.setTextIntoInfoField(this.getClass().getSimpleName() + " moved to " + coordinates );
    }

    static Coordinates checkTargetAround(LinkedHashMap<Coordinates, Entity> entities, Creature creature) {
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

    void checkStarvation(LinkedHashMap<Coordinates, Entity> entities) {
        if (this.getHealth() <= 0) {
            this.death(entities);
        }
    }

    private void death(LinkedHashMap<Coordinates, Entity> entities) {
        entities.remove(this.getPosition());
    }

    public int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean isForDeletion() {
        return forDeletion;
    }

    @Override
    public void setForDeletion(boolean forDeletion) {
        this.forDeletion = forDeletion;
    }

    public int getSpeed() {
        return speed;
    }

    protected void takeDamage(int damage) {
        this.health -= damage;
    }
}
