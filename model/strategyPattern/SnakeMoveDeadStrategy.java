package model.strategyPattern;

import model.Snake;
import view.GameBoard;

public class SnakeMoveDeadStrategy implements SnakeMoveStrategy {

    private Snake snake;

    public SnakeMoveDeadStrategy(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void moveAlgorithm() {
        var composite = snake.getComposite();
        for (var s: composite) {
            s.y += GameBoard.CELL_SIZE;
        }
        snake.y = composite.get(0).y;
        if (snake.y >= GameBoard.HEIGHT) {
            for (var s: composite) {
                s.y = 0;
            }
        }
        snake.y = 0;
    }
    
}
