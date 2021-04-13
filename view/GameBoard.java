package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.ButtonClickListener;
import controller.KeyController;
import controller.TimerListener;
import model.Food;
import model.Snake;
import model.observerPattern.SnakeObserver;

public class GameBoard {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int CELL_SIZE = 20;
    public static final int FPS = 4;
    public static final int DELAY = 1000 / FPS;
    
    private JFrame window;
    private MyCanvas canvas;
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton exitButton = new JButton("Exit");
    private JLabel scoreDisplay = new JLabel();
    private int score = 0;
    private Timer timer;
    private boolean gameOver;

    private Snake snake = new Snake();

    public GameBoard(JFrame window) {
        this.window = window;
    }

    public void init() {
        Container cp = window.getContentPane();

        canvas = new MyCanvas(this, WIDTH, HEIGHT);
        cp.add(BorderLayout.CENTER, canvas);

        JPanel northPanel = new JPanel();
        JLabel label = new JLabel("Score: ");
        northPanel.add(label);
        scoreDisplay.setText("" + score);
        northPanel.add(scoreDisplay);
        cp.add(BorderLayout.NORTH, northPanel);

        JPanel southPanel = new JPanel();
        southPanel.add(startButton);
        southPanel.add(stopButton);
        southPanel.add(exitButton);
        cp.add(BorderLayout.SOUTH, southPanel);

        Text t1 = new Text("Click <Start> to Play", 100, 100);
        t1.color = Color.yellow;
        canvas.getFigures().add(t1);

        ButtonClickListener buttonListener = new ButtonClickListener(this);
        startButton.addActionListener(buttonListener);
        stopButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);
        
        KeyController keyController = new KeyController(this);
        canvas.addKeyListener(keyController);
        canvas.requestFocusInWindow();
        canvas.setFocusable(true);

        // disable focusable in all other components
        startButton.setFocusable(false);
        stopButton.setFocusable(false);
        exitButton.setFocusable(false);
        label.setFocusable(false);
        scoreDisplay.setFocusable(false);

        SnakeObserver observer = new SnakeObserver(this);
        snake.addSnakeListener(observer);

        timer = new Timer(DELAY, new TimerListener(this));
        timer.start();
    }

    public void createFood() {
        Random random = new Random();
        int xloc, yloc;
        do {
            xloc = random.nextInt(GameBoard.WIDTH / GameBoard.CELL_SIZE) * GameBoard.CELL_SIZE;
            yloc = random.nextInt(GameBoard.HEIGHT / GameBoard.CELL_SIZE) * GameBoard.CELL_SIZE;
        } while (xloc == snake.x && yloc == snake.y);

        Food food = new Food(xloc, yloc, Color.pink);
        canvas.getFigures().add(food);
    }

    public MyCanvas getCanvas() {
        return canvas;
    }

    public Snake getSnake() {
        return snake;
    }

    public JButton getStartButton() {
        return startButton;
    }
    
    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JLabel getScoreDisplay() {
        return scoreDisplay;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Timer getTimer() {
        return timer;
    }
}
