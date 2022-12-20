import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateButton {
    private JFrame frame;
    private Traitement t;
    private Interface interface1;
    private JSlider slider;
    private Menu menu;
    private TextFieldTest convolMatrice;

    public CreateButton(JFrame frame, Traitement t, Interface interface1, JSlider slider, Menu menu) {
        this.frame = frame;
        this.t = t;
        this.interface1 = interface1;
        this.slider = slider;
        this.menu = menu;

    }

    public void setConvolMatrice(TextFieldTest convolMatrice){
        this.convolMatrice=convolMatrice;
    }

    public void build() {
        JButton assombrissemeButton = new JButton("Assombrissement");
        JButton eclaircissementButton = new JButton("Eclaircissement");
        JButton reverseButton = new JButton("Reverse");
        JButton contrasteButton = new JButton("Contraste");
        JButton flipHButton = new JButton("Flip H/B");
        JButton convol3 = new JButton("3x3");
        JButton convol5 = new JButton("5x5");
        JButton convol = new JButton("Appliquer Convol");
        convolMatrice.setMatriceLength(false);
        convolMatrice.build();


    contrasteButton.setBounds(0,455,200,30);
    frame.add(new JPanel().add(contrasteButton));
    contrasteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.changeContraste(slider.getValue());
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            interface1.repaint();
            menu.updateBack();
            frame.repaint();
        }
    });

    flipHButton.setBounds(0,104,200,30);
    frame.add(new JPanel().add(flipHButton));
    flipHButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.reverseHautBas();
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            interface1.repaint();
            menu.updateBack();
            frame.repaint();
        }
    });

    reverseButton.setBounds(0,78,200,30);
    frame.add(new JPanel().add(reverseButton));
    reverseButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.reverseImg();
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            interface1.repaint();
            menu.updateBack();
            frame.repaint();
        }
    });

    assombrissemeButton.setBounds(0,52,200,30);
    frame.add(new JPanel().add(assombrissemeButton));
    assombrissemeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            t.changeAssombrissement();
            menu.updateBack();
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
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
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            interface1.repaint();
            menu.updateBack();
            frame.repaint();
        }
    });

    //Bouton pour passer sur une matrice 3x3
    convol3.setBounds(0,350,100,30);
    frame.add(new JPanel().add(convol3));
    convol3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            convolMatrice.setMatriceLength(true);
            convolMatrice.build();
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            interface1.repaint();
            frame.repaint();
        }
    });

    //Bouton pour passer sur une matrice 5x5
    convol5.setBounds(100,350,100,30);
    frame.add(new JPanel().add(convol5));
    convol5.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            convolMatrice.setMatriceLength(false);
            convolMatrice.build();
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            interface1.repaint();
            frame.repaint();

        }
    });

    //Le bouton pour appliquer la convolution
    convol.setBounds(00,156,200,30);
    frame.add(new JPanel().add(convol));
    convol.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            convolMatrice.actionPerformed(evt);
            t.convolution(convolMatrice.getMatrice());
            if (t instanceof TraitementRGB) {
                interface1.redefine(t.getWidth(),t.getTabRGB());
            }
            else{
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            interface1.repaint();
            menu.updateBack();
            frame.repaint();
            
        }

    });


    JButton changeToGreyButton = new JButton("Change to grey");
    changeToGreyButton.setBounds(0,26,200,30);
    frame.add(new JPanel().add(changeToGreyButton));
    changeToGreyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (t instanceof TraitementRGB) {
                String name= t.imgName;
                t.changeColor();
                t=new TraitementGrey(t.getTabGrey(),t.getWidth(),t.getHeight());
                t.changeImgName(name);
                interface1.redefine(t.getWidth(),t.getTabGrey());
            }
            menu.redefine(t);
            menu.updateBack();
            interface1.repaint();
            frame.repaint();
        }
    });
    }

    public void redefine(JFrame frame, Traitement t, Interface interface1, JSlider slider, TextFieldTest convolMatrice) {
        this.frame = frame;
        this.t = t;
        this.interface1 = interface1;
        this.slider = slider;
        this.convolMatrice = convolMatrice;
    }


}
