package com.devminrat.entities;

import com.devminrat.Coordinates;

public class Predator extends Creature {

    private final static int DAMAGE = 5;
    private static final String SPRITE = EntitySprite.PREDATOR.getSprite();

    public Predator(Coordinates position) {
        super(position, SPRITE);
    }

    public void attack(Coordinates coordinates, Herbivore herbivore) {
        //do attack
    }
}
