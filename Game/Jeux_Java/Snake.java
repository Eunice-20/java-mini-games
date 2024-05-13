package Jeux_Java;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.sql.*;

public class Snake extends JPanel implements KeyListener {

    static final String DB_URL = "jdbc:mysql://localhost:3306/database_db";
    static final String USER = "eunice";
    static final String PASS = "eunice";
    private static Connection conn;

    private static final int unit_size = 20;  
    private static final int  board_size = 20; 
    private static final int time = 200;
    
    private ArrayList<Point> snake;
    private Point food;
    private char direction;
    private boolean running;
    private int score;

    public static void main(String[] args) {
        int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous jouer au jeu Snake ?", " ", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Snake Game");
                JFrame SnakeFrame = new JFrame();
                SnakeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                SnakeFrame.setVisible(false);
                ImageIcon img = new ImageIcon("./asset/Snake_Game.png");
                Image icon = img.getImage();
                frame.setIconImage(icon);
                frame.getContentPane().add(new Snake());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        frame.dispose(); 
                    }
                });
            });
        } else {
            JFrame snakeFrame = new JFrame();
            snakeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            snakeFrame.setVisible(false);
        }
    }
    
    
    public Snake() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


       
        setPreferredSize(new Dimension( board_size * unit_size,  board_size * unit_size));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        startGame();

        
    }

    private void startGame() {
        snake = new ArrayList<>();
        snake.add(new Point( board_size / 2,  board_size / 2));
        Food();
        direction = 'R';
        running = true;
        score = 0;
        repaint();
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while (running) {
            move();
            checkCollision();
            checkapple();
            repaint();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead;
        switch (direction) {
            case 'U': // haut
                newHead = new Point(head.x, head.y - 1);
                break;
            case 'D':// bas 
                newHead = new Point(head.x, head.y + 1);
                break;
            case 'L': // gauche
                newHead = new Point(head.x - 1, head.y);
                break;
            case 'R':// droit
                newHead = new Point(head.x + 1, head.y);
                break;
            default:
                return;
        }
        snake.add(0, newHead);
        if (!food.equals(newHead)) {
            snake.remove(snake.size() - 1);
        } else {
            playSound("./Game/Jeux_Java/Ressources/Music/PomSnake.wav");
            Food();
            score++;
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >=  board_size || head.y < 0 || head.y >=  board_size) {
            gameOver();
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver();
                break;
            }
        }
    }

    private void checkapple() {
        if (snake.get(0).equals(food)) {
            Food();
            score++;
        }
    }

    private void Food() {
        Random rand = new Random();
        int x = rand.nextInt( board_size);
        int y = rand.nextInt( board_size);
        food = new Point(x, y);

    }
// ---- la mise a jour ne fonctionne pas correctement a revoir -----

    private void gameOver() {
        running = false;
        int choice = JOptionPane.showConfirmDialog(this, "Game Over! Score: " + score + "\nVoulez-vous rejouer ?", "Fin de la partie", JOptionPane.YES_NO_OPTION);
        insererColonne(score);
        if (choice == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    startGame();
                    

                }
            });
        } else {
            JFrame SnakeFrame = new JFrame();
            SnakeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
    public static void insererColonne(int score ) {
        try {
            String sql = "INSERT INTO Snakebase (score) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, score);
                int lignesModifiees = pstmt.executeUpdate();
                if (lignesModifiees > 0) {
                    System.out.println("Insertion réussie !");
                } else {
                    System.out.println("Erreur lors de l'insertion.");
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(food.x * unit_size, food.y * unit_size, unit_size, unit_size);
        g.setColor(Color.blue);
        for (Point p : snake) {
            g.fillRect(p.x * unit_size, p.y * unit_size, unit_size, unit_size);
        }
    }
     public void playSound(String soundFilePath) {
        File soundFile = new File(soundFilePath);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (direction != 'D') direction = 'U';
                playSound("./Game/Jeux_Java/Ressources/Music/Snake.wav");
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') direction = 'D';
                playSound("./Game/Jeux_Java/Ressources/Music/Snake.wav");
                break;
            case KeyEvent.VK_LEFT:
                if (direction != 'R') direction = 'L';
                playSound("./Game/Jeux_Java/Ressources/Music/Snake.wav");
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') direction = 'R';
                playSound("./Game/Jeux_Java/Ressources/Music/Snake.wav");
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
