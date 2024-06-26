package Jeux_Java;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException; 
import java.sql.Connection;
import java.io.File;
import javax.imageio.ImageIO;

public class MenuGame extends JFrame { 

    static final String DB_URL = "jdbc:mysql://localhost:3306/database_db";
    static final String USER = "eunice";
    static final String PASS = "eunice";
    private static Connection conn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                playSound("./Game/Jeux_Java/Ressources/Music/Dema.wav");
                new MenuGame().setVisible(true);
     
            }
        });
    }
    
    public MenuGame() {

        setTitle("Multi_Game");
        
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image image = ImageIO.read(getClass().getResource("./Ressources/MenuImg/Img_Font.png"));
                    g.drawImage(image, 10, 10, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        



        getContentPane().add(panel);
        ImageIcon img = new ImageIcon("./Game/asset/Multi_Game.png");
        Image icon = img.getImage();
        setIconImage(icon);

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 49, 50)); 

        ImageIcon flappyBirdIcon = new ImageIcon("./Game/Jeux_Java/Ressources/MenuImg/flappy_Brid.png");
        JButton flappyBirdButton = new JButton(resizeImage(flappyBirdIcon, 200, 200));
        flappyBirdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");
                    startFlappyBird();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ImageIcon snakeIcon = new ImageIcon("./Game/Jeux_Java/Ressources/MenuImg/Snake_Game.png");
        JButton snakeButton = new JButton(resizeImage(snakeIcon, 200, 200));
        snakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");
                    startSnake();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ImageIcon memoryIcon = new ImageIcon("./Game/asset/tapis.jpg");
        JButton memoryButton = new JButton(resizeImage(memoryIcon, 200, 200));
        memoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");

                    startmemory();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ImageIcon SudokuIcon = new ImageIcon("./Game/Jeux_Java/Ressources/MenuImg/Img_Sudoku.png");
        JButton SudokuButton = new JButton(resizeImage(SudokuIcon, 200, 200));
        SudokuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");

                    startSudoku();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ImageIcon Game2048Icon = new ImageIcon("./Game/Jeux_Java/Ressources/MenuImg/Img_Game2048.png");
        JButton Game2048Button = new JButton(resizeImage(Game2048Icon, 200, 200));
        Game2048Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");
                    startGame2048();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ImageIcon  gamepmpIcon = new ImageIcon("./Game/Jeux_Java/Ressources/MenuImg/Img_gamepmp.png");
        JButton  gamepmpButton = new JButton(resizeImage( gamepmpIcon, 200, 200));
        gamepmpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");

                    startgamepmp();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ImageIcon  TrueFalseIcon = new ImageIcon("./Game/Jeux_Java/Ressources/MenuImg/True_False.png");
        JButton  TrueFalseButton = new JButton(resizeImage( TrueFalseIcon, 200, 200));
        TrueFalseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {   
                    playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");

                    startTrueFalse();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    

        ImageIcon  HangmanIcon = new ImageIcon("./Game/Jeux_Java/Ressources/MenuImg/Img_Hangman.png");
        JButton  HangmanButton = new JButton(resizeImage( HangmanIcon, 200, 200));
        HangmanButton.addActionListener(new ActionListener() {
           
                public void actionPerformed(ActionEvent e) {
                    try {
                        playSound("./Game/Jeux_Java/Ressources/Music/ChMenu.wav");
                        startHangman();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        });

        
        panel.add(flappyBirdButton);
        panel.add(snakeButton);
        panel.add(memoryButton);
        panel.add(SudokuButton);
        panel.add(Game2048Button);
        panel.add(gamepmpButton);
        panel.add(TrueFalseButton);
        panel.add(HangmanButton);

        add(panel);
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



    private void startFlappyBird() throws Exception {
        FlappyBird.main(new String[]{});
    }

    private void startSnake() throws Exception {
        Snake.main(new String[]{});
    }

    private void startmemory() throws Exception{
        memory.main(new String[]{});
    }

    private void startSudoku() throws Exception {
        Sudoku.main(new String[]{});
    }

    private void  startGame2048() throws Exception {
        Game2048.main(new String[]{});
    }

    private void startgamepmp() throws Exception {
        gamepmp.main(new String[]{});
    }
   
    private void startTrueFalse() throws Exception {
        TrueFalse.main(new String[]{});
        
    }

    private void startHangman() throws Exception {
        Hangman.main(new String[]{});
        
    }

    private void setTitleFont(Font font) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font currentFont = getFont();
        Font newFont = currentFont.deriveFont(currentFont.getStyle(), 24);
        setFont(newFont);
    }
    

    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); 
        return new ImageIcon(resizedImage); 
    }

}
