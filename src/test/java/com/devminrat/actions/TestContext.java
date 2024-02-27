package com.devminrat.actions;

import com.devminrat.Coordinates;
import com.devminrat.Field;
import com.devminrat.FieldConsoleRender;
import com.devminrat.entities.Obstacle;

public class TestContext {
    public final Spec spec;
    public final Field testField;
    public final FieldConsoleRender consoleRender;

    public TestContext(Spec spec) {
        this.spec = spec;
        this.consoleRender = new FieldConsoleRender();
        this.testField = new Field();
    }


    public Field initTestField() {
        var entities = testField.getEntities();

        entities.put(new Coordinates(2, 3), new Obstacle(new Coordinates(2, 3)));
        entities.put(new Coordinates(2, 4), new Obstacle(new Coordinates(2, 4)));
        entities.put(new Coordinates(2, 5), new Obstacle(new Coordinates(2, 5)));
        entities.put(new Coordinates(2, 6), new Obstacle(new Coordinates(2, 6)));
        entities.put(new Coordinates(3, 6), new Obstacle(new Coordinates(3, 6)));
        entities.put(new Coordinates(4, 6), new Obstacle(new Coordinates(4, 6)));

        testField.addHerbivores(spec.herbivoresCount);
        testField.addFood(spec.foodCount);
        testField.addPredators(spec.predatorsCount);

        return testField;
    }

    public static class Spec {
        private Integer herbivoresCount;
        private Integer foodCount;
        private Integer predatorsCount;

        public Spec herbivoresCount(int herbivoresCount) {
            this.herbivoresCount = herbivoresCount;
            return this;
        }

        public Spec foodCount(int foodCount) {
            this.foodCount = foodCount;
            return this;
        }

        public Spec predatorsCount(int predatorsCount) {
            this.predatorsCount = predatorsCount;
            return this;
        }

        public TestContext build() {
            this.foodCount(foodCount)
                    .herbivoresCount(herbivoresCount)
                    .predatorsCount(predatorsCount);

            return new TestContext(this);
        }
    }


}
