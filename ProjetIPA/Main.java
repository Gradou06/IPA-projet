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

    // LA BARRE DE MENU
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem exit = new JMenuItem("Exit");
    save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (t.getIsRGB()){
            t.saveRGBImg();
            }
            else{
            t.saveGreyImg();
            }
        }
    });
    exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    });
    menu.add(save);
    menu.add(exit);
    menubar.add(menu);

    
    
    
    // LES BOUTONS
    JButton contrasteButton = new JButton("Constraste");
    JButton changeColorButton = new JButton("Change color");
    JButton assombrissemeButton = new JButton("Assombrissement");
    JButton eclaircissementButton = new JButton("Eclaircissement");

    //changeColorButton.setBounds(0,0,120,30);
    contrasteButton.setBounds(0,0,120,30);
    frame.add(new JPanel().add(contrasteButton));
    contrasteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (t.getIsRGB()){
            t.changeRGBContraste(255);
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }}
    });

    assombrissemeButton.setBounds(0,52,120,30);
    frame.add(new JPanel().add(assombrissemeButton));
    assombrissemeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (t.getIsRGB()){
            t.changeRGBAssombrissement();
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }}
    });
    
    eclaircissementButton.setBounds(0,26,120,30);
    frame.add(new JPanel().add(eclaircissementButton));
    eclaircissementButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (t.getIsRGB()){
            t.changeRGBEclairage();
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }}
    });

    /*changeColorButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (t.getIsRGB()){
            t.changeRGBtoGrey();
            interface1.redefine(t.getWidth(),toGrey);
            interface1.repaint();
            frame.repaint();
        }}
    });*/

    // AJOUT DE TOUT CA DANS LA FENETRE
    
    //frame.add(new JPanel().add(changeColorButton));
    frame.setJMenuBar(menubar);
    frame.add(interface1);
    frame.pack();
    frame.setSize(t.getWidth()+170,t.getHeight()+50);
    frame.setVisible(true);
}
}
