package com.devminrat;

import com.devminrat.entities.*;

import java.util.HashMap;
import java.util.Random;

public class Field {

    Random random = new Random();

    public static HashMap<Coordinates, Entity> entities = new HashMap<>();

    private Coordinates getRandomCoordinates() {
        Coordinates coordinates;

        do {
            coordinates = new Coordinates(random.nextInt(10), random.nextInt(15));
        } while (entities.containsKey(coordinates));

        return coordinates;
    }


    public void setEntities() {
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

}
