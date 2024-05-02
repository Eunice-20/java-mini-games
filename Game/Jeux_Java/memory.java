package Jeux_Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class memory extends JPanel implements ActionListener 
{
   // public static Image Bg;

    public static JPanel panel = new JPanel();

    public static JButton card , card2 , card3 , card4 , card5 , card6 , card7 , card8 , card9 , card10 , card11 , card12 , card13 , card14 , card15 , card16 , card17 , card18, cardOrigin;

    public static JFrame frame = new JFrame();

    public static int cardt;

    public static javax.swing.JButton h;

    public static String k;

    public static javax.swing.JButton hj;

    public static String kj;

    public static ImageIcon gp = new ImageIcon("./Game/asset/Memory/inscryber-card.png");

    public static int cp;

    //public static ??? time;
    
    public static int carda;
    
    public static void main(String[] args)
    {
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
    JFrame memoryFrame = new JFrame();
    memoryFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    memoryFrame.setVisible(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setLayout(null);
        Image img =Toolkit.getDefaultToolkit().getImage("./Game/asset/tapis.jpg");
        frame.setIconImage(img);
       
    }
        


	public void actionPerformed(ActionEvent e) 
    {
        if(cardt == 0)
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
                cp += 2; 
        }
    });
    timer.setRepeats(false);
    timer.start();
    if(carda == 18)
    {
    System.out.print("Work in progress :" + cp);
    } 
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

}