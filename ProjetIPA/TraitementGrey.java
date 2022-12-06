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

    public TraitementGrey(String imgName) {
        super(imgName);
    }

    public void imgToPixelTab(){
        RenderedOp ropimage;
        ropimage = JAI.create("fileload", imgName);
        this.width = ropimage.getWidth();
        this.height = ropimage.getHeight();
        Raster r = ropimage.getData();
        DataBufferByte db = (DataBufferByte)(r.getDataBuffer());
        byte[] px;
        px = db.getData();
        this.tabGrey=px;
    }

    public void saveImg(){
        int IMG_WIDTH = this.width;
        int IMG_HEIGHT = this.height;
        SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_BYTE,IMG_WIDTH,IMG_HEIGHT,1);
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        image.setData(Raster.createRaster(sm, new DataBufferByte(this.tabGrey, this.tabGrey.length), new Point()));
        JAI.create("filestore",image,"res-gs.png","PNG");
                }
    
    public void reverseImg(){
            byte[] reversed = new byte[this.tabGrey.length];
            for (int j=0;j<this.height;j++){
                for (int i=this.width-1; i>0; i--){
                    reversed[(j*this.width)+this.width-i]=this.tabGrey[(j*this.width)+i];
                    }
                }
            this.tabGrey=reversed;
            }
    public void changeContraste(int p){ // généralement p=255
            byte grey;
            for (int i=0; i<this.tabGrey.length; i++){
                grey = (byte)(p-this.tabGrey[i]);
                this.tabGrey[i]= grey;
                }
            }

    public void changeAssombrissement(){
            this.tabIntGrey = this.normalize(tabGrey);
            for (int i=0; i<this.tabGrey.length; i++){
                this.tabIntGrey[i] =(this.tabIntGrey[i]*this.tabIntGrey[i])/255;   
                }
                this.tabGrey=this.unNormalize(this.tabIntGrey);
            }

    public void changeEclairage(){
            this.tabIntGrey = this.normalize(tabGrey);
            for (int i=0; i<this.tabGrey.length; i++){
                this.tabIntGrey[i] = (int)(Math.sqrt(this.tabIntGrey[i])*Math.sqrt(255));
            }
            this.tabGrey=this.unNormalize(this.tabIntGrey);
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
            for (int j=0;j<this.height;j++){
                for (int i=0; i<this.width; i++){
                    reversed[(j*this.width)+i]=this.tabGrey[((this.height-j-1)*this.width)+i];
                    }
                }
            this.tabGrey=reversed;
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
            }

    public void back(){}
}
