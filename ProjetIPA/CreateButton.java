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

    public CreateButton(JFrame frame, Traitement t, Interface interface1, JSlider slider) {
        this.frame = frame;
        this.t = t;
        this.interface1 = interface1;
        this.slider = slider;
    }

    public void build() {
        JButton assombrissemeButton = new JButton("Assombrissement");
        JButton eclaircissementButton = new JButton("Eclaircissement");
        JButton reverseButton = new JButton("Reverse");
        JButton contrasteButton = new JButton("Contraste");
        JButton flipHButton = new JButton("Flip H/B");


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
