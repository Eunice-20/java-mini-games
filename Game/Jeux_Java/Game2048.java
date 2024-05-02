package Jeux_Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game2048 extends JFrame implements KeyListener {
    private final int GRID_SIZE = 5;
    private final int TILE_SIZE = 100;
    private final Color BACKGROUND_COLOR = new Color(0xBBADA0);
    private final Color EMPTY_TILE_COLOR = new Color(0xCDC1B4);
    private final Color[] TILE_COLORS = {
        new Color(0xF2B179), new Color(0xF59563), new Color(0xF67C5F),
        new Color(0xF65E3B), new Color(0xEDCF72), new Color(0xEDCC61),
        new Color(0xEDC850), new Color(0xEDC53F), new Color(0xF3B27A),
        new Color(0xF3965F), new Color(0xF57D4B), new Color(0xF7D157),
        new Color(0xF7BC5E), new Color(0xF7A04D), new Color(0xF7883E)
    };

    private int[][] grid;
    private JLabel[][] tiles;
    private int score;
    private JLabel scoreLabel;

    public Game2048() {
        setTitle("2048");
        setSize(GRID_SIZE * TILE_SIZE, GRID_SIZE * TILE_SIZE);
        JFrame Game2048Frame = new JFrame();
        Game2048Frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Game2048Frame.setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        addKeyListener(this);
        ImageIcon img = new ImageIcon("./Game/asset/Nub.png");
        Image icon = img.getImage();
        setIconImage(icon);


        JPanel gamePanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gamePanel.setBackground(BACKGROUND_COLOR);
        add(gamePanel, BorderLayout.CENTER);

        grid = new int[GRID_SIZE][GRID_SIZE];
        tiles = new JLabel[GRID_SIZE][GRID_SIZE];
        score = 0;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JLabel tile = new JLabel("", SwingConstants.CENTER);
                tile.setOpaque(true);
                tile.setBackground(EMPTY_TILE_COLOR);
                tile.setFont(new Font("Arial", Font.BOLD, 20));
                gamePanel.add(tile);
                tiles[i][j] = tile;
            }
        }

        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(scoreLabel, BorderLayout.NORTH);

        generateNewTile();
        generateNewTile();

        setVisible(true);
        
    }

    private void generateNewTile() {
        ArrayList<Point> emptyTiles = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 0) {
                    emptyTiles.add(new Point(i, j));
                }
            }
        }
        if (!emptyTiles.isEmpty()) {
            Random rand = new Random();
            Point position = emptyTiles.get(rand.nextInt(emptyTiles.size()));
            int value = (rand.nextInt(2) + 1) * 2; // Either 2 or 4
            grid[position.x][position.y] = value;
            updateTile(position.x, position.y);
        }
    }

    private void updateTile(int row, int col) {
        int value = grid[row][col];
        tiles[row][col].setText(value == 0 ? "" : String.valueOf(value));
        tiles[row][col].setBackground(value == 0 ? EMPTY_TILE_COLOR : TILE_COLORS[(int) (Math.log(value) / Math.log(2))]);
    }

    private void updateScore(int value) {
        score += value;
        scoreLabel.setText("Score: " + score);
    }

    private boolean canMove() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 0) {
                    return true;
                }
                if (i > 0 && grid[i][j] == grid[i - 1][j]) {
                    return true;
                }
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveLeft() {
        for (int i = 0; i < GRID_SIZE; i++) {
            int[] newRow = new int[GRID_SIZE];
            int index = 0;
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] != 0) {
                    if (index > 0 && newRow[index - 1] == grid[i][j]) {
                        newRow[index - 1] *= 2;
                        updateScore(newRow[index - 1]);
                    } else {
                        newRow[index++] = grid[i][j];
                    }
                }
            }
            System.arraycopy(newRow, 0, grid[i], 0, GRID_SIZE);
        }
    }

    private void moveRight() {
        for (int i = 0; i < GRID_SIZE; i++) {
            int[] newRow = new int[GRID_SIZE];
            int index = GRID_SIZE - 1;
            for (int j = GRID_SIZE - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    if (index < GRID_SIZE - 1 && newRow[index + 1] == grid[i][j]) {
                        newRow[index + 1] *= 2;
                        updateScore(newRow[index + 1]);
                    } else {
                        newRow[index--] = grid[i][j];
                    }
                }
            }
            for (int j = GRID_SIZE - 1; j >= 0; j--) {
                grid[i][j] = newRow[j];
            }
        }
    }

    private void moveUp() {
        for (int j = 0; j < GRID_SIZE; j++) {
            int[] newCol = new int[GRID_SIZE];
            int index = 0;
            for (int i = 0; i < GRID_SIZE; i++) {
                if (grid[i][j] != 0) {
                    if (index > 0 && newCol[index - 1] == grid[i][j]) {
                        newCol[index - 1] *= 2;
                        updateScore(newCol[index - 1]);
                    } else {
                        newCol[index++] = grid[i][j];
                    }
                }
            }
            for (int i = 0; i < GRID_SIZE; i++) {
                grid[i][j] = newCol[i];
            }
        }
    }

    private void moveDown() {
        for (int j = 0; j < GRID_SIZE; j++) {
            int[] newCol = new int[GRID_SIZE];
            int index = GRID_SIZE - 1;
            for (int i = GRID_SIZE - 1; i >= 0; i--) {
                if (grid[i][j] != 0) {
                    if (index < GRID_SIZE - 1 && newCol[index + 1] == grid[i][j]) {
                        newCol[index + 1] *= 2;
                        updateScore(newCol[index + 1]);
                    } else {
                        newCol[index--] = grid[i][j];
                    }
                }
            }
            // System.arraycopy(newCol, 0, grid[:, j], 0, GRID_SIZE);
            System.arraycopy(newCol, 0, grid[j], 0, GRID_SIZE);

        }
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over! Score: " + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
    }

    private void checkGameOver() {
        if (!canMove()) {
            gameOver();
            System.exit(0);
        }
    }

    private void updateGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                updateTile(i, j);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
        }
        generateNewTile();
        updateGrid();
        checkGameOver();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
