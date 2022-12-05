
import java.awt.*;
import javax.swing.*;

public class ButtonTest extends JPanel {


    public void paint(Graphics g) {
        super.paint(g);
        int[] tabRGB = {0, 0, 0};
        tabRGB[1] = 255;
        Color c = new Color(tabRGB[0], tabRGB[1], tabRGB[2]);
        g.setColor(c);
        g.fillRect(10,10,80,80);
        g.setColor(Color.BLUE);
        g.fillOval(150,50,80,80);
        g.setColor(c);
    }

      public static void main(String[] args) {
         JFrame f = new JFrame("Button Test");
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ButtonTest bt = new ButtonTest();
         f.add(bt);
         f.setSize(300,200);
         f.setVisible(true);
      }
   }