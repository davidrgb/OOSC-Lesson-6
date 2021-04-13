package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Food;
import model.GameElement;
import model.Snake;
import model.SnakeBody;
import model.Snake.Event;
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
        if (!gameBoard.isGameOver())
            detectCollision();

        gameBoard.getCanvas().repaint();
    }

    private void detectCollision() {
        var figures = gameBoard.getCanvas().getFigures();

        Snake snake = null;
        for (var f: figures) {
            if (f instanceof Snake) {
                snake = (Snake) f;
                break;
            }
        }
        if (snake == null) return;

        if (snake.x < 0 || snake.x >= GameBoard.WIDTH || snake.y < 0 || snake.y >= GameBoard.HEIGHT) {
            snake.notifyObservers(Event.LeftScene);
            gameBoard.setGameOver(true);
        }

        if (snake.selfCollision()) {
            snake.notifyObservers(Event.SelfCollision);
            gameBoard.setGameOver(true);
        }

        var removeFoods = new ArrayList<GameElement>();
        for (var f: figures) {
            if (f instanceof Snake) continue;
            if (snake.collideWith(f)) {
                if (f instanceof Food) {
                    removeFoods.add(f);
                    snake.getComposite().add(new SnakeBody(-100, -100));
                    snake.notifyObservers(Event.AteFood);
                }
            }
        }

        if (removeFoods.size() > 0) {
            figures.removeAll(removeFoods);
            gameBoard.createFood();
        }
    }
}
