import javax.swing.event.*; 
import javax.swing.*; 
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