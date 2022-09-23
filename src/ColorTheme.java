
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.UIManager;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author beamj
 */
public class ColorTheme{
    
    private Color main;
    private Color sub;
    private String nimbusName;
    
    public ColorTheme(Color main, Color sub) {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                nimbusName = info.getClassName();
            }
        }
        this.main = main;
        this.sub = sub;
    }

    public Color getMain() {
        return main;
    }

    public Color getSub() {
        return sub;
    }
    
    public void setTheme(Container c){
        st(c);
        UIManager.put("nimbusFocus", main);
        UIManager.put("nimbusBase", main);
    }
    
    public void setNimbus(){
        try {
            UIManager.setLookAndFeel(nimbusName);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private Color darker(Color c){
        return new Color(Math.max(0, c.getRed() - 30), Math.max(0, c.getGreen() - 30), Math.max(0, c.getBlue() - 30));
    }
    
    private ArrayList<Component> st(Container c){
        Component[] comps = c.getComponents();
        ArrayList<Component> compList = new ArrayList<>();
        for(Component comp : comps) {
            compList.add(comp);
            comp.setBackground(main);
            comp.setForeground(sub);
            if(comp instanceof Container) {
                compList.addAll(st((Container)comp));
            }
        }
        return compList;
    }
    
    @Override
    public String toString() {
        return main.getRGB() + " " + sub.getRGB();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ColorTheme)){
            return false;
        }
        ColorTheme ct = (ColorTheme)obj;
        return (main.equals(ct.main) && sub.equals(ct.sub));
    }
    
    public static ColorTheme convert(String line){
        if(line.equals("Blue-Yellow")){
            return new BlueYellowTheme();
        } else if(line.equals("DarkGrey-LightGrey")) {
            return new DarkLightGreyTheme();
        } else if(line.equals("Grey-Yellow")){
            return new GreyYellowTheme();
        } else if(line.equals("Pink-Green")){
            return new PinkGreenTheme();
        } else if(line.equals("Pink-Orange")){
            return new PinkOrangeTheme();
        } else if(line.equals("Purple-Orange")){
            return new PurpleOrangeTheme();
        } else if(line.equals("Purple-Yellow")){
            return new PurpleYellowTheme();
        } else if(line.equals("Red-Teal")){
            return new RedTealTheme();
        } else if(line.equals("Red-Yellow")){
            return new RedYellowTheme();
        } else if(line.equals("Teal-Blue")){
            return new TealBlueTheme();
        } else if(line.equals("Green-Black")){
            return new GreenBlackTheme();
        } else {
            try(Scanner in = new Scanner(line);){
                return new ColorTheme(new Color(in.nextInt()), new Color(in.nextInt()));
            } catch (Exception e){
                return null;
            }
        }
    }
}