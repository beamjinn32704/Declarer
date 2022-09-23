
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author beamj
 */
public class Declaration {
    private String message;
    private Time time;
    private boolean oneTime;
    private JFrame jf;
    
    public Declaration(String m, Time t, boolean oT){
        message = m;
        time = t;
        oneTime = oT;
        jf = new JFrame();
    }
    
    public String message(){
        return message;
    }
    
    public Time time(){
        return time;
    }
    
    public boolean oneTime(){
        return oneTime;
    }
    
    public void go(){
        System.out.println("GO");
        g();
    }
    
    private void g(){
        System.out.println("g");
        Main.main.clock.setDec(this);
        Main.main.revalidate();
        Main.main.repaint();
    }
    
    public void timeDone(boolean show){
        if(show){
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, message, "Declaration", JOptionPane.PLAIN_MESSAGE);
            jf.setAlwaysOnTop(false);
            if(!oneTime){
                go();
            } else {
                Main.main.clock.setDead();
            }
        } else {
            Main.main.clock.setDead();
        }
        
    }
    
    public void setTime(Time t){
        time = t.copy();
    }
}