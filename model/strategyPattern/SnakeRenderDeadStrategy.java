package model.strategyPattern;

import java.awt.Color;
import java.awt.Graphics2D;

import model.Snake;

public class SnakeRenderDeadStrategy implements SnakeRenderStrategy {

    private Snake snake;

    public SnakeRenderDeadStrategy(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void renderAlgorithm(Graphics2D g2) {
        var composite = snake.getComposite();
        for (var s: composite) {
            s.color = Color.gray;
            s.filled = false;
            s.render(g2);
        }
    }
    
}
