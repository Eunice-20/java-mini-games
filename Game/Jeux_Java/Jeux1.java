package Jeux_Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class Jeux1 {
    static final String DB_URL = "jdbc:mysql://localhost:3306/database_db";
    static final String USER = "eunice";
    static final String PASS = "eunice";
    private static Connection conn;

    public static void main(String[] args) {
        new Jeux1();
    }

    public Jeux1() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        Random rand = new Random();
        int randomNumber = rand.nextInt(1000) + 1;
        System.err.println(randomNumber);

        JFrame frame = new JFrame("Jeux du + ou - ");
        JLabel label = new JLabel("Consigne : vous disposez de 3 tentatives avant la fin du jeu", JLabel.CENTER);
        JTextField textField = new JTextField(18);

        frame.add(label);
        frame.add(textField);

        JPanel panel = new JPanel();

        JButton leave = new JButton("Quitter");
        JButton btnD = new JButton("Devinez");

        panel.add(textField);
        panel.add(leave);
        panel.add(btnD);

        frame.setLayout(new GridLayout(3, 1));
        btnD.setBackground(Color.PINK);

        frame.add(label);
        frame.add(panel);

        frame.pack();

        ImageIcon img = new ImageIcon("./asset/image.png");
        Image icon = img.getImage();

        frame.setIconImage(icon);

        frame.setVisible(true);
        frame.setTitle("Jeux du + ou - ");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        btnD.addActionListener(new ActionListener() {
            int time = 0;
            int score = 0;

            @Override
            public void actionPerformed(ActionEvent event) {
                time++;
                if (time <= 3) {
                    try {
                        int guess = Integer.parseInt(textField.getText());
                        System.out.println("Nombre de l'utilisateur : " + guess);
                        if (guess >= 1 && guess <= 1000) {
                            if (guess == randomNumber) {
                                score += 2;
                                JOptionPane.showMessageDialog(null, "Bravo ! Vous avez deviné le nombre secret ! Votre score est : " + score);
                                insererScore(score);
                            } else if (guess < randomNumber) {
                                JOptionPane.showMessageDialog(null, "Le nombre secret est plus grand !");
                            } else {
                                JOptionPane.showMessageDialog(null, "Le nombre secret est plus petit !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre entre 1 et 1000.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre valide.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre maximum de tentatives atteint. Le nombre secret était " + randomNumber);
                }
            }
        });
    }

    public static void insererScore(int score) {
        try {
            String sql = "INSERT INTO Jeux1 (score) VALUES (?)"; // Requête d'insertion
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Définir les valeurs des paramètres de la requête
                pstmt.setInt(1, score);
                // Exécution de la requête d'insertion
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

