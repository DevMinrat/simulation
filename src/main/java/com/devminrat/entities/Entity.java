package com.devminrat.entities;

import com.devminrat.Coordinates;

public abstract class Entity {
    private Coordinates position;
    private final String sprite;

    public Entity(Coordinates position, String sprite) {
        this.position = position;
        this.sprite = sprite;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public String getSprite() {
        return sprite;
    }

}
