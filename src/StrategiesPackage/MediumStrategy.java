/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategiesPackage;

import GamePackage.Button;
import StatePackage.OState;

/**
 *
 * @author Mohamed Elkalaf
 */
public class MediumStrategy implements Strategy {
    
    private static MediumStrategy mediumstrategy;
    private MediumStrategy(){}
    public static MediumStrategy start()
    {
        if(mediumstrategy==null)
            mediumstrategy=new MediumStrategy();
        return mediumstrategy;
    }
    
    @Override
    public void setPriorties(Button[][] buttons)
    {

        OState ostate = OState.start();
        
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                buttons[i][j].resetPriorty();
                    if(ostate.equals(buttons[i][(j+1)%3].getState()))
                        buttons[i][j].increasePriorty();
                    
                    if(ostate.equals(buttons[i][(j+2)%3].getState()))
                        buttons[i][j].increasePriorty();
                    
                    if(ostate.equals(buttons[(i+1)%3][j].getState()))
                        buttons[i][j].increasePriorty();
                    
                    if(ostate.equals(buttons[(i+2)%3][j].getState()))
                        buttons[i][j].increasePriorty();
                    
                    if(i==j)
                    {
                    if(ostate.equals(buttons[(i+1)%3][(j+1)%3].getState()))
                        buttons[i][j].increasePriorty(); 
                    
                    if(ostate.equals(buttons[(i+2)%3][(j+2)%3].getState()))
                        buttons[i][j].increasePriorty();
                    
                    }
                    if(i==2-j)
                    {
                    if(ostate.equals(buttons[(i+1)%3][(j+2)%3].getState()))
                        buttons[i][j].increasePriorty();   
                    
                    if(ostate.equals(buttons[(i+2)%3][(j+1)%3].getState()))
                        buttons[i][j].increasePriorty(); 
                    
                    }

                    
                  
            }
    }
    
}
