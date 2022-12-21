import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.color.ColorSpace;
import java.io.File;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.awt.event.ActionEvent;

/*
 * Cette classe permets de créer la partie avec tout les boutons, les barres ainsi que les boites de texte
 */
public class Menu extends JMenuBar{

    private Traitement t;
    private Interface interface1;
    private JFrame frame;
    private JMenuBar menubar;
    private TextFieldTest convolMatrice;
    private JMenuItem back;
    /*
     * Associe les bons parametres pour construire correctement le menu
     * @param t l'image et son traitement associé
     * @param interface1 l'endroit ou l'image sera placé
     * @param frame la fenetre dans laquelle tout sera affiché
     * @param convolMatrice la matrice de convolution
     */
    public Menu(Traitement t, Interface interface1 , JFrame frame, TextFieldTest convolMatrice) {
        this.t = t;
        this.interface1 = interface1;
        this.frame = frame;
        this.convolMatrice = convolMatrice;
        this.menubar = new JMenuBar();
    }

    /*
     * Permets de construire tout le menu,c'est à dire la partie avec les boutons ect...
     */
    public void build() {
    // LA BARRE DE MENU
    JMenu menu = new JMenu("File");
    JMenu menu2 = new JMenu("Edit");
    back = new JMenuItem("Back");
    back.setEnabled(false);
    JMenuItem save = new JMenuItem("Save");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem changeImg = new JMenuItem("Change image");
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

    CreateButton button = new CreateButton(frame, t, interface1, slider, this);
    button.setConvolMatrice(convolMatrice);
    button.build();

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
        
        String fn = file.getAbsolutePath();
        if (!(fn.substring(fn.length() - 3).equals("png"))) {
            return;
        }
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
        if (t instanceof TraitementRGB) {
            interface1.redefine(t.getWidth(),t.getTabRGB(),t.imgName);
        }
        else{
            // marche bien ici
            interface1.redefine(t.getWidth(),t.getTabGrey(),t.imgName);
        }
            button.redefine(frame, t, interface1, slider, convolMatrice);
            interface1.repaint();
            frame.repaint();
        }
            
        }
    });
    save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.saveImg(t.imgName);
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
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB(),t.imgName);
                if (t.getTabRGBBack()[1] == null) {
                    back.setEnabled(false);
                }
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey(),t.imgName);
                if (t.getTabGreyBack()[1] == null) {
                    back.setEnabled(false);
                }
            }
            interface1.repaint();
            frame.repaint();
        }
    });

    

    menu.add(save);
    menu.add(changeImg);
    menu.add(exit);
    menu2.add(back);
    menubar.add(menu);
    menubar.add(menu2);
    
    frame.setJMenuBar(menubar);

    
    
}

    /*
     * Permets de changer d'image
     * @param t le nouveau "Traitement" à afficher
     */
    public void redefine(Traitement t) {
        this.t = t;
    }

    /*
     * Permets de griser, ou non, le bouton "back" s'il est utilisable ou non.
     */
    public void updateBack() {
        if (t instanceof TraitementRGB && t.getTabRGBBack()[1] == null) {
            back.setEnabled(false);
        }
        else if (t instanceof TraitementGrey && t.getTabGreyBack()[1] == null) {
            back.setEnabled(false);
        }
        else{
            back.setEnabled(true);
        }
    }
}

