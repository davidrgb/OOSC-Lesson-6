package model;

import java.awt.Color;
import java.awt.Graphics2D;

import view.GameBoard;

public class Food extends GameElement {

    public Food(int x, int y, Color color) {
        super(x, y);
        super.color = color;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(x, y, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
    }

    @Override
    public void move() {
        
    }
    
}
