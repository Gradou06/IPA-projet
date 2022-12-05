import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import javax.media.jai.JAI;
import javax.media.jai.RasterFactory;

import java.awt.Point;
import java.awt.color.ColorSpace;
import javax.media.jai.RenderedOp;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

public class Traitement {
    private String imgName;
    private byte[] tabGrey;
    private int[] tabRGB;
    private int width;
    private int height;
    private Boolean isRGB;
    private int[] tabIntGrey;

    public void setImgName(String imgName) {
        this.imgName = imgName;
        this.isRGB=this.isRGB();
            }
    
    public void settabRgb(int[] tabRGB) {this.tabRGB = tabRGB;}
    public byte[] getTabGrey(){return this.tabGrey;}
    public int[] getTabRGB(){return this.tabRGB;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public Boolean getIsRGB(){return this.isRGB;}
    public int[] getTabIntGrey(){return this.tabIntGrey;}
    
    
    public void imgRBGtoPixelTab(){
        RenderedOp ropimage; 
        ropimage = JAI.create("fileload", imgName);
        this.width = ropimage.getWidth();
        this.height = ropimage.getHeight();
        BufferedImage bi = ropimage.getAsBufferedImage();
        int[] px2;
        px2 = bi.getRGB(0,0,this.width,this.height,null,0,this.width);
        this.tabRGB=px2;
    }

    public void imgGreytoPixelTab(){
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
        
        public Boolean isRGB(){
        RenderedOp ropimage;
        ropimage = JAI.create("fileload", this.imgName);
        ColorModel cm = ropimage.getColorModel();
        if (cm.getColorSpace().getType() == ColorSpace.TYPE_RGB) {
            return true;
        }
        return false;
            }

        public void saveGreyImg(){
            int IMG_WIDTH = this.width;
            int IMG_HEIGHT = this.height;
            SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_BYTE,IMG_WIDTH,IMG_HEIGHT,1);
            BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
            image.setData(Raster.createRaster(sm, new DataBufferByte(this.tabGrey, this.tabGrey.length), new Point()));
            JAI.create("filestore",image,"res-gs.png","PNG");
                    }
        
        public void reverseGreyImg(){
            byte[] reversed = new byte[this.tabGrey.length];
            for (int j=0;j<this.height;j++){
                for (int i=this.width-1; i>0; i--){
                    reversed[(j*this.width)+this.width-i]=this.tabGrey[(j*this.width)+i];
                    }
                }
            this.tabGrey=reversed;
            }
            
        public void reverseRGBImg(){
            int[] reversed = new int[this.tabRGB.length];
            for (int j=0;j<this.height;j++){
                for (int i=this.width-1; i>0; i--){
                    reversed[(j*this.width)+this.width-i]=this.tabRGB[(j*this.width)+i];
                    }
                }
            this.tabRGB=reversed;
            }

        public void saveRGBImg(){
            DataBufferInt dataBuffer = new DataBufferInt(this.tabRGB, this.tabRGB.length);
            ColorModel colorModel = new DirectColorModel(32,0xFF0000,0xFF00,0xFF,0xFF000000);
            WritableRaster raster = Raster.createPackedRaster(dataBuffer, this.width, this.height, this.width,
            ((DirectColorModel) colorModel).getMasks(), null);
            BufferedImage image = new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
            JAI.create("filestore", image, "res.png", "png");
        }

        public void changeRGBtoGrey(){
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
            }

        public void changeGreyContraste(int p){ // généralement p=255
            byte grey;
            for (int i=0; i<this.tabGrey.length; i++){
                grey = (byte)(p-this.tabGrey[i]);
                this.tabGrey[i]= grey;
                }
            }
        
        public void changeRGBContraste(int p){ // généralement p=255
            int red;
            int green;
            int blue;
            int alpha;
            for (int i=0; i<this.tabRGB.length; i++){
                red = (this.tabRGB[i] >> 16) & 0xFF;
                green = (this.tabRGB[i] >> 8) & 0xFF;
                blue = this.tabRGB[i] & 0xFF;
                alpha = (this.tabRGB[i] >> 24) & 0xFF;
                red = p-red;
                green = p-green;
                blue = p-blue;
                this.tabRGB[i]= (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
            }

        public void changeRGBAssombrissement(){
            int red;
            int green;
            int blue;
            int alpha;
            for (int i=0; i<this.tabRGB.length; i++){
                red = (this.tabRGB[i] >> 16) & 0xFF;
                green = (this.tabRGB[i] >> 8) & 0xFF;
                blue = this.tabRGB[i] & 0xFF;
                alpha = (this.tabRGB[i] >> 24) & 0xFF;
                red = red*red/255;
                green = green*green/255;
                blue = blue*blue/255;
                this.tabRGB[i]= (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
            }

        public void changeGreyAssombrissement(){
            this.tabIntGrey = this.normalize(tabGrey);
            for (int i=0; i<this.tabGrey.length; i++){
                this.tabIntGrey[i] =(this.tabIntGrey[i]*this.tabIntGrey[i])/255;   
                }
                this.tabGrey=this.unNormalize(this.tabIntGrey);
            }

        

        public void changeRGBEclairage(){
            int red;
            int green;
            int blue;
            int alpha;
            for (int i=0; i<this.tabRGB.length; i++){
                red = (this.tabRGB[i] >> 16) & 0xFF;
                green = (this.tabRGB[i] >> 8) & 0xFF;
                blue = this.tabRGB[i] & 0xFF;
                alpha = (this.tabRGB[i] >> 24) & 0xFF;
                red =(int)(Math.sqrt(red)*Math.sqrt(255));
                green =(int)(Math.sqrt(green)*Math.sqrt(255));
                blue =(int)(Math.sqrt(blue)*Math.sqrt(255));
                this.tabRGB[i]= (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
        }

        public void changeGreyEclairage(){
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

        public void changeGreytoRGB(){
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
        }
        

        
    
        