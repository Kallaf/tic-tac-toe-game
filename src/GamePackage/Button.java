/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePackage;

import StatePackage.*;
import javax.swing.JButton;

/**
 *
 * @author Mohamed Elkalaf
 */
public class Button{
    public JButton button;
    private int Priorty=0;
    private State state ;
    
    public void setState(State state)
    {
        this.state=state;
        state.setIcon(this.button);
    }
    
    public State getState()
    {
        return this.state;
    }
    
    public void resetPriorty()
    {
        Priorty=0;
    }
    
    public void increasePriorty()
    {
        Priorty++;
    }
    
    
    public int getPriorty()
    {
        return Priorty;
    }
    
}
