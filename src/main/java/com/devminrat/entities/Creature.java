package com.devminrat.entities;

import com.devminrat.Coordinates;

public abstract class Creature extends Entity {
    private int health = 20;

    public Creature(Coordinates position, String sprite) {
        super(position, sprite);
    }

    public void move(Coordinates coordinates) {
        this.setPosition(coordinates);
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
