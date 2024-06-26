package Jeux_Java;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.io.IOException; 
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.text.AbstractDocument;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sudoku extends JFrame {

    private JPanel sudokuPanel;
    private JTextField[][] sudokuCells;
    private JButton checkButton;
    private JLabel statusLabel;
    private int[][] solution;

    public static void main(String[] args) {
        new Sudoku();
    }

    public Sudoku() {
        setTitle("Sudoku");
        setSize(400, 400);
        ImageIcon img = new ImageIcon("./asset/Sudoku.png");
        Image icon = img.getImage();
        setIconImage(icon);
        setLocationRelativeTo(null);

        sudokuPanel = new JPanel(new GridLayout(9, 9));
        sudokuCells = new JTextField[9][9];
        solution = generateSolution(); 

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuCells[i][j] = new JTextField();
                sudokuCells[i][j].setHorizontalAlignment(JTextField.CENTER);

                ((AbstractDocument) sudokuCells[i][j].getDocument()).setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                        if (newText.matches("[1-9]") && newText.length() <= 1) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });

                sudokuCells[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        playSound("./Game/Jeux_Java/Ressources/Music/move.wav");

                        int row = -1;
                        int col = -1;
                        for (int k = 0; k < 9; k++) {

                            for (int l = 0; l < 9; l++) {
                                if (sudokuCells[k][l] == e.getSource()) {
                                    row = k;

                                    col = l;
                                    break;
                                }
                            }
                        }
                        if (row != -1 && col != -1) {
                            switch (e.getKeyCode()) {
                                case KeyEvent.VK_UP:
                                    row = (row == 0) ? 8 : row - 1;
                                    break;
                                case KeyEvent.VK_DOWN:
                                    row = (row == 8) ? 0 : row + 1;
                                    break;
                                case KeyEvent.VK_LEFT:
                                    col = (col == 0) ? 8 : col - 1;
                                    break;
                                case KeyEvent.VK_RIGHT:
                                    col = (col == 8) ? 0 : col + 1;
                                    break;
                            }
                            sudokuCells[row][col].requestFocus();
                        }
                    }
                });

                sudokuPanel.add(sudokuCells[i][j]);
            }
        }

        checkButton = new JButton("Check");
        statusLabel = new JLabel("");

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Arrays.deepEquals(getCurrentConfiguration(), solution)) {
                    statusLabel.setText("Sudoku is solved correctly!");
                } else {
                    statusLabel.setText("Invalid Sudoku!");
                }
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(checkButton);
        bottomPanel.add(statusLabel);

        add(sudokuPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void playSound(String soundFilePath) {
        File soundFile = new File(soundFilePath);
        try {
            javax.sound.sampled.AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private int[][] generateSolution() {
        int[][] solution = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        return solution;
    }

    private boolean isValidSet() {
        int[][] currentConfiguration = getCurrentConfiguration();

        for (int i = 0; i < 9; i++) {
            if (!isValidSetSet(currentConfiguration[i]) || !isValidSetSet(getColumn(currentConfiguration, i)) ||
                    !isValidSetSet(getSquare(currentConfiguration, i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidSetSet(int[] set) {
        boolean[] seen = new boolean[10];
        for (int num : set) {
            if (num != 0 && seen[num]) {
                return false;
            }
            seen[num] = true;
        }
        return true;
    }

    private int[] getColumn(int[][] grid, int col) {
        int[] column = new int[9];
        for (int i = 0; i < 9; i++) {
            column[i] = grid[i][col];
        }
        return column;
    }

    private int[] getSquare(int[][] grid, int square) {
        int[] squareArray = new int[9];
        int rowOffset = (square / 3) * 3;
        int colOffset = (square % 3) * 3;
        int index = 0;
        for (int i = rowOffset; i < rowOffset + 3; i++) {
            for (int j = colOffset; j < colOffset + 3; j++) {
                squareArray[index++] = grid[i][j];
            }
        }
        return squareArray;
    }

    private int[][] getCurrentConfiguration() {
        int[][] currentConfiguration = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = sudokuCells[i][j].getText();
                if (!text.isEmpty()) {
                    currentConfiguration[i][j] = Integer.parseInt(text);
                } else {
                    currentConfiguration[i][j] = 0;
                }
            }
        }

        return currentConfiguration;
    }
}
