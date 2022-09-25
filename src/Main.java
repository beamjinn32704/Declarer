
import java.io.File;
import java.io.FileNotFoundException;
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
public class Main {
    
    public static MainFrame main;
    
    public static void main(String[] args) throws FileNotFoundException {

        File ctheme = new File("ct.dll");
        if(ctheme.isFile()){
            try(Scanner in = new Scanner(ctheme)){
                main = new MainFrame(ColorTheme.convert(in.nextLine()));
            } catch(Exception e){
                main = new MainFrame(new DarkLightGreyTheme());
            }
        } else {
            main = new MainFrame(new GreenBlackTheme());
        }
        
        main.setVisible(true);
    }
    
    public static void restart(ColorTheme t){
        System.out.println("RESTART");
        main.setVisible(false);
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Metal".equals(info.getName())) {
            	try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
        main = new MainFrame(t);
        main.setVisible(true);
    }
    
}
