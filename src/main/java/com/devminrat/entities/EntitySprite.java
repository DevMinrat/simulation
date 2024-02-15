package com.devminrat.entities;

public enum EntitySprite {
    FOOD("\uD83C\uDF4E"),
    OBSTACLE("\uD83E\uDDF1"),
    HERBIVORE("\uD83D\uDC1B"),
    PREDATOR("\uD83D\uDC14");

    private final String sprite;

    public String getSprite() {
        return sprite;
    }

    EntitySprite(String sprite) {
        this.sprite = sprite;
    }
}
