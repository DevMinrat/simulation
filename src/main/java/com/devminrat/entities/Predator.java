package com.devminrat.entities;

import com.devminrat.Coordinates;
import com.devminrat.gui.ButtonManager;

import java.util.LinkedHashMap;

public class Predator extends Creature {
    public final int damage = random.nextInt(1, 6);
    private static final String SPRITE = EntitySprite.PREDATOR.getSprite();

    public Predator(Coordinates position) {
        super(position, SPRITE);
    }

    public void attack(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        Creature target = (Creature) entities.get(coordinates);
        target.takeDamage(damage);
        takeDamage(1);

        ButtonManager.setTextIntoInfoField(this.getClass().getSimpleName() + " attacked " + coordinates );
    }

    @Override
    public LinkedHashMap<Coordinates, Entity> makeMove(LinkedHashMap<Coordinates, Entity> entities) {
        Coordinates targetCoord = checkTargetAround(entities, this);
        Creature target = (Creature) entities.get(targetCoord);

        if (target != null) {
            if (target.getHealth() - damage <= 0) {
                eat(entities, targetCoord);
            } else {
                attack(entities, targetCoord);
            }

        } else {
            var path = getPathToTarget(entities);

            if (path != null) {
                go(entities, path.peek());
            }
        }

        checkStarvation(entities);

        return entities;
    }

    @Override
    void eat(LinkedHashMap<Coordinates, Entity> entities, Coordinates coordinates) {
        Removable eatedEntity = (Removable) entities.get(coordinates);

        eatedEntity.setForDeletion(true);
        go(entities, coordinates);
        setHealth(this.getHealth() + NUTRITIONAL_VALUE);

        ButtonManager.setTextIntoInfoField(this.getClass().getSimpleName() + " ate " + coordinates );
    }

}
