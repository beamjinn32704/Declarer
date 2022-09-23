/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author beamj
 */
public class Util {
    public static int objectExists(Object[] o, Object obj){
        for(int i = 0; i < o.length; i++){
            if(o[i] == obj){
                return i;
            }
        }
        return -1;
    }
    
    public static int objectExists(Object[] o, Object obj, boolean rel){
        for(int i = 0; i < o.length; i++){
            if(rel){
                if(o[i].equals(obj)){
                    return i;
                }
            } else {
                if(o[i] == obj){
                    return i;
                }
            }
        }
        return -1;
    }
}
