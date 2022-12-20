/*
 * Cette classe permets de traiter une image
 * imgName est le nom de l'image
 * tabGrey est un tableau contenant la valeur de chaque pixel en Byte d'une image en gris
 * tabRGB est un tableau contenant la valeur de chaque pixel d'une image en couleur
 * width la taille de l'image et height sa hauteur
 * isRGB désigne si l'image est en couleur ou en gris
 * tabIntGrey est un tableau contenant la valeur de chaque pixel en int d'une image en gris
 */
public abstract class Traitement {
    protected String imgName;
    protected byte[] tabGrey;
    protected int[] tabRGB;
    protected int width;
    protected int height;
    protected Boolean isRGB;
    protected int[] tabIntGrey;
    
    /*
     * Associe l'image que l'on veut traiter à la classe
     * @param imgName le nom de l'image que l'on veut traiter
     */
    public Traitement(String imgName) {
        this.imgName = imgName;
    }

    /*
     * Associe l'image que l'on veut traiter à la classe
     * @param tabGrey un tableau de byte
     * @param la largeur de l'image
     * @param la hauteur de l'image
     */
    public Traitement(byte[] tabGrey, int width, int height) {
        this.tabGrey = tabGrey;
        this.width = width;
        this.height = height;
    }

    public Traitement(){}

    


    /*
     * permets de changer l'image que l'on veut traiter
     * @param imgName le nom de l'image que l'on veut traiter
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
        this.imgToPixelTab();
            }
    
    public void changeImgName(String name){this.imgName=name;}
    public void settabRgb(int[] tabRGB) {this.tabRGB = tabRGB;}
    public byte[] getTabGrey(){return this.tabGrey;}
    public int[] getTabRGB(){return this.tabRGB;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public Boolean getIsRGB(){return this.isRGB;}
    public int[] getTabIntGrey(){return this.tabIntGrey;}
    
    public abstract void back();
    public abstract void imgToPixelTab();
    public abstract void changeColor();
    public abstract void saveImg(String name);
    public abstract void reverseImg();
    public abstract void reverseHautBas();
    public abstract void changeAssombrissement();
    public abstract void changeEclairage();
    public abstract void changeContraste(int p);
    public abstract void convolution(int[][] matriceC);
    public abstract int[] normalize(byte[] tabByte);
    public abstract byte[][] getTabGreyBack();
    public abstract int[][] getTabRGBBack();
}