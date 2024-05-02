
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.TimerTask;

public class fghj {
    public static void main(String[] args){
    JFrame frame = new JFrame();

    JPanel panel = new JPanel();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
    frame.setLayout(null);
    Image img =Toolkit.getDefaultToolkit().getImage("./Game/asset/tapis");
    frame.setIconImage(img);
    }
}
