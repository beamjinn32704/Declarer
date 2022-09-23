
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author beamj
 */
public class ColorThemeBoxModel implements ComboBoxModel {
    
    ColorTheme[] themes;
    
    int selectedInd;

    public ColorThemeBoxModel() {
        selectedInd = 0;
        themes = new ColorTheme[]{new GreenBlackTheme(), new RedTealTheme(), new RedYellowTheme(), new BlueYellowTheme(),
        new TealBlueTheme(), new PurpleYellowTheme(), new PurpleOrangeTheme(), new DarkLightGreyTheme(),
        new PinkOrangeTheme(), new GreyYellowTheme(), new PinkGreenTheme()};
    }
    
    public boolean contains(ColorTheme t){
        return Util.objectExists(themes, t) != -1;
    }
    
    public boolean contains(ColorTheme t, boolean rel){
        return Util.objectExists(themes, t, rel) != -1;
    }
    
    public ColorTheme relObj(ColorTheme t){
        return themes[indexOf(t, true)];
    }
    
    public int indexOf(ColorTheme t){
        return Util.objectExists(themes, t);
    }
    
    public int indexOf(ColorTheme t, boolean rel){
        int j = Util.objectExists(themes, t, rel);
        System.out.println(j);
        return j;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        int ind = Util.objectExists(themes, anItem);
        if(ind != -1){
            selectedInd = ind;
        }
    }

    @Override
    public Object getSelectedItem() {
        return themes[selectedInd];
    }

    @Override
    public int getSize() {
        return themes.length;
    }

    @Override
    public Object getElementAt(int index) {
        if(index >= 0 && index < themes.length){
            return themes[index];
        } else {
            return null;
        }
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
    
}
