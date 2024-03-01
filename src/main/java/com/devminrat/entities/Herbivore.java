package com.devminrat.entities;

import com.devminrat.Coordinates;

import java.util.LinkedHashMap;

public class Herbivore extends Creature {
    private static final String SPRITE = EntitySprite.HERBIVORE.getSprite();

    public Herbivore(Coordinates position) {
        super(position, SPRITE);
    }

    @Override
    public LinkedHashMap<Coordinates, Entity> makeMove(LinkedHashMap<Coordinates, Entity> entities) {
        Coordinates targetCoord = checkTargetAround(entities, this);

        if (targetCoord != null) {
            eat(entities, targetCoord);
        } else {
            var path = getPathToTarget(entities);

            if (path != null)
                go(entities, path.peek());
        }

        checkStarvation(entities);

        return entities;
    }

    @Override
    void eat(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        go(entities, coordinates);
        setHealth(this.getHealth() + NUTRITIONAL_VALUE);
    }

}
