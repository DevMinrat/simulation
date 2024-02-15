package com.devminrat.entities;

import com.devminrat.Coordinates;

public class Predator extends Creature {
    private static final String SPRITE = EntitySprite.PREDATOR.getSprite();

    public Predator(Coordinates position) {
        super(position, SPRITE);

    }
}
