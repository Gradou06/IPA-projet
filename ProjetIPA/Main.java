import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        String fn = "D:\\Cours\\MaM3\\IPA\\IPA-projet\\ProjetIPA\\images\\shining-gs.png";
        Traitement t = new Traitement();
        t.setImgName(fn);
        
        if (t.getIsRGB()){
            System.out.println("RGB");
            t.imgRBGtoPixelTab();
        }
        else{
            System.out.println("GREY");
            t.imgGreytoPixelTab();
            t.changeGreytoRGB();
            
            }

        
        if (t.getIsRGB()){
            System.out.println("RGB");
            t.saveRGBImg();
        }
        else{
            System.out.println("GREY");
            t.saveGreyImg();
        }
    JFrame frame = new JFrame("salut");
    Interface interface1 = new Interface(t.getWidth(),t.getTabRGB());
    frame.add(interface1);
    frame.setSize(t.getWidth()+50,t.getHeight()+50);
    frame.setVisible(true);
}
}
