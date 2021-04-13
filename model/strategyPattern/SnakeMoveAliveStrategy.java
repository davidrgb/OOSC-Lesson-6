package model.strategyPattern;

import model.Snake;
import view.GameBoard;

public class SnakeMoveAliveStrategy implements SnakeMoveStrategy {

    private Snake snake;

    public SnakeMoveAliveStrategy(Snake snake) {
        this.snake = snake;
    }
    @Override
    public void moveAlgorithm() {
        var composite = snake.getComposite();
        for (int i = composite.size() - 1; i > 0; i--) {
            composite.get(i).x = composite.get(i - 1).x;
            composite.get(i).y = composite.get(i - 1).y;
        }

        switch(snake.direction) {
            case LEFT:
                composite.get(0).x -= GameBoard.CELL_SIZE;
                snake.x = composite.get(0).x;
                break;
            case RIGHT:
                composite.get(0).x += GameBoard.CELL_SIZE;
                snake.x = composite.get(0).x;
                break;
            case UP:
                composite.get(0).y -= GameBoard.CELL_SIZE;
                snake.y = composite.get(0).y;
                break;
            case DOWN:
                composite.get(0).y += GameBoard.CELL_SIZE;
                snake.y = composite.get(0).y;
                break;
        }
        
    }
    
}
