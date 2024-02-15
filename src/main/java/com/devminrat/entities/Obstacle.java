package com.devminrat.entities;

import com.devminrat.Coordinates;

public class Obstacle extends Entity {
    private static final String SPRITE = EntitySprite.OBSTACLE.getSprite();

    public Obstacle(Coordinates position) {
        super(position, SPRITE);
    }

}
