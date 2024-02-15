package com.devminrat.entities;

import com.devminrat.Coordinates;

public class Herbivore extends Creature {
    private static final String SPRITE = EntitySprite.HERBIVORE.getSprite();

    public Herbivore(Coordinates position) {
        super(position, SPRITE);

    }
}
