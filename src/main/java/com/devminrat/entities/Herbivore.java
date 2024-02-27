package com.devminrat.entities;

import com.devminrat.Coordinates;

import java.util.LinkedHashMap;

public class Herbivore extends Creature {
    private static final String SPRITE = EntitySprite.HERBIVORE.getSprite();

    public Herbivore(Coordinates position) {
        super(position, SPRITE);
    }

    @Override
    void eat(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        go(entities, coordinates);
        setHealth(this.getHealth() + 5);
    }

}
