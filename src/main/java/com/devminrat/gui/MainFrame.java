package com.devminrat.gui;

import com.devminrat.Field;
import com.devminrat.utils.Actions;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.devminrat.gui.ButtonManager.*;
import static com.devminrat.gui.ButtonManager.setButtonsEnabled;
import static com.devminrat.utils.Actions.nextTurn;

public class MainFrame extends JFrame {

    public JFrame frame;

    public MainFrame(Field field) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GridPanel gridPanel = new GridPanel(Field.ROWS, Field.COLS, 50, field.getEntities());
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            ButtonManager.initButtons();
            Button nextTurnButton = getNextTurnButton();
            Button runLoopButton = getRunLoopButton();
            Button pauseLoopButton = getPauseLoopButton();

            textPanel.add(getInfoField());
            buttonPanel.add(nextTurnButton);
            buttonPanel.add(runLoopButton);
            buttonPanel.add(pauseLoopButton);

            AtomicReference<SwingWorker<Void, Void>> loopWorkerRef = new AtomicReference<>();
            nextTurnButton.addActionListener(e -> {
                setButtonsEnabled(false);
                nextTurn(field, gridPanel);
            });
            runLoopButton.addActionListener(e -> {
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        nextTurn(field, gridPanel);

                        while (!isCancelled()) {
                            setButtonsEnabled(false);
                            if (Actions.turnWorker.get().isDone())
                                nextTurn(field, gridPanel);
                        }

                        return null;
                    }
                };
                loopWorkerRef.set(worker);
                worker.execute();
            });
            pauseLoopButton.addActionListener(e -> {
                SwingWorker<Void, Void> worker = loopWorkerRef.get();
                if (worker != null && !worker.isCancelled()) {
                    pauseLoopButton.setEnabled(false);
                }
                if (worker != null && !worker.isDone()) {
                    worker.cancel(true);
                }
            });

            controlPanel.add(textPanel);
            controlPanel.add(buttonPanel);
            frame.add(gridPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            gridPanel.createBoardImage();
        });
    }
}
