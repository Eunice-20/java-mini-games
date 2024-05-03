package Jeux_Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class memory extends JPanel implements ActionListener 
{

    static final String DB_URL = "jdbc:mysql://localhost:3306/database_db";
    static final String USER = "eunice";
    static final String PASS = "eunice";
    private static Connection conn;

   // public static Image Bg; 

   public static  long previousTime;

    public static JPanel panel = new JPanel();

    public static JButton card , card2 , card3 , card4 , card5 , card6 , card7 , card8 , card9 , card10 , card11 , card12 , card13 , card14 , card15 , card16 , card17 , card18, cardOrigin;

    public static JButton restart , exit; 

    public static JFrame frame = new JFrame("Memory");

    public static int cardt;

    public static javax.swing.JButton h;

    public static String k;

    public static javax.swing.JButton hj;

    public static String kj;

    public static JLabel label;

    public static ImageIcon gp = new ImageIcon("./Game/asset/Memory/inscryber-card.png");

    public static int cp;

    //public static ??? time;
    
    public static int carda;

                public static void main(String[] args) {
                    SwingUtilities.invokeLater(() -> memory());
                }

    public static void memory(){

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        JLabel background = new JLabel(new ImageIcon(("./Game/asset/Memory/tapiscart.jpg")));
        frame.setContentPane(background);
        label = new JLabel();
        label.setText("Trouve les carte paire.");
        label.setBounds(450, 100, 1000, 100);
        label.setFont(new Font("", Font.BOLD, 50));
frame.add(label);
    JButton [] tabname = {
        card = new JButton("./Game/asset/Memory/1.png"), card2 = new JButton("./Game/asset/Memory/1.png"),card3 = new JButton("./Game/asset/Memory/2.png"),
        card4 = new JButton("./Game/asset/Memory/2.png"), card5 = new JButton("./Game/asset/Memory/3.png"),card6 = new JButton("./Game/asset/Memory/3.png"),
        card7 = new JButton("./Game/asset/Memory/4.png"), card8 = new JButton("./Game/asset/Memory/4.png"),card9 = new JButton("./Game/asset/Memory/5.png"),
        card10 = new JButton("./Game/asset/Memory/5.png"), card11 = new JButton("./Game/asset/Memory/6.png"),card12 = new JButton("./Game/asset/Memory/6.png"),
        card13 = new JButton("./Game/asset/Memory/7.png"), card14 = new JButton("./Game/asset/Memory/7.png"),card15 = new JButton("./Game/asset/Memory/8.png"),
        card16 = new JButton("./Game/asset/Memory/8.png"), card17 = new JButton("./Game/asset/Memory/9.png"),card18 = new JButton("./Game/asset/Memory/9.png"),
        cardOrigin = new JButton(),
    };
    int x = 50;
    int y = 200;
    int ra = 6;
    for(int gg = 18; gg != -1 ; gg --)
    {
        if(ra == 0)
        {
            x = 50;
            y += 200;
            ra = 6;
        }
        int undex = (int)(Math.random() * gg);
        tabname[undex].setBounds(x, y, 180, 100);
        tabname[undex].addActionListener(new memory());
        frame.add(tabname[undex]);
        tabname[undex].setIcon(gp);
        tabname = removeTheElement(tabname, undex);
        x += 245;
        ra -= 1;
    }
        //label.setText("Devine a quelle nombre je pense.");
        JFrame SnakeFrame = new JFrame();
        SnakeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        SnakeFrame.setVisible(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setLayout(null);
        Image img =Toolkit.getDefaultToolkit().getImage("./Game/asset/tapis.jpg");
        frame.setIconImage(img);
        previousTime = System.currentTimeMillis();
    }
        


	public void actionPerformed(ActionEvent e) 
    {
        if(((JButton)e.getSource()) == exit){
            System.exit(0);
        }
        if(((JButton)e.getSource()) == restart){
        memory();
        }
        label.setText("");
        if(cardt == 0 && ((JButton)e.getSource()) != restart)
        {
            h = ((JButton)e.getSource());
            k = ((JButton)e.getSource()).getText();
            ((JButton)e.getSource()).setIcon(new ImageIcon(((JButton)e.getSource()).getText()));
            cardt += 1;
        }
        if(cardt == 1 && ((JButton)e.getSource()) != h)
        {
            hj = ((JButton)e.getSource());
            kj = ((JButton)e.getSource()).getText();
            ((JButton)e.getSource()).setIcon(new ImageIcon(((JButton)e.getSource()).getText()));
            cardt += 1;
        }
        Timer timer = new Timer(2000, m -> {
            if(cardt >= 2)
            {  
                if(k == kj &&  h != hj)
                    {
                    hj.setVisible(false); 
                    h.setVisible(false);     
                    frame.remove(hj);
                    frame.remove(h);
                    carda +=2;
                    System.out.println(carda);
                    }
                hj.setIcon(gp);
                h.setIcon(gp);
                cardt = 0;
                cp += 1; 
            if(carda == 18)
    {
        victory();
    } 
        }
    });
    timer.setRepeats(false);
    timer.start();
    }


        public static JButton[] removeTheElement(JButton[] arr, int index) 
        { 
            if (arr == null || index < 0
                || index >= arr.length) { 
      
                return arr; 
            } 
            JButton[] anotherArray = new JButton[arr.length - 1]; 
            for (int i = 0, k = 0; i < arr.length; i++) { 
                if (i == index) { 
                    continue; 
                } 
                anotherArray[k++] = arr[i]; 
            }  
            return anotherArray;
	    }
public static void menumemo(){
        JLabel a = new JLabel(new ImageIcon(("./Game/asset/Memory/menu.png")));
        frame.setContentPane(a);
}

public static void victory(){
    long currentTime = System.currentTimeMillis();
    double elapsedTime = (currentTime - previousTime) / 1000.0;
    label.setText("Temps : " + elapsedTime + "s");
   JLabel label2 = new JLabel();
    label2.setText("Nombre de coup :" + cp);
    label2.setBounds(450, 180, 1000, 100);
    label2.setFont(new Font("", Font.BOLD, 40));
frame.add(label2);
int scoreT ,scoreC, score;
scoreC = 50 - (cp - 9);
if(scoreC <= 0){scoreC = 0;}
scoreT = 50 - ((int)elapsedTime/2);
if(scoreT <= 0){scoreT = 0;}
score = scoreT + scoreC;
JLabel label3 = new JLabel();
    label3.setText("Ton score est de :" + score);
    label3.setBounds(450, 230, 1000, 100);
    label3.setFont(new Font("", Font.BOLD, 30));
frame.add(label3);
    previousTime = currentTime;
    exit = new JButton("exit");
    restart = new JButton("restart");
    exit.setBounds(800, 350, 180, 100);
        exit.addActionListener(new memory());
        frame.add(exit);
        restart.setBounds(500, 350, 180, 100);
        restart.addActionListener(new memory());
        frame.add(restart);
        carda = 0;
        cp = 0;
        insererColonne(score, elapsedTime, cp);
}

public static void insererColonne(int score , double elapsedTime , int cp) {
    try {
        String sql = "INSERT INTO Memorybase (score, elapsedTime, cp) VALUES (?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score);
            pstmt.setDouble(2, elapsedTime);
            pstmt.setInt(3, cp);
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




       /*@Override
        public void actionPerformed(ActionEvent e) {
           // get reference to bound component
           KeyBindingPanel panel = (KeyBindingPanel) e.getSource();
           panel.setOvalColor(color);
        }*/
}