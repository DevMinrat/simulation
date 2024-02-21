package com.devminrat.utils;

import com.devminrat.Coordinates;
import com.devminrat.entities.*;

import java.util.*;

import static com.devminrat.Field.*;


public final class EntityPathFinder {
    public static LinkedList<Coordinates> findPathToTarget(Creature creature, LinkedHashMap<Coordinates, Entity> entities) {
        Class<? extends Entity> target = creature.getClass().equals(Herbivore.class) ? Food.class : Herbivore.class;
        Coordinates currentNode = creature.getPosition();
        Set<Coordinates> checkedNodes = new HashSet<>();

        HashMap<Coordinates, LinkedList<Coordinates>> step = new HashMap<>(Map.of(currentNode, new LinkedList<>(List.of(currentNode))));

        while (!step.isEmpty()) {
            HashMap<Coordinates, LinkedList<Coordinates>> nextStep = new HashMap<>();

            for (Map.Entry<Coordinates, LinkedList<Coordinates>> entry : step.entrySet()) {
                Coordinates coordinates = entry.getKey();
                LinkedList<Coordinates> path = entry.getValue();
                int row = coordinates.X;
                int col = coordinates.Y;

                if (entities.get(coordinates) != null && entities.get(coordinates).getClass().equals(target)) {
                    path.poll();
                    return path;
                }

                tryAddNode(row - 1, col, path, checkedNodes, nextStep, entities, creature);
                tryAddNode(row + 1, col, path, checkedNodes, nextStep, entities, creature);
                tryAddNode(row, col - 1, path, checkedNodes, nextStep, entities, creature);
                tryAddNode(row, col + 1, path, checkedNodes, nextStep, entities, creature);
            }

            step = nextStep;
        }

        return null;
    }

    private static void tryAddNode(int row, int column, LinkedList<Coordinates> path, Set<Coordinates> checkedNodes,
                                   HashMap<Coordinates, LinkedList<Coordinates>> nextStep, LinkedHashMap<Coordinates, Entity> entities, Creature creature) {
        if (isValidNeighbour(row, column, entities, checkedNodes, creature)) {
            Coordinates node = new Coordinates(row, column);
            LinkedList<Coordinates> newPath = new LinkedList<>(path);

            newPath.offer(node);
            nextStep.put(node, newPath);
            checkedNodes.add(node);
        }
    }

    private static boolean isValidNeighbour(int row, int col, LinkedHashMap<Coordinates, Entity> entities, Set<Coordinates> checkedNodes, Creature creature) {
        Class<? extends Creature> creatureClass = creature.getClass();
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return false;
        }

        Coordinates node = new Coordinates(row, col);
        if (checkedNodes.contains(node) || entities.get(node) instanceof Predator) {
            return false;
        }

        if (entities.get(node) != null && entities.get(node).getClass().equals(creatureClass)) {
            return false;
        }

        if (entities.get(node) != null && creatureClass.equals(Predator.class) && entities.get(node).getClass().equals(Food.class)) {
            return false;
        }

        return !(entities.get(node) instanceof Obstacle);
    }
}
