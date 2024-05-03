package Jeux_Java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrueFalse extends JFrame implements ActionListener {

    static final String DB_URL = "jdbc:mysql://localhost:3306/database_db";
    static final String USER = "eunice";
    static final String PASS = "eunice";
    private static Connection conn;

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrueFalse::new);
    }
    private List<Question> questions;
    private JLabel questionLabel;
    private JButton trueButton;
    private JButton falseButton;
    private JLabel scoreLabel;
    private int currentQuestionIndex;
    private int score;


    public TrueFalse() {
        questions = new ArrayList<>();
        
        questions.add(new Question("La Terre est plate.", false));
        questions.add(new Question("Le soleil tourne autour de la Terre.", false));
        questions.add(new Question("Le chat est un reptile.", false));
        questions.add(new Question("La lune est faite de fromage.", false));
        questions.add(new Question("Les humains peuvent voler sans équipement spécial.", false));
        questions.add(new Question("La gravité existe.", true));
        questions.add(new Question("L'eau est un composé chimique.", true));
        questions.add(new Question("2 + 2 = 5.", false));
        questions.add(new Question("Java est un langage de programmation.", true));
        questions.add(new Question("je suis Eunice", true));

        Collections.shuffle(questions); 
        
        currentQuestionIndex = 0;
        score = 0;

        setTitle("Jeu Vrai ou Faux");
        setSize(1300, 1300);
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();
        position.gridx = 0;
        position.gridy = 0;
        position.anchor = GridBagConstraints.CENTER;

        ImageIcon img = new ImageIcon("./asset/TrueFalse.png");
        Image icon = img.getImage();
        setIconImage(icon);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image image = ImageIO.read(getClass().getResource("./Ressources/MenuImg/Img_Font.png"));
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints panelPosition = new GridBagConstraints();
        panelPosition.gridx = 0;
        panelPosition.gridy = 0;
        panelPosition.anchor = GridBagConstraints.CENTER;
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setOpaque(false); // Make panel transparent

        questionLabel = new JLabel(questions.get(currentQuestionIndex).getText());
        panel.add(questionLabel, panelPosition);

        panelPosition.gridy++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        trueButton = new JButton("Vrai");
        trueButton.addActionListener(this);
        buttonPanel.add(trueButton);
        falseButton = new JButton("Faux");
        falseButton.addActionListener(this);
        buttonPanel.add(falseButton);
        panel.add(buttonPanel, panelPosition);

        panelPosition.gridy++;
        scoreLabel = new JLabel("Score: " + score);
        panel.add(scoreLabel, panelPosition);

        add(panel, position);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean userAnswer = false;
        if (e.getSource() == trueButton) {
            userAnswer = true;
        } else if (e.getSource() == falseButton) {
            userAnswer = false;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);
        if (userAnswer == currentQuestion.isCorrect()) {
            score++;
            JOptionPane.showMessageDialog(this, "Bonne réponse !");
        } else {
            JOptionPane.showMessageDialog(this, "Mauvaise réponse.");
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            questionLabel.setText(questions.get(currentQuestionIndex).getText());
            scoreLabel.setText("Score: " + score);
        } else {
            JOptionPane.showMessageDialog(this, "Fin du jeu. Votre score est de " + score);
            System.exit(0);
        }
    }

  
}

class Question { 
    private String text;
    private boolean correct;

    public Question(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }
}
