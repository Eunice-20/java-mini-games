package Jeux_Java;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class gamepmp extends JFrame implements ActionListener {

public static String file = "score.txt"; 

public static JTextField nbj;

public static JButton valide;

public static JLabel label;

public static JLabel label2;

public static int tenta = 0;

public static int niv = 1;

public static int devine = (int)(Math.random() * 1000);

public static ImageIcon haut = new ImageIcon("./asset/haut.png");

public static ImageIcon bas = new ImageIcon("./asset/bas.png");
public static void main(String[] args) 
   {
        Scanner myObj = new Scanner(System.in);
        label = new JLabel();
        label.setText("Devine a quelle nombre je pense.");
        label.setBounds(20, 00, 400, 100);
        label2 = new JLabel();
        label2.setText("Plus ton score est bas, mieux c'est.");
        label2.setBounds(20, 50, 400, 50);

        JFrame frame = new JFrame("+ ou -");
        JFrame gamepmpFrame = new JFrame();
        gamepmpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        gamepmpFrame.setVisible(false);
        frame.setSize(250, 250);
        frame.setLayout(null);
        frame.add(label);
        frame.add(label2);
        
        System.out.println(devine);
        
        valide = new JButton();
        valide.setBounds(35, 100, 50, 25);
        valide.addActionListener(new gamepmp());

        nbj = new JTextField();
        nbj.setBounds(85, 100, 100, 25);
        
        frame.add(valide);
        frame.add(nbj);
        //frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==valide) {
            int devi=Integer.parseInt(nbj.getText());
            System.out.println(devi);
            if(devi < devine) {
                label.setText("C'est plus.");
                label.setIcon(haut);
                label2.setText("");
                tenta ++;
            }
            else if(devi > devine) {
                label.setText("C'est moins.");
                label.setIcon(bas);
                label2.setText("");
                tenta ++;
            }
            else {
                tenta ++;
                niv ++;
                label.setText("C'est juste. ton score est de :" + tenta);
                label.setIcon(null);
                label2.setText("Tu passe niveau " + niv);
                devine = (int)(Math.random() * 1000);
                System.out.println(devine);
            }
            nbj.setText("");
        }
    }  
}
    
        //String s = e.getActionCommand();
        //if (s.equals("valide")) {

        //     System.out.print(nbj.getText());
    
        //         new FileWriter(file);
        //         File myObj = new File(file);  
        //         if (myObj.createNewFile()) {  
        //         System.out.println("File created: " + myObj.getName());  
        //         } else {  
        //         System.out.println("File already exists.");  
        //         }
        //         FileWriter fileWriter = new FileWriter(file);
        //         PrintWriter printWriter = new PrintWriter(fileWriter);
        //         printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        //     printWriter.close();
            
        // }
        // 

