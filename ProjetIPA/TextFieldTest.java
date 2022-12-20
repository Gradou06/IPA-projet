import javax.swing.*;
import java.awt.event.*;
/*
 * Permets de créer des boites de textes
 * Elles permettent de rentrer la matrice de convolution voulu
 * text3 représente 9 boites de textes permettant de créer une matrice de convolution de taille 3x3
 * text5 représente 25 boites permettant de créer une matrice de convolution de taille 5x5
 * btn désigne un bouton
 * matriceC5 désigne la matrice de convolution de taille 5x5
 * matriceC3 désigne la matrice de convolution de taille 3x3
 * matrice désigne la matrice que l'on va utiliser pour faire la convolution
 * matriceLength désigne si la matrice va etre de taille 3x3 ou 5x5 (true signifie 3x3 et false signifie 5x5)
 */
public class TextFieldTest implements ActionListener
{
  JTextField[] text3 =new JTextField[9];
  JTextField[] text5 =new JTextField[25];
  JButton btn;
  int[][] matriceC5= new int[5][5];
  int[][] matriceC3=new int[3][3];
  int[][] matrice;
  Traitement t;
  boolean matriceLength;
  JFrame f;
  Interface interface1;
  
  /*
   * Permets de créer la boite de texte
   * on y associe le bon Jframe,l'image et son traitement, ainsi que l'interface sur laquelle on la dessine
   * 
   */
  TextFieldTest(JFrame f,Traitement t, Interface interface1){
    this.t=t;
    this.interface1=interface1;
    this.f=f;
    //On crée la matrice 3x3
    for(int i=0;i<text3.length;i++){ //On parcours le tableau et on le remplit de JTextField
      text3[i]=new JTextField("0");
    }
    
  }

  /*
   * permets de dire si la matrice est de taille 3x3 ou 5x5
   * @param t un boolean (true si la matrice est de taille 3x3 ou false si la matrice est de taille 5x5)
   */
  public void setMatriceLength(boolean t){
    matriceLength=t;
  }
  /*
   * Permets d'obtenir la matrice de convolution
   */
  public int[][] getMatrice(){
    return matrice;
  }
  /*
   * permets de savoir si la matrice est de taille 3x3 ou 5x5
   * @return un boolean (true si la matrice est de taille 3x3 ou false si la matrice est de taille 5x5)
   */
  public boolean getMatriceLength(){
    return matriceLength;
  }

  /*
   * permets de construire les boites de textes ainsi que d'y associer les valeurs dans une matrice
  */
  public void build(){

    int x = 30;
    int y = 200;
    int width=20;
    int height=20;
    int space=30;

    if(matriceLength){ //Si la matrice est 3x3
      
      unbuild5();
      build3(x,y,width,height,space);
    }else{ //Si elle est 5x5
      
      unbuild3();
      build5(x,y,width,height,space);
      
    }
    

  }

  /*
   * Permets d'enlever les boites de textes de la matrice 3x3
   */
  private void unbuild3() {
    for(int i=0;i<text3.length;i++){
      f.remove(text3[i]);
      f.repaint();}

  }
  /*
   * Permets d'enlever les boites de texte de la matrice 5x5
   */
  private void unbuild5() {
    for(int i=0;i<text5.length;i++){
      f.remove(text5[i]);
      f.repaint();
      
      
    }
      
      
      
  }
  /*
   * Permets de construire les boites de texte de la matrice 3x3
   * @param x l'emplacement en abscisse de la premiere boite
   * @param y l'emplacement en ordonnée de la premiere boite
   * @param width la largeur de la boite
   * @param height la hauteur de la boite
   * @param space la distance entre les boites
   */
  public void build3(int x,int y,int width,int height,int space){


    //Création de la matrice 3x3
    for(int i=0;i<text3.length;i++){ //On parcours le tableau et on le remplit de JTextField
      text3[i]=new JTextField("0");
    }


    //On set up les coordonnées pour la matrice 3x3
    for(int i=0;i<text3.length;i++){
      if(i<3){ //Si on est dans la ligne 1
        text3[i].setBounds(x+i*space,y,width,height);
      }else if(i<6){ // Si on est dans la ligne 2,on descends
        text3[i].setBounds(x+(i-3)*space,y+1*space,width,height);
      }else{ //Si on est dans la ligne 3, on descends
        text3[i].setBounds(x+(i-6)*space,y+2*space,width,height);
      }
    }
      
      
    //On ajoute dans la frame la matrice 3x3
    for(int i=0;i<text3.length;i++){
      f.add(text3[i]);
    }



    
    
  }
  /*
   * Permets de construire les boites de texte de la matrice 3x3
   * @param x l'emplacement en abscisse de la premiere boite
   * @param y l'emplacement en ordonnée de la premiere boite
   * @param width la largeur de la boite
   * @param height la hauteur de la boite
   * @param space la distance entre les boites
   */
  public void build5(int x,int y,int width,int height,int space){

    //Création de la matrice 5x5
    for(int i=0;i<text5.length;i++){ //On parcours le tableau et on le remplit de JTextField
      text5[i]=new JTextField("0");
    }

    //On set up les coordonnées pour la matrice  5x5
    for(int i=0;i<text5.length;i++){
      if(i<5){
        text5[i].setBounds(x+i*space,y,width,height);
      }else if(i<10){
        text5[i].setBounds(x+(i-5)*space,y+1*space,width,height);
      }else if(i<15){
        text5[i].setBounds(x+(i-10)*space,y+2*space,width,height);
      }else if(i<20){
        text5[i].setBounds(x+(i-15)*space,y+3*space,width,height);
      }else{
        text5[i].setBounds(x+(i-20)*space,y+4*space,width,height);
      }
    }

    
    //On ajoute dans la frame la matrice 5x5

    for(int i=0;i<text5.length;i++){
      f.add(text5[i]);
    }


  }

  /*
   * Permets de vérifier que la matrice de convolution ne contient que des entiers
   * @throws Exception si la valeur rentré n'est pas un entier
   */
  public Boolean verif(){


    try{
      int counter=0;

      //On verifie que la matrice 3x3 est pas buggé
      if(matriceLength){
        for(int i=0;i<3;i++){
          for(int j=0;j<3;j++){
            
            matriceC3[i][j]=Integer.parseInt(text3[counter].getText());
            counter+=1;
          }
        }
        counter=0;
      }else{
        //On vérifie que la matrice 5x5 est pas buggé
        counter=0;
        for(int i=0;i<5;i++){
          for(int j=0;j<5;j++){
            
            matriceC5[i][j]=Integer.parseInt(text5[counter].getText());
            counter+=1;
          }
        }
        counter=0;

      }


        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        return false;
    }
    return true;
  }

  


  /*
   * associe la matrice de la bonne taille à l'attribut matrice
   * @param e l'action qui a déclenché cet méthode
   */
  public void actionPerformed(ActionEvent e) 
  {
    if (verif()){
        int max=3;
        if (!matriceLength)max=5;
        matrice=new int[max][max];
            if(max==3){
              for(int j=0;j<max;j++){
                for(int i=0;i<max;i++){
                    matrice[j][i]=matriceC3[j][i];
                    System.out.println(matrice[j][i]);
                }
            }
          }else{
            for(int j=0;j<max;j++){
              for(int i=0;i<max;i++){
                  matrice[j][i]=matriceC5[j][i];
                  System.out.println(matrice[j][i]);
              }
          }
          }
            
        
        
        }
    }
  }
    