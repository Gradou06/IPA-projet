import javax.swing.event.ChangeEvent; 
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JFrame;

/*
 * Cette classe permets de créer le slider pour le contraste
 */

class ContrasteChange extends JFrame implements ChangeListener { 
 
    private JSlider slider;
    private JLabel label;

    public ContrasteChange(JSlider s, JLabel label) {
        slider = s;
        this.label = label;
        


    }
  
    public void stateChanged(ChangeEvent e) 
    {
        label.setText(""+slider.getValue());
    }
}