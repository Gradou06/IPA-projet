import java.awt.event.ActionListener;
import java.sql.Savepoint;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        
        String fn = "D:\\Cours\\MaM3\\IPA\\IPA-projet\\ProjetIPA\\images\\shining.png";
        Traitement t = new Traitement();
        t.setImgName(fn);
        
        if (t.getIsRGB()){
            System.out.println("RGB");
            t.imgRBGtoPixelTab();
        }
        else{
            System.out.println("GREY");
            t.imgGreytoPixelTab();
            
            }

        
        if (t.getIsRGB()){
            System.out.println("RGB");
            t.saveRGBImg();
        }
        else{
            System.out.println("GREY");
            t.saveGreyImg();
        }
    
// LA FENETRE
    JFrame frame = new JFrame("Traitement d'image");
    Interface interface1 = new Interface(t.getWidth(),t.getTabRGB());
    Menu menu = new Menu(t,interface1,frame);
    frame.add(interface1);
    frame.pack();
    frame.setSize(t.getWidth()+170,t.getHeight()+50);
    frame.setVisible(true);
}
}
