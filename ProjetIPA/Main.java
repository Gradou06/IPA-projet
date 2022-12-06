import java.awt.image.ColorModel;
import java.awt.color.ColorSpace;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        Traitement t;
        //DETECTION SI RGB OU GREY (CHANGE_IMG ?)
        String fn = "D:\\Cours\\MaM3\\IPA\\IPA-projet\\ProjetIPA\\images\\the-legend.png";
        RenderedOp ropimage;
        ropimage = JAI.create("fileload", fn);
        ColorModel cm = ropimage.getColorModel();
        if (cm.getColorSpace().getType() == ColorSpace.TYPE_RGB) {
            t = new TraitementRGB(fn);
        }
        else{
            t = new TraitementGrey(fn);
            }
        t.setImgName(fn);
        // JUSQUE LA
    JFrame frame = new JFrame("Traitement d'image");
    Interface interface1 = new Interface(t.getWidth(),t.getTabRGB());
    Menu menu = new Menu(t,interface1,frame);
    frame.add(interface1);
    frame.pack();
    frame.setSize(t.getWidth()+300,t.getHeight()+200);
    frame.setVisible(true);
}
}
