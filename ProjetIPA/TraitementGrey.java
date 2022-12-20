import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.media.jai.JAI;
import javax.media.jai.RasterFactory;

import java.awt.Point;
import javax.media.jai.RenderedOp;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;

public class TraitementGrey extends Traitement {

    protected byte[][] tabGreyBack= new byte[3][];

    public TraitementGrey(String imgName) {
        super(imgName);
    }

    public TraitementGrey(byte[] tabGrey, int width, int height) {
        super(tabGrey, width, height);
    }

    public byte[][] getTabGreyBack(){
        return this.tabGreyBack;
    }

    public int[][] getTabRGBBack(){
        return null;
    }


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

    public void saveImg(String name){
        int IMG_WIDTH = this.width;
        int IMG_HEIGHT = this.height;
        SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_BYTE,IMG_WIDTH,IMG_HEIGHT,1);
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        image.setData(Raster.createRaster(sm, new DataBufferByte(this.tabGrey, this.tabGrey.length), new Point()));
        JAI.create("filestore",image, name.substring(0,name.length()-4)+"-gs.png","PNG");
        System.out.println(name);
                }
    
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
    public void changeContraste(int p){ // généralement p=255
            byte grey;
            for (int i=0; i<this.tabGrey.length; i++){
                grey = (byte)(p-this.tabGrey[i]);
                this.tabGrey[i]= grey;
                }
                this.newAction();
            }

    public void changeAssombrissement(){
            this.tabIntGrey = this.normalize(tabGrey);
            for (int i=0; i<this.tabGrey.length; i++){
                this.tabIntGrey[i] =(this.tabIntGrey[i]*this.tabIntGrey[i])/255;   
                }
                this.tabGrey=this.unNormalize(this.tabIntGrey);
                this.newAction();
            }

    public void changeEclairage(){
            this.tabIntGrey = this.normalize(tabGrey);
            for (int i=0; i<this.tabGrey.length; i++){
                this.tabIntGrey[i] = (int)(Math.sqrt(this.tabIntGrey[i])*Math.sqrt(255));
            }
            this.tabGrey=this.unNormalize(this.tabIntGrey);
            this.newAction();
        }
    
    public int[] normalize(byte[] tabByte){
            int[] tabInt = new int[tabByte.length];
            for (int i=0;i<tabByte.length;i++){
                tabInt[i]=Byte.toUnsignedInt(tabByte[i]);
            }
            return tabInt;
        }

        public byte[] unNormalize(int[] tabInt){
            byte[] tabByte = new byte[tabInt.length];
            for (int i=0;i<tabInt.length;i++){
                tabByte[i]=(byte)tabInt[i];
            }
            return tabByte;
            
        }

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

    public void changeColor(){ //ILLEGAL
            int red;
            int green;
            int blue;
            int alpha;
            this.tabRGB = new int[this.tabGrey.length];
            for (int i=0; i<this.tabGrey.length; i++){
                red = Byte.toUnsignedInt(this.tabGrey[i]);
                green = Byte.toUnsignedInt(this.tabGrey[i]);
                blue = Byte.toUnsignedInt(this.tabGrey[i]);
                alpha = 255;
                this.tabRGB[i]= (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
                this.isRGB=true;
                this.tabGrey=null;
                this.newAction();
            }

            public void back(){
                this.tabGrey=this.tabGreyBack[1];
                this.tabGreyBack[0]=this.tabGreyBack[1];
                this.tabGreyBack[1]=this.tabGreyBack[2];
                this.tabGreyBack[2]=null;
    
            }
    
            public void newAction(){
    
                this.tabGreyBack[2]=this.tabGreyBack[1];
                this.tabGreyBack[1]=this.tabGreyBack[0];
                this.tabGreyBack[0]=this.tabGrey;
            }

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
