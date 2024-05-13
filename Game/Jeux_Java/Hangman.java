package Jeux_Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.sql.*;

public class Hangman extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/database_db";
    static final String USER = "eunice";
    static final String PASS = "eunice";
    private static Connection conn;


    private String[] mots;
    private String motSecret;
    private ArrayList<Character> lettresIncorrectes = new ArrayList<>();
    private StringBuilder motCache;
    private int tentativesMax = 6;
    private int tentativesRestantes;
    private int score;
    private JLabel motCacheLabel, lettresIncorrectesLabel, HangmanLabel, scoreLabel, statutLabel;
    private JTextField lettreField;
    private JButton guessButton, restartButton;
    private static JLabel hangmanImageLabel;

    public static void main(String[] args) {
        new Hangman();
    }

    public Hangman() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        
        setTitle("Jeu du Hangman");
        setSize(400, 400);
        setLocationRelativeTo(null);

        initWords();
        initGame();

        JPanel panel = new JPanel(new GridLayout(6, 1));

        motCacheLabel = new JLabel(motCache.toString(), SwingConstants.CENTER);
        panel.add(motCacheLabel);

        lettresIncorrectesLabel = new JLabel("Lettres incorrectes: ", SwingConstants.CENTER);
        panel.add(lettresIncorrectesLabel);

        HangmanLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(HangmanLabel);

        lettreField = new JTextField(1);
        panel.add(lettreField);

        guessButton = new JButton("Devinez !");
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                devinerLettre();
            }
        });
        panel.add(guessButton);

        restartButton = new JButton("Rejouer");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        restartButton.setVisible(true);
        panel.add(restartButton);

        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        panel.add(scoreLabel);

        statutLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(statutLabel);

        hangmanImageLabel = new JLabel();
        panel.add(hangmanImageLabel);
        add(panel);
        setVisible(true);
        
        // Load the initial hangman image
        drawHangman(tentativesRestantes);
    }

    private void restartGame() {
        initGame();
        motCacheLabel.setText(motCache.toString());
        lettresIncorrectes.clear();
        HangmanLabel.setText("");
        lettresIncorrectesLabel.setText("");
        statutLabel.setText("");
        guessButton.setEnabled(true);
        restartButton.setVisible(true);
    }

    private void initGame() {
        Random random = new Random();
        motSecret = mots[random.nextInt(mots.length)];
        motCache = new StringBuilder();
        for (int i = 0; i < motSecret.length(); i++) {
            motCache.append("-");
        }
        tentativesRestantes = tentativesMax;
        score = 0;
    }

    private void initWords() {
        try {
            mots = Files.readAllLines(Paths.get("./Game/Jeux_Java/file.txt/fileHangman.txt")).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la lecture du fichier de mots.");
            System.exit(1);
        }
    }

    private void devinerLettre() {
        String input = lettreField.getText();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une seule lettre valide.");
            return;
        }
        char lettre = input.toUpperCase().charAt(0);
        lettreField.setText("");

        boolean lettreTrouvee = false;

        String motSecretUpperCase = motSecret.toUpperCase();

        if (motCache.indexOf("-") == -1) {
            statutLabel.setText("Vous avez gagné !");
            score += tentativesRestantes * 10;
            scoreLabel.setText("Score: " + score);
            guessButton.setEnabled(false);
            restartButton.setVisible(true);
        }

        for (int i = 0; i < motSecret.length(); i++) {
            if (motSecretUpperCase.charAt(i) == lettre) {
                motCache.setCharAt(i, motSecret.charAt(i));
                lettreTrouvee = true;
            }
        }

        if (!lettreTrouvee) {
            lettresIncorrectes.add(lettre);
            HangmanLabel.setText("Nombre de tentatives restantes: " + --tentativesRestantes);
            lettresIncorrectesLabel.setText("Lettres incorrectes: " + lettresIncorrectes.toString());
            drawHangman(tentativesRestantes);
            if (tentativesRestantes == 0) {
                statutLabel.setText("Vous avez perdu. Le mot était: " + motSecret);
                guessButton.setEnabled(false);
            }
        } else {
            if (motCache.indexOf("-") == -1) {
                statutLabel.setText("Vous avez gagné !");
                score += tentativesRestantes * 10;
                scoreLabel.setText("Score: " + score);
                guessButton.setEnabled(false);
                insererColonne(score);
            }
        }
        if (tentativesRestantes == 0) {
            statutLabel.setText("Vous avez perdu. Le mot était: " + motSecret);
            guessButton.setEnabled(false);
            restartButton.setVisible(true); // Rendre le bouton "Rejouer" visible
        }

        motCacheLabel.setText(motCache.toString());
    }

    public static void drawHangman(int l) {
        ImageIcon img = null;
        switch (l) {
            case 0:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman1.png"));
                break;
            case 1:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman2.png"));
                break;
            case 2:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman3.png"));
                break;
            case 3:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman4.png"));
                break;
            case 4:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman5.png"));
                break;
            case 5:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman6.png"));
                break;
            case 6:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman7.png"));
                break;
            case 7:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman8.png"));
                break;
            case 8:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman9.png"));
                break;
            case 9:
                img = new ImageIcon(Hangman.class.getResource("./Game/Jeux_Java/Ressources/hangman10.png"));
                break;
            default:
                break;
        }
        if (img != null) {
            hangmanImageLabel.setIcon(img);
        }
    }
    //            playSound("./Game/Jeux_Java/Ressources/Music/carte.wav");


    public static void insererColonne(int score) {
        try {
            String sql = "INSERT INTO hangmanbase (score) VALUES (?)";
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

}
