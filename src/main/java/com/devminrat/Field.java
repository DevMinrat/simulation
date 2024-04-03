package com.devminrat;

import com.devminrat.entities.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public final class Field {

    public static final int ROWS = 10;
    public static final int COLS = 15;

    private final Random random = new Random();

    private LinkedHashMap<Coordinates, Entity> entities = new LinkedHashMap<>();

    public LinkedHashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

    public LinkedHashMap<Coordinates, Entity> setEntities(LinkedHashMap<Coordinates, Entity> entities) {
        return this.entities = entities;
    }

    private Coordinates getRandomCoordinates() {
        Coordinates coordinates;

        do {
            coordinates = new Coordinates(random.nextInt(ROWS), random.nextInt(COLS));
        } while (entities.containsKey(coordinates));

        return coordinates;
    }


    public void initEntities() {

        for (int i = 0; i < 25; i++) {
            Coordinates obstacleCoordinates = getRandomCoordinates();
            entities.put(obstacleCoordinates, new Obstacle(obstacleCoordinates));
        }

        for (int i = 0; i < 5; i++) {
            Coordinates foodCoordinates = getRandomCoordinates();
            entities.put(foodCoordinates, new Food(foodCoordinates));

            Coordinates herbivoreCoordinates = getRandomCoordinates();
            entities.put(herbivoreCoordinates, new Herbivore(herbivoreCoordinates));

            Coordinates predatorCoordinates = getRandomCoordinates();
            entities.put(predatorCoordinates, new Predator(predatorCoordinates));
        }

    }

    public void addFood(int count) {
        for (int i = 0; i < count; i++) {
            Coordinates coord = getRandomCoordinates();
            entities.put(coord, new Food(coord));
        }
    }

    public void addHerbivores(int count) {
        for (int i = 0; i < count; i++) {
            Coordinates coord = getRandomCoordinates();
            entities.putFirst(coord, new Herbivore(coord));
        }
    }

    public void addPredators(int count) {
        for (int i = 0; i < count; i++) {
            Coordinates coord = getRandomCoordinates();
            entities.put(coord, new Predator(coord));
        }
    }

}
