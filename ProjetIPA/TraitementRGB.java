import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;

public class TraitementRGB extends Traitement{

    protected int[][] tabRGBBack= new int[3][];

    public TraitementRGB(String imgName) {
        super(imgName);
    }

    public void imgToPixelTab(){
        System.out.println("RGB");
        RenderedOp ropimage; 
        ropimage = JAI.create("fileload", imgName);
        this.width = ropimage.getWidth();
        this.height = ropimage.getHeight();
        BufferedImage bi = ropimage.getAsBufferedImage();
        int[] px2;
        px2 = bi.getRGB(0,0,this.width,this.height,null,0,this.width);
        this.tabRGB=px2;
        this.tabRGBBack[0]=px2;
    }


    public void reverseImg(){
        int[] reversed = new int[this.tabRGB.length];
        for (int j=0;j<this.height;j++){
            for (int i=this.width-1; i>0; i--){
                reversed[(j*this.width)+this.width-i]=this.tabRGB[(j*this.width)+i];
                }
            }
        this.tabRGB=reversed;
        this.newAction();
        }

    public void reverseHautBas(){
        int[] reversed = new int[this.tabRGB.length];
        for (int j=this.height-1;j>0;j--){
            for (int i=0; i<this.width; i++){
                reversed[((this.height-j)*this.width)+i]=this.tabRGB[(j*this.width)+i];
                }
            }
        this.tabRGB=reversed;
        this.newAction();
        }

        public void saveImg(){
            DataBufferInt dataBuffer = new DataBufferInt(this.tabRGB, this.tabRGB.length);
            ColorModel colorModel = new DirectColorModel(32,0xFF0000,0xFF00,0xFF,0xFF000000);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, this.width, this.height, this.width,
            ((DirectColorModel) colorModel).getMasks(), null);
            BufferedImage image = new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
            JAI.create("filestore", image, "res.png", "png");
        }

        public void changeColor(){
            int red;
            int green;
            int blue;
            byte grey;
            this.tabGrey = new byte[this.tabRGB.length];
            for (int i=0; i<this.tabRGB.length; i++){
                red = (this.tabRGB[i] >> 16) & 0xFF;
                green = (this.tabRGB[i] >> 8) & 0xFF;
                blue = this.tabRGB[i] & 0xFF;
                grey = (byte)(0.21 * red + 0.72 * green + 0.07 * blue);
                this.tabGrey[i]= grey;
                }
                this.isRGB=false;
                this.tabRGB=null;
                this.newAction();
            }
        
        public void changeContraste(int p){ // généralement p=255
            int red;
            int green;
            int blue;
            int alpha;
            int[] tab= new int[this.tabRGB.length];
            for (int i=0; i<this.tabRGB.length; i++){
                red = (this.tabRGB[i] >> 16) & 0xFF;
                green = (this.tabRGB[i] >> 8) & 0xFF;
                blue = this.tabRGB[i] & 0xFF;
                alpha = (this.tabRGB[i] >> 24) & 0xFF;
                red = p-red;
                green = p-green;
                blue = p-blue;
                tab[i]= (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
            this.tabRGB=tab;
            this.newAction();
            }
        
        public void changeAssombrissement(){
            int red;
            int green;
            int blue;
            int alpha;
            int[] tab= new int[this.tabRGB.length];
            for (int i=0; i<this.tabRGB.length; i++){
                red = (this.tabRGB[i] >> 16) & 0xFF;
                green = (this.tabRGB[i] >> 8) & 0xFF;
                blue = this.tabRGB[i] & 0xFF;
                alpha = (this.tabRGB[i] >> 24) & 0xFF;
                red = red*red/255;
                green = green*green/255;
                blue = blue*blue/255;
                tab[i]= (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
            this.tabRGB=tab;
            this.newAction();
            }
        
        public void changeEclairage(){
            int red;
            int green;
            int blue;
            int alpha;
            int[] tab = new int[this.tabRGB.length];
            for (int i=0; i<this.tabRGB.length; i++){
                red = (this.tabRGB[i] >> 16) & 0xFF;
                green = (this.tabRGB[i] >> 8) & 0xFF;
                blue = this.tabRGB[i] & 0xFF;
                alpha = (this.tabRGB[i] >> 24) & 0xFF;
                red =(int)(Math.sqrt(red)*Math.sqrt(255));
                green =(int)(Math.sqrt(green)*Math.sqrt(255));
                blue =(int)(Math.sqrt(blue)*Math.sqrt(255));
                tab[i]= (alpha << 24) | (red << 16) | (green << 8) | blue;
            }
                this.tabRGB= tab;
            
                
            this.newAction();
        }

        public void back(){
            this.tabRGB=this.tabRGBBack[1];
            this.tabRGBBack[0]=this.tabRGBBack[1];
            this.tabRGBBack[1]=this.tabRGBBack[2];
            this.tabRGBBack[2]=null;

        }

        public void newAction(){

            this.tabRGBBack[2]=this.tabRGBBack[1];
            this.tabRGBBack[1]=this.tabRGBBack[0];
            this.tabRGBBack[0]=this.tabRGB;
        }

        public void convolution(int[][] matriceC){
            int[] tab = new int[this.tabRGB.length];
            int x=0;
            int y=0;
            for (int i=0;i<this.tabRGB.length;i++){
                tab[i]=this.convol(x,y,matriceC);
                x++;
                if (x==this.width){
                    x=0;
                    y++;
                }
            }
            this.tabRGB=tab;
            this.newAction();
        }

        public int convol(int x, int y, int[][] matriceC){
            int red=0;
            int green=0;
            int blue=0;
            int alpha=0;
            int nb=0;
        for (int j=-(matriceC.length-1)/2;j<(matriceC.length-1)/2+1;j++){
            for (int i=-(matriceC.length-1)/2;i<(matriceC.length-1)/2+1;i++){
                if (!(x+i<0 || x+i>=this.width || y+j<0 || y+j>=this.height || matriceC[j+(matriceC.length-1)/2][i+(matriceC.length-1)/2]==0)){
                    red+= ((this.tabRGB[(y+j)*this.width+(x+i)] >> 16) & 0xFF)*matriceC[j+(matriceC.length-1)/2][i+(matriceC.length-1)/2];
                    green+= ((this.tabRGB[(y+j)*this.width+(x+i)] >> 8) & 0xFF)*matriceC[j+(matriceC.length-1)/2][i+(matriceC.length-1)/2];
                    blue+= (this.tabRGB[(y+j)*this.width+(x+i)] & 0xFF)*matriceC[j+(matriceC.length-1)/2][i+(matriceC.length-1)/2];
                    alpha+= ((this.tabRGB[(y+j)*this.width+(x+i)] >> 24) & 0xFF)*matriceC[j+(matriceC.length-1)/2][i+(matriceC.length-1)/2];
                    nb++;
                }
        
        }
    }
    if (nb!=0){
    red=red/nb;
    green=green/nb;
    blue=blue/nb;
    alpha=alpha/nb;
    }
    else {
        return this.tabRGB[y*this.width+x];
    }
    return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public int[] normalize(byte[] tabByte){return (new int[0]);}


        
            
    
}
