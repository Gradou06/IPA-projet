import javax.swing.*;
import java.awt.event.*;
public class TextFieldTest implements ActionListener
{
  JTextField text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,text17,text18,text19,text20,text21,text22,text23,text24,text25;
  JButton btn;
  int[][] matriceC5= new int[5][5];
  int[][] matrice;
  Traitement t;
  JFrame f;
    Interface interface1;
  
  TextFieldTest(JFrame f,Traitement t, Interface interface1){
    this.t=t;
    this.interface1=interface1;
    this.f=f;
    this.text1 = new JTextField("0");
    this.text2 = new JTextField("0");
    this.text3 = new JTextField("0");
    this.text4 = new JTextField("0");
    this.text5 = new JTextField("0");
    this.text6 = new JTextField("0");
    this.text7 = new JTextField("0");
    this.text8 = new JTextField("0");
    this.text9 = new JTextField("0");
    this.text10 = new JTextField("0");
    this.text11 = new JTextField("0");
    this.text12 = new JTextField("0");
    this.text13 = new JTextField("0");
    this.text14 = new JTextField("0");
    this.text15 = new JTextField("0");
    this.text16 = new JTextField("0");
    this.text17 = new JTextField("0");
    this.text18 = new JTextField("0");
    this.text19 = new JTextField("0");
    this.text20 = new JTextField("0");
    this.text21 = new JTextField("0");
    this.text22 = new JTextField("0");
    this.text23 = new JTextField("0");
    this.text24 = new JTextField("0");
    this.text25 = new JTextField("0");
  }
  public void build(){
    int x = 30;
    int y = 200;
    int width=20;
    int height=20;
    int space=30;
    text1.setBounds(x,y, width,height);
    text2.setBounds(x+space,y, width,height);
    text3.setBounds(x+2*space,y, width,height);
    text4.setBounds(x+3*space,y, width,height);
    text5.setBounds(x+4*space,y, width,height);
    text6.setBounds(x,y+1*space, width,height);
    text7.setBounds(x+1*space,y+1*space, width,height);
    text8.setBounds(x+2*space,y+1*space, width,height);
    text9.setBounds(x+3*space,y+1*space, width,height);
    text10.setBounds(x+4*space,y+1*space, width,height);
    text11.setBounds(x,y+2*space, width,height);
    text12.setBounds(x+1*space,y+2*space, width,height);
    text13.setBounds(x+2*space,y+2*space, width,height);
    text14.setBounds(x+3*space,y+2*space, width,height);
    text15.setBounds(x+4*space,y+2*space, width,height);
    text16.setBounds(x,y+3*space, width,height);
    text17.setBounds(x+1*space,y+3*space, width,height);
    text18.setBounds(x+2*space,y+3*space, width,height);
    text19.setBounds(x+3*space,y+3*space, width,height);
    text20.setBounds(x+4*space,y+3*space, width,height);
    text21.setBounds(x,y+4*space, width,height);
    text22.setBounds(x+1*space,y+4*space, width,height);
    text23.setBounds(x+2*space,y+4*space, width,height);
    text24.setBounds(x+3*space,y+4*space, width,height);
    text25.setBounds(x+4*space,y+4*space, width,height);
    f.add(text1);
    f.add(text2);
    f.add(text3);
    f.add(text4);
    f.add(text5);
    f.add(text6);
    f.add(text7);
    f.add(text8);
    f.add(text9);
    f.add(text10);
    f.add(text11);
    f.add(text12);
    f.add(text13);
    f.add(text14);
    f.add(text15);
    f.add(text16);
    f.add(text17);
    f.add(text18);
    f.add(text19);
    f.add(text20);
    f.add(text21);
    f.add(text22);
    f.add(text23);
    f.add(text24);
    f.add(text25);
    btn = new JButton("Click");
    btn.setBounds(00,156,200,30);
    btn.addActionListener(this);
    f.add(btn);
  }

  public Boolean verif(){
    try{
        matriceC5[0][0]=Integer.parseInt(text1.getText());
        matriceC5[0][1]=Integer.parseInt(text2.getText());
        matriceC5[0][2]=Integer.parseInt(text3.getText());
        matriceC5[0][3]=Integer.parseInt(text4.getText());
        matriceC5[0][4]=Integer.parseInt(text5.getText());
        matriceC5[1][0]=Integer.parseInt(text6.getText());
        matriceC5[1][1]=Integer.parseInt(text7.getText());
        matriceC5[1][2]=Integer.parseInt(text8.getText());
        matriceC5[1][3]=Integer.parseInt(text9.getText());
        matriceC5[1][4]=Integer.parseInt(text10.getText());
        matriceC5[2][0]=Integer.parseInt(text11.getText());
        matriceC5[2][1]=Integer.parseInt(text12.getText());
        matriceC5[2][2]=Integer.parseInt(text13.getText());
        matriceC5[2][3]=Integer.parseInt(text14.getText());
        matriceC5[2][4]=Integer.parseInt(text15.getText());
        matriceC5[3][0]=Integer.parseInt(text16.getText());
        matriceC5[3][1]=Integer.parseInt(text17.getText());
        matriceC5[3][2]=Integer.parseInt(text18.getText());
        matriceC5[3][3]=Integer.parseInt(text19.getText());
        matriceC5[3][4]=Integer.parseInt(text20.getText());
        matriceC5[4][0]=Integer.parseInt(text21.getText());
        matriceC5[4][1]=Integer.parseInt(text22.getText());
        matriceC5[4][2]=Integer.parseInt(text23.getText());
        matriceC5[4][3]=Integer.parseInt(text24.getText());
        matriceC5[4][4]=Integer.parseInt(text25.getText());
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Prière de mettre des entiers");
        return false;
    }
    return true;
  }

  public int getDim(){
    int dim=0;
    for(int j=0;j<5;j++){
        for(int i=0;i<5;i++){
            if(matriceC5[j][i]!=0){
                dim=Math.max(Math.max(i,j),dim);
            }
        }
    
    }
    dim+=1;
    if (dim==2 || dim==4){
        return dim+1;
    }
    else if (dim==3 || dim==5){
        return dim;
    }
    else{
        JOptionPane.showMessageDialog(null, "Prière de mettre une matrice de dimension valide");
        return -1;
    }
  }

  public void redefine(Traitement t){
    this.t=t;
  }



  public void actionPerformed(ActionEvent e) 
  {
    if (verif()){
        int max=getDim();
        if (max!=-1){
            matrice=new int[max][max];
            for(int j=0;j<max;j++){
                for(int i=0;i<max;i++){
                    matrice[j][i]=matriceC5[j][i];
                }
            }
        
        t.convolution(matrice);
        if (t instanceof TraitementRGB) {
          interface1.redefine(t.getWidth(),t.getTabRGB());
      }
      else{
          interface1.redefine(t.getWidth(),t.getTabGrey());
      }
        interface1.repaint();
        f.repaint();
        }
    }
    
  }
}