import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.media.jai.JAI;
import javax.media.jai.RasterFactory;
import java.awt.Point;
import javax.media.jai.RenderedOp;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;

/*
 * La classe permets de traiter les images en gris
 * Elle hérite de la classe Traitement
 * tabGreyBack est le tableau contenant la valeur de chaque  pixel
 */
public class TraitementGrey extends Traitement {

    protected byte[][] tabGreyBack= new byte[3][];

    public TraitementGrey(String imgName) {
        super(imgName);
    }

    public TraitementGrey(byte[] tabGrey, int width, int height) {
        super(tabGrey, width, height);
    }

    /*
     * @return l'attribut contenant la valeur de chaque pixel
     */
    public byte[][] getTabGreyBack(){
        return this.tabGreyBack;
    }

    /*
     * @return la valeur de chaque pixel avec les couleurs (donc null dans ce cas puisque l'image est grise)
     */
    public int[][] getTabRGBBack(){
        return null;
    }

    /**
     * Transforme une image en un tableau de pixel
     */
    public void imgToPixelTab(){
        System.out.println("Grey");
        RenderedOp ropimage;
        ropimage = JAI.create("fileload", imgName);
        this.width = ropimage.getWidth();
        this.height = ropimage.getHeight();
        Raster r = ropimage.getData();
        DataBufferByte db = (DataBufferByte)(r.getDataBuffer());
        byte[] px;
        px = db.getData();
        this.tabGrey=px;
        this.tabGreyBack[0]=px;
    }
        /*
         * permets de sauvegarder une image
         * @param name le nom de l'image que l'on veut sauvegarder
         */
    public void saveImg(String name){
        int IMG_WIDTH = this.width;
        int IMG_HEIGHT = this.height;
        SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_BYTE,IMG_WIDTH,IMG_HEIGHT,1);
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        image.setData(Raster.createRaster(sm, new DataBufferByte(this.tabGrey, this.tabGrey.length), new Point()));
        JAI.create("filestore",image, name.substring(0,name.length()-4)+"-gs.png","PNG");
        System.out.println(name);
                }
    /*
     * Permets de retourner l'image de gauche à droite
     */
    public void reverseImg(){
            byte[] reversed = new byte[this.tabGrey.length];
            for (int j=0;j<this.height;j++){
                reversed[(j*this.width)]=this.tabGrey[(j*this.width)+this.width-1];
                for (int i=this.width-1; i>0; i--){
                    reversed[(j*this.width)+this.width-i]=this.tabGrey[(j*this.width)+i];
                    }
                }
            this.tabGrey=reversed;
            this.newAction();
            }

    /*
     * Permets d'appliquer un contraste sur l'image
     * @param p désigne la valeur de contraste de l'image (255 contraste l'image à 100%)
     */
    public void changeContraste(int p){ // généralement p=255
            byte grey;
            for (int i=0; i<this.tabGrey.length; i++){
                grey = (byte)(p-this.tabGrey[i]);
                this.tabGrey[i]= grey;
                }
                this.newAction();
            }
            /*
             * Permets d'assombrir l'image
             */
    public void changeAssombrissement(){
            this.tabIntGrey = this.normalize(tabGrey);
            for (int i=0; i<this.tabGrey.length; i++){
                this.tabIntGrey[i] =(this.tabIntGrey[i]*this.tabIntGrey[i])/255;   
                }
                this.tabGrey=this.unNormalize(this.tabIntGrey);
                this.newAction();
            }
            /*
             * Permets d'éclairer l'image
             */
    public void changeEclairage(){
            this.tabIntGrey = this.normalize(tabGrey);
            for (int i=0; i<this.tabGrey.length; i++){
                this.tabIntGrey[i] = (int)(Math.sqrt(this.tabIntGrey[i])*Math.sqrt(255));
            }
            this.tabGrey=this.unNormalize(this.tabIntGrey);
            this.newAction();
        }
        
    /*
     * Permets de transformer un tableau de byte en un tableau de int
     * @param tabByte un tableau contenant des Byte
     * @return le tableau d'entier contenant les valeurs du tableau de byte
     */
    public int[] normalize(byte[] tabByte){
            int[] tabInt = new int[tabByte.length];
            for (int i=0;i<tabByte.length;i++){
                tabInt[i]=Byte.toUnsignedInt(tabByte[i]);
            }
            return tabInt;
        }

        /*
         * Permets de transformer un tableau d'entier en un tableau de byte
         * @param tabInt un tableau contenant des entiers
         * @return le tableau de byte contenant les valeurs du tableau d'entier
         */
        public byte[] unNormalize(int[] tabInt){
            byte[] tabByte = new byte[tabInt.length];
            for (int i=0;i<tabInt.length;i++){
                tabByte[i]=(byte)tabInt[i];
            }
            return tabByte;
            
        }
    /*
     * permets de retourner l'image de haut en bas
     */
        public void reverseHautBas(){
            byte[] reversed = new byte[this.tabGrey.length];
            for (int i=0; i<this.width;i++){
                reversed[i]=this.tabGrey[this.width*(this.height-1)+i];
            }
            for (int j=0;j<this.height;j++){
                for (int i=0; i<this.width; i++){
                    reversed[(j*this.width)+i]=this.tabGrey[((this.height-j-1)*this.width)+i];
                    }
                }
            this.tabGrey=reversed;
            this.newAction();
            }

    public void changeColor(){}
        /*
         * Permets de récuperer l'image d'avant l'action précédente
         */
            public void back(){
                this.tabGrey=this.tabGreyBack[1];
                this.tabGreyBack[0]=this.tabGreyBack[1];
                this.tabGreyBack[1]=this.tabGreyBack[2];
                this.tabGreyBack[2]=null;
    
            }
        /*
         * Garde en mémoire les 2 dernieres images pour pouvoir retourner en arriere plus tard
         */
            public void newAction(){
    
                this.tabGreyBack[2]=this.tabGreyBack[1];
                this.tabGreyBack[1]=this.tabGreyBack[0];
                this.tabGreyBack[0]=this.tabGrey;
            }
        /*
         * Permets d'appliquer une matrice de convolution sur l'image
         * @param matriceC la matrice de convolution que l'on va appliquer sur l'image
         */
    public void convolution(int[][] matriceC){
        byte[] tab = new byte[this.tabGrey.length];
        int x=0;
        int y=0;
        for (int i=0;i<this.tabGrey.length;i++){
            tab[i]=this.convol(x,y,matriceC);
            x++;
            if (x==this.width){
                x=0;
                y++;
            }
        }
        this.tabGrey=tab;
        this.newAction();
    }
        /*
         * Calcule chaque pixel du tableau de pixel
         * @param x la colonne sur laquelle on se trouve
         * @param y la ligne sur laquelle on se trouve
         * @return le pixel a mettre dans le tableau
         */
    public byte convol(int x, int y, int[][] matriceC){
        int px=0;
        int nb=0;
    for (int j=-(matriceC.length-1)/2;j<(matriceC.length-1)/2+1;j++){
        for (int i=-(matriceC.length-1)/2;i<(matriceC.length-1)/2+1;i++){
            if (!(x+i<0 || x+i>=this.width || y+j<0 || y+j>=this.height || matriceC[j+(matriceC.length-1)/2][i+(matriceC.length-1)/2]==0)){
                px+=Byte.toUnsignedInt(this.tabGrey[(y+j)*this.width+(x+i)])*matriceC[j+(matriceC.length-1)/2][i+(matriceC.length-1)/2];
                nb++;
            }
    
    }
}
if (nb!=0){
    px=px/nb;
}
else {
    return this.tabGrey[y*this.width+x];
}
return (byte)px;
}
}
