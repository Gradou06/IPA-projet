public abstract class Traitement {
    protected String imgName;
    protected byte[] tabGrey;
    protected int[] tabRGB;
    protected int width;
    protected int height;
    protected Boolean isRGB;
    protected int[] tabIntGrey;
    

    public Traitement(String imgName) {
        this.imgName = imgName;
    }

    public Traitement(byte[] tabGrey, int width, int height) {
        this.tabGrey = tabGrey;
        this.width = width;
        this.height = height;
    }

    public Traitement(){}

    



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
}