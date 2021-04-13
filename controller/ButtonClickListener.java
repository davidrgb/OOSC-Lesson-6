package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.GameBoard;

public class ButtonClickListener implements ActionListener {

    private GameBoard gameBoard;

    public ButtonClickListener (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (e.getSource() == gameBoard.getStartButton()) {
            gameBoard.getCanvas().getFigures().clear();
            gameBoard.getSnake().init();
            gameBoard.getCanvas().getFigures().add(gameBoard.getSnake());
            gameBoard.createFood();
            gameBoard.setScore(0);
            gameBoard.getScoreDisplay().setText("0");
            gameBoard.setGameOver(false);
        }
        else if (button == gameBoard.getStopButton()) {
            String label = button.getText();
            if (label.equals("Stop")) {
                button.setText("Resume");
                gameBoard.getTimer().stop();
            }
            else {
                button.setText("Stop");
                gameBoard.getTimer().start();
            }
        }
        else if (button == gameBoard.getExitButton()) {
            System.exit(0);
        }
    }
    
}
