package com.devminrat;

import com.devminrat.entities.*;

import java.util.HashMap;
import java.util.Random;

import static com.devminrat.utils.EntityPathFinder.findPathToTarget;

public class Field {

    Random random = new Random();

    public static final HashMap<Coordinates, Entity> entities = new HashMap<>();

    private Coordinates getRandomCoordinates() {
        Coordinates coordinates;

        do {
            coordinates = new Coordinates(random.nextInt(10), random.nextInt(15));
        } while (entities.containsKey(coordinates));

        return coordinates;
    }


    public void setEntities() {

        entities.put(new Coordinates(2,3), new Obstacle(new Coordinates(2,3)));
        entities.put(new Coordinates(2,4), new Obstacle(new Coordinates(2,4)));
        entities.put(new Coordinates(2,5), new Obstacle(new Coordinates(2,5)));
        entities.put(new Coordinates(2,6), new Obstacle(new Coordinates(2,6)));
        entities.put(new Coordinates(3,6), new Obstacle(new Coordinates(3,6)));
        entities.put(new Coordinates(4,6), new Obstacle(new Coordinates(4,6)));

        entities.put(new Coordinates(3,2), new Herbivore(new Coordinates(3,2)));

        entities.put(new Coordinates(2,7), new Food(new Coordinates(2,7)));

        Herbivore herbivore = (Herbivore) entities.get(new Coordinates(3,2));
        FieldConsoleRender cr = new FieldConsoleRender();

        herbivore.makeMove();
        cr.colorizeField();
        herbivore.makeMove();
        cr.colorizeField();
        herbivore.makeMove();
        cr.colorizeField();
        herbivore.makeMove();
        cr.colorizeField();

//        for (int i = 0; i < 25; i++) {
//            Coordinates obstacleCoordinates = getRandomCoordinates();
//            entities.put(obstacleCoordinates, new Obstacle(obstacleCoordinates));
//        }
//
//        for (int i = 0; i < 5; i++) {
//            Coordinates foodCoordinates = getRandomCoordinates();
//            entities.put(foodCoordinates, new Food(foodCoordinates));
//
//            Coordinates herbivoreCoordinates = getRandomCoordinates();
//            entities.put(herbivoreCoordinates, new Herbivore(herbivoreCoordinates));
//
//            Coordinates predatorCoordinates = getRandomCoordinates();
//            entities.put(predatorCoordinates, new Predator(predatorCoordinates));
//        }

    }

}
