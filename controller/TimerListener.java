package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameBoard;

public class TimerListener implements ActionListener {

    private GameBoard gameBoard;

    public TimerListener(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    // Game loop
    @Override
    public void actionPerformed(ActionEvent e) {
        for (var f: gameBoard.getCanvas().getFigures()) {
            f.move();
        }
        detectCollision();
        gameBoard.getCanvas().repaint();
    }

    private void detectCollision() {

    }
}
