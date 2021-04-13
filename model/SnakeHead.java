package model;

import java.awt.Graphics2D;

import view.GameBoard;

public class SnakeHead extends GameElement {

    public SnakeHead(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(super.color);
        if (super.filled) {
            g2.fillOval(x, y, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
        }
        else {
            g2.drawOval(x, y, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
        }
    }

    @Override
    public void move() {
        
    }
    
}
