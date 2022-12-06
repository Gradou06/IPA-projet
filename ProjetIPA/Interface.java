import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Canvas;

public class Interface extends JPanel{
    private int width;
    private int[] tab;
    

    public Interface(int width, int[] tab){
        this.tab = tab;
        this.width=width;
    }

    public void paint(Graphics g) {
        super.paint(g);
        int x=120;
        int y=0;
        for (int i=0; i<tab.length; i++){
            g.setColor(new Color((this.tab[i] >> 16) & 0xFF, (this.tab[i] >> 8) & 0xFF, tab[i] & 0xFF));

            g.fillRect(x, y, 1, 1);
            x++;
            if (x==width+120){
                x=120;
                y++;
            }
        }
    }

    public void redefine(int width, int[] tab){
        this.tab = tab;
        this.width=width;
    }

}
    
