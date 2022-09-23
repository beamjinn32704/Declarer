
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
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
    private static String shortcutParent = "C:\\users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup";
    private static String lnkName = "Declarer3.0";
    private static String lnk;
    private static String[] pastVers = new String[] { "Declarer.lnk", "Declarer2.0.lnk", "Declarer2.5.lnk", "Declarer2.7.lnk"};
    
    public static void main(String[] args) throws FileNotFoundException {
        shortcut();
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
    
    private static void shortcut(){
        delPastVers();
        lnk = lnkName + ".lnk";
        if(!(new File(shortcutParent, lnk)).exists()) {
            File batch = new File("Declarer.bat");
            try (PrintWriter writer = new PrintWriter(batch);){
                writer.println("@echo off");
                writer.println("set startupPath=\"c:\\users\\%username%\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\n"
                        + "set exePath=\"%CD%\n"
                        + "cd %startupPath%\n"
                        + "echo Set oWS = WScript.CreateObject(\"WScript.Shell\") > %startupPath%\\CreateShortcut.vbs\n" +
                        "echo sLinkFile = \"" + lnk + "\" >> %startupPath%\\CreateShortcut.vbs\n" +
                        "echo Set oLink = oWS.CreateShortcut(sLinkFile) >> %startupPath%\\CreateShortcut.vbs\n" +
                        "echo oLink.TargetPath = %exePath%\\Declarer.exe\" >> %startupPath%\\CreateShortcut.vbs\n" +
                        "echo oLink.WorkingDirectory = %startupPath%\" >> %startupPath%\\CreateShortcut.vbs\n" +
                        "echo oLink.Description = \"" + lnkName + "\" >> %startupPath%\\CreateShortcut.vbs\n" +
                        "echo oLink.Save >> %startupPath%\\CreateShortcut.vbs\n"
                        + "cd %startupPath%\n" +
                        "C:\\Windows\\System32\\cscript.exe %startupPath%\\CreateShortcut.vbs\n" +
                        "del CreateShortcut.vbs\n"
                        + "echo %CD%\n"
                        + "start " + lnk +"\n"
                        + "cd %exePath%\n" +
                        "(goto) 2>nul & del \"%~f0\" & exit /b");
                Desktop.getDesktop().open(batch);
            } catch (Exception e){
                boolean fail = false;
                if(batch.exists()){
                    try {
                        Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", batch.toString()});
                    } catch(Exception ex){
                        fail = true;
                        JOptionPane.showMessageDialog(null, "Unable to set to open when computer starts!");
                    }
                } else {
                    fail = true;
                    JOptionPane.showMessageDialog(null, "Unable to set to open when computer starts!");
                }
                if(fail){
                    batch.delete();
                }
            }
        }
    }
    
    private static void delPastVers() {
        for(int i = 0; i < pastVers.length; i++) {
        	File file = new File(shortcutParent, pastVers[i]);
        	if(file.isFile()) {
        		file.delete();
        	}
        }
    }
}
