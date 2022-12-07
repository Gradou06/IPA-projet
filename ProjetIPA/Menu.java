import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JMenuBar{

    private Traitement t;
    private Interface interface1;
    private JFrame frame;
    private JMenuBar menubar;

    public Menu(Traitement t, Interface interface1 , JFrame frame) {
        this.t = t;
        this.interface1 = interface1;
        this.frame = frame;
        this.menubar = new JMenuBar();
    


    // LA BARRE DE MENU
    JMenu menu = new JMenu("File");
    JMenu menu2 = new JMenu("Edit");
    JMenuItem back = new JMenuItem("Back");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem changeImg = new JMenuItem("Change image");

    changeImg.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser choose = new JFileChooser(
        FileSystemView
        .getFileSystemView()
        .getHomeDirectory()
        );
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images PNG", "png");
        choose.addChoosableFileFilter(filter);
        int res = choose.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
        File file = choose.getSelectedFile();
        t.setImgName(file.getAbsolutePath());
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
            
        }
    });
    save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.saveImg();
        }
    });
    exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    });
    back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.back();
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
    });
    menu.add(save);
    menu.add(exit);
    menu2.add(back);
    menu.add(changeImg);
    menubar.add(menu);
    menubar.add(menu2);
    
    frame.setJMenuBar(menubar);


    

    // Créer une étiquette
    JLabel label = new JLabel(); 

    // Créer un panneau
    JPanel p = new JPanel(); 
    p.setBounds(0, 400, 200, 50);
    // Créer un slider 
    JSlider slider = new JSlider(0, 255, 255); 
    ContrasteChange obj = new ContrasteChange(slider, label); 
    // Peindre la piste(track) et l'étiquette
    slider.setPaintTrack(true);
    slider.setPaintTicks(true); 
    slider.setPaintLabels(true); 
    // Définir l'espacement
    slider.setMajorTickSpacing(0); 
    slider.setMinorTickSpacing(5); 

    // Associer le Listener au slider
    slider.addChangeListener(obj); 
    // Ajouter le slider au panneau
    p.add(slider); 
    p.add(label); 

    // Ajouter le panneau au frame
    frame.add(p); 
    // Définir le texte de l'étiquette
    label.setText("" + slider.getValue()); 




    // LES BOUTONS
    JButton assombrissemeButton = new JButton("Assombrissement");
    JButton eclaircissementButton = new JButton("Eclaircissement");
    JButton reverseButton = new JButton("Reverse");
    JButton contrasteButton = new JButton("Contraste");
    JButton flipHButton = new JButton("Flip H/B");
    JButton convolButton = new JButton("Convol");

    convolButton.setBounds(0,156,200,30);
    frame.add(new JPanel().add(convolButton));
    int [][] tab = {{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
    convolButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.convolution(tab);
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
    });

    contrasteButton.setBounds(0,455,200,30);
    frame.add(new JPanel().add(contrasteButton));
    contrasteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.changeContraste(slider.getValue());
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
    });

    flipHButton.setBounds(0,104,200,30);
    frame.add(new JPanel().add(flipHButton));
    flipHButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.reverseHautBas();
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
    });

    reverseButton.setBounds(0,78,200,30);
    frame.add(new JPanel().add(reverseButton));
    reverseButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.reverseImg();
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
    });

    assombrissemeButton.setBounds(0,52,200,30);
    frame.add(new JPanel().add(assombrissemeButton));
    assombrissemeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.changeAssombrissement();
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
    });
    
    eclaircissementButton.setBounds(0,130,200,30);
    frame.add(new JPanel().add(eclaircissementButton));
    eclaircissementButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.changeEclairage();
            interface1.redefine(t.getWidth(),t.getTabRGB());
            interface1.repaint();
            frame.repaint();
        }
    });
    
}
}

