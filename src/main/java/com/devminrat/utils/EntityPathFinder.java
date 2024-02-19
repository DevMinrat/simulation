package com.devminrat.utils;

import com.devminrat.Coordinates;
import com.devminrat.entities.*;

import java.util.*;

import static com.devminrat.Field.*;


public final class EntityPathFinder {
    public static LinkedList<Coordinates> findPathToTarget(Creature creature, HashMap<Coordinates, Entity> entities) {
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

                tryAddNode(row - 1, col, path, checkedNodes, nextStep, entities);
                tryAddNode(row + 1, col, path, checkedNodes, nextStep, entities);
                tryAddNode(row, col - 1, path, checkedNodes, nextStep, entities);
                tryAddNode(row, col + 1, path, checkedNodes, nextStep, entities);
            }

            step = nextStep;
        }

        return null;
    }

    private static void tryAddNode(int row, int column, LinkedList<Coordinates> path, Set<Coordinates> checkedNodes,
                                   HashMap<Coordinates, LinkedList<Coordinates>> nextStep, HashMap<Coordinates, Entity> entities) {
        if (isValidNeighbour(row, column, entities, checkedNodes)) {
            Coordinates node = new Coordinates(row, column);
            LinkedList<Coordinates> newPath = new LinkedList<>(path);

            newPath.offer(node);
            nextStep.put(node, newPath);
            checkedNodes.add(node);
        }
    }

    private static boolean isValidNeighbour(int row, int col, HashMap<Coordinates, Entity> entities, Set<Coordinates> checkedNodes) {
        if (row < 0 || row >= COLS || col < 0 || col >= ROWS) {
            return false;
        }

        Coordinates node = new Coordinates(row, col);
        if (checkedNodes.contains(node)) {
            return false;
        }

        return !(entities.get(node) instanceof Obstacle);
    }
}
