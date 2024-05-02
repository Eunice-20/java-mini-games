package Jeux_Java;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.nio.file.*;
import java.io.IOException;


public class test extends JFrame {
    private final String[] words;
    private final Random random;
    private String correctWord;
    private int tries;
    private StringBuilder word;
    private StringBuilder usedLetters;

    private JTextArea wordTextArea;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;

    public test() throws IOException {
        setTitle("Pendu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        words = Files.readAllLines(Paths.get("./Game/Jeux_Java/file.txt/fileHangman.txt")).toArray(new String[0]);
        random = new Random();
        wordTextArea = new JTextArea();
        wordTextArea.setEditable(false);
        wordTextArea.setLineWrap(true);
        add(new JScrollPane(wordTextArea));

        guessField = new JTextField();
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(e -> checkGuess());
        add(guessButton);

        messageLabel = new JLabel("", JLabel.CENTER);
        add(messageLabel);

        startGame();
    }

    private void startGame() {
        correctWord = words[random.nextInt(words.length)];
        tries = 10;
        word = new StringBuilder("-".repeat(correctWord.length()));
        usedLetters = new StringBuilder();

        updateUI();
    }

    private void updateUI() {
        wordTextArea.setText(word.toString());
        guessField.setText("");
        messageLabel.setText("Tries left: " + tries + ", Letters used: " + usedLetters);
    }

    private void checkGuess() {
        String guess = guessField.getText().toUpperCase();
        guessField.setText("");
        if (!Character.isLetter(guess.charAt(0))) {
            JOptionPane.showMessageDialog(this, "Invalid guess. Please enter a single letter.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (usedLetters.indexOf(guess) != -1) {
            JOptionPane.showMessageDialog(this, "You already guessed that letter! Try another one.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        usedLetters.append(guess).append(" ");
        boolean contain = false;
        for (int i = 0; i < correctWord.length(); i++) {
            if (correctWord.charAt(i) == guess.charAt(0)) {
                word.setCharAt(i, guess.charAt(0));
                contain = true;
            }
        }
        if (!contain) {
            tries--;
            if (tries == 0) {
                JOptionPane.showMessageDialog(this, "You ran out of tries. Better luck next time!\nThe correct word was: " + correctWord, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                startGame();
                return;
            }
        }
        if (word.indexOf("-") == -1) {
            JOptionPane.showMessageDialog(this, "Congratulations! You win. The word was: " + correctWord, "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            startGame();
            return;
        }
        updateUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new test().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}


    // Ajoutez la méthode drawHangman si nécessaire
    // (code omis pour des raisons de concision)



//    public static void drawHangman(int l) {
//     si(l == 6) {
//      System.out.println("|----------");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//     }
//     sinon si(l == 5) {
//      System.out.println("|----------");
//      System.out.println("| O");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//     }
//     sinon si(l == 4) {
//      System.out.println("|----------");
//      System.out.println("| O"); 
//      System.out.println("| |");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//     }
//     sinon si(l == 3) {
//      System.out.println("|----------");
//      System.out.println("| O");
//      System.out.println("| -|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//     }
//     sinon si(l == 2) {
//      System.out.println("|----------");
//      System.out.println("| O");
//      System.out.println("| -|-");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//     }
//     sinon si(l == 1) {
//      System.out.println("|----------");
//      System.out.println("| O");
//      System.out.println("| -|-");
//      System.out.println("| /");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//     }
//     autre{
//      System.out.println("|----------");
//      System.out.println("| O");
//      System.out.println("| -|-"); 
//      System.out.println("| /|");
//      System.out.println("|");
//      System.out.println("|");
//      System.out.println("|");
//     }
//    }