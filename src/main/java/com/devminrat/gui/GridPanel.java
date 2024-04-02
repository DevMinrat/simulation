package com.devminrat.gui;

import com.devminrat.Coordinates;
import com.devminrat.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

public class GridPanel extends JPanel {
    private final int rows;
    private final int cols;
    private final int cellSize;
    private LinkedHashMap<Coordinates, Entity> entities;
    private BufferedImage bordImage;

    public GridPanel(int rows, int cols, int cellSize, LinkedHashMap<Coordinates, Entity> entities) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.entities = entities;
        this.setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bordImage, 0, 0, null);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Coordinates c = new Coordinates(row, col);
                if (entities.containsKey(c)) {
                    Entity e = entities.get(c);
                    ImageIcon image = new ImageIcon(e.getSprite());
                    g.drawImage(image.getImage(), col * cellSize + 10, row * cellSize + 10, null);
                }
            }
        }
    }

    public void createBoardImage() {
        bordImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bordImage.createGraphics();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ((row + col) % 2 == 0) {
                    g2d.setColor(new Color(94, 134, 1));
                } else {
                    g2d.setColor(new Color(0, 94, 95));
                }
                g2d.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
        g2d.dispose();
    }

    public void updateEntities(LinkedHashMap<Coordinates, Entity> newEntities) {
        this.entities = newEntities;
        this.repaint();
    }
}
