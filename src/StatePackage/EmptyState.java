/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatePackage;

import javax.swing.JButton;

/**
 *
 * @author Mohamed Elkalaf
 */
public class EmptyState implements State {

    private static EmptyState emptystate;
    private EmptyState(){}
    public static EmptyState start()
    {
        if(emptystate==null)
            emptystate=new EmptyState();
        
        return emptystate;
    }
    
    
    @Override
    public void setIcon(JButton button) {
        button.setIcon(null);
    }
    

    
    
}
