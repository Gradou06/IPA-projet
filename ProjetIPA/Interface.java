import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Interface extends JPanel{
    private int width;
    private int[] tab;
    private byte[] tabG;
    private String type="rgb";
    private BufferedWriter bw;
    private Path path;
    

    public Interface(int width, int[] tab){
        this.tab = tab;
        this.width=width;
        path = Paths.get("test.csv");
        try{
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            else {
                Files.delete(path);
                Files.createFile(path);
            }
            bw= Files.newBufferedWriter(path);
            bw.write(RGBtoExcelFormat());
            bw.newLine();
            bw.close();
    }
    catch(Exception e){
        System.out.println("Erreur");
    }
    }

    public void paint(Graphics g) {
        super.paint(g);
        int x=200;
        int y=10;
        if (this.type=="rgb"){
        for (int i=0; i<tab.length; i++){
            g.setColor(new Color((this.tab[i] >> 16) & 0xFF, (this.tab[i] >> 8) & 0xFF, tab[i] & 0xFF));
            g.fillRect(x, y, 1, 1);
            x++;
            if (x==width+200){
                x=200;
                y++;
            }}}
            
            else {
                for (int i=0; i<tabG.length; i++){
                g.setColor(new Color(this.tabG[i] & 0xFF, this.tabG[i] & 0xFF, this.tabG[i] & 0xFF));
                g.fillRect(x, y, 1, 1);
            x++;
            if (x==width+200){
                x=200;
                y++;
            }
        }
    }}

    public void redefine(int width, int[] tab){
        this.tab = tab;
        this.width=width;
        this.type="rgb";
        try{
            Files.delete(path);
            Files.createFile(path);
            bw= Files.newBufferedWriter(path);
            bw.write(RGBtoExcelFormat());
            bw.newLine();
            bw.close();
    }
    catch(Exception e){
        System.out.println("Erreur");
    }
    }
    

    public void redefine(int width, byte[] tab){
        this.tabG = tab;
        this.width=width;
        this.type="grey";
        try{
            Files.delete(path);
            Files.createFile(path);
            bw= Files.newBufferedWriter(path);
            bw.write(GreytoExcelFormat());
            bw.newLine();
            bw.close();
    }
    catch(Exception e){
        System.out.println("Erreur");
    }
}

public String RGBtoExcelFormat(){
    String out="";
    int[] red=new int[256];
    int[] green=new int[256];
    int[] blue=new int[256];
    for (int i=0; i<256; i++){
        red[i]=0;
        green[i]=0;
        blue[i]=0;
    }
    for (int i=0; i<tab.length; i++){
        red[(this.tab[i] >> 16) & 0xFF]++;
        green[(this.tab[i] >> 8) & 0xFF]++;
        blue[tab[i] & 0xFF]++;
    }
    for (int i=0; i<256; i++){
        out+=i+";"+red[i]+";"+green[i]+";"+blue[i]+";\n";
    }
    return out;
}

    public String GreytoExcelFormat(){
        String out="";
        int[] tabInt = new int[tabG.length];
        int[] grey=new int[256];
            for (int i=0;i<tabG.length;i++){
                tabInt[i]=Byte.toUnsignedInt(tabG[i]);
            }
        for (int i=0; i<256; i++){
            grey[i]=0;
        }
        for (int i=0; i<tabG.length; i++){
            grey[tabInt[i]]++;
        }
        for (int i=0; i<256; i++){
            out+=i+";"+grey[i]+";\n";
        }
        return out;
    }

}
    
        
    


    
