package com.devminrat.entities;

public enum EntitySprite {
    FOOD("images/food-s.png"),
    OBSTACLE("images/obstacle-s.png"),
    HERBIVORE("images/herbivore-s.png"),
    PREDATOR("images/predator-s.png");

    private final String sprite;

    public String getSprite() {
        return sprite;
    }

    EntitySprite(String sprite) {
        this.sprite = sprite;
    }
}
