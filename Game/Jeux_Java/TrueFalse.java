package Jeux_Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrueFalse extends JFrame implements ActionListener {
    private List<Question> questions;
    private JLabel questionLabel;
    private JButton trueButton;
    private JButton falseButton;
    private JLabel scoreLabel;
    private int currentQuestionIndex;
    private int score;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrueFalse::new);
    }

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
        JFrame TrueFalseFrame = new JFrame();
        TrueFalseFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        TrueFalseFrame.setVisible(false);
        setSize(1300, 1300);
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();
        position.gridx = 0;
        position.gridy = 0;
        position.anchor = GridBagConstraints.CENTER;

        ImageIcon img = new ImageIcon("./asset/TrueFalse.png");
        Image icon = img.getImage();
      
         setIconImage(icon);

        questionLabel = new JLabel(questions.get(currentQuestionIndex).getText());
        add(questionLabel, position);

        position.gridy++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        trueButton = new JButton("Vrai");
        trueButton.addActionListener(this);
        buttonPanel.add(trueButton);
        falseButton = new JButton("Faux");
        falseButton.addActionListener(this);
        buttonPanel.add(falseButton);
        add(buttonPanel, position);

        position.gridy++;
        scoreLabel = new JLabel("Score: " + score);
        add(scoreLabel, position);

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
