package com.devminrat.entities;

import com.devminrat.Coordinates;

import java.util.LinkedHashMap;

public class Predator extends Creature {
    private final int damage = random.nextInt(1, 6);
    private static final String SPRITE = EntitySprite.PREDATOR.getSprite();

    public Predator(Coordinates position) {
        super(position, SPRITE);
    }

    public void attack(Coordinates coordinates, Herbivore herbivore) {
        //do attack
    }

    @Override
    void eat(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        Removable eatedEntity = (Removable) entities.get(coordinates);

        eatedEntity.setForDeletion(true);
        go(entities, coordinates);
        setHealth(this.getHealth() + NUTRITIONAL_VALUE);
    }

}
