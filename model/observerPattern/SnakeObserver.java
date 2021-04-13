package model.observerPattern;

import model.Snake;
import model.strategyPattern.SnakeMoveDeadStrategy;
import model.strategyPattern.SnakeRenderDeadStrategy;
import view.GameBoard;
import view.Text;

public class SnakeObserver implements Observer {

    private GameBoard gameBoard;

    public SnakeObserver(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void snakeAteFood() {
        int score = gameBoard.getScore();
        ++score;
        gameBoard.setScore(score);
        gameBoard.getScoreDisplay().setText("" + score);
    }

    @Override
    public void snakeAtePoison() {
        
    }

    @Override
    public void snakeLeftScene() {
        gameBoard.getCanvas().getFigures().add(new Text("Game Over - Out of Bound", 100, 200));
        Snake snake = gameBoard.getSnake();
        snake.setMoveStrategy(new SnakeMoveDeadStrategy(snake));
        snake.setRenderStrategy(new SnakeRenderDeadStrategy(snake));
    }

    @Override
    public void snakeSelfCollision() {
        gameBoard.getCanvas().getFigures().add(new Text("Game Over - Self Collision", 100, 200));
        Snake snake = gameBoard.getSnake();
        snake.setMoveStrategy(new SnakeMoveDeadStrategy(snake));
        snake.setRenderStrategy(new SnakeRenderDeadStrategy(snake));
    }
    
}
