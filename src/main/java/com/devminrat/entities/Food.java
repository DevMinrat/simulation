package com.devminrat.entities;

import com.devminrat.Coordinates;

public class Food extends Entity {
    private static final String SPRITE = EntitySprite.FOOD.getSprite();

    public Food(Coordinates position) {
        super(position, SPRITE);

    }
}
