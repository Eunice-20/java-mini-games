package Jeux_Java;

 import java.sql.SQLException;
 import java.sql.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.sound.sampled.*;
 import java.io.File;
 import java.io.IOException;
import java.awt.*;

public class User extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/database_db";
    static final String USER = "eunice";
    static final String PASS = "eunice";

    private JTextField pseudoField;
    private JButton saveButton;
    private static Connection conn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    public User() {
       

        super(" pseudo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

      

        ImageIcon img = new ImageIcon("./Game/asset/user.png");
        Image icon = img.getImage();
        setIconImage(icon);

        pseudoField = new JTextField(20);
        saveButton = new JButton("Save");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pseudo = pseudoField.getText();
                if (pseudo.length() < 4) {
                    JOptionPane.showMessageDialog(User.this, "Le pseudo doit contenir au moins 4 caractères!");
                    return; 
                }
        
                if (!pseudo.isEmpty()) {
                    insererColonne(pseudo);
                    playSound("./Game/Jeux_Java/Ressources/Music/Dema.wav");

                    MenuGame menu = new MenuGame();
                    menu.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(User.this, "Veuillez saisir un pseudo.");
                }
            }
        });

        add(new JLabel("Pseudo:"));
        add(pseudoField);
        add(saveButton);

        getContentPane().setPreferredSize(new Dimension(300, 70));
        setLocationRelativeTo(null);
        pack();
    }


    public static void insererColonne(String pseudo) {
        try {
            String sql = "INSERT INTO userbase (pseudo) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, pseudo);
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
}
