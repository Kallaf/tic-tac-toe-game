/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategiesPackage;

import GamePackage.Button;
import StatePackage.OState;
import StatePackage.XState;

/**
 *
 * @author Mohamed Elkalaf
 */
public class HardStrategy implements Strategy {

    private static HardStrategy hardstrategy;
    private HardStrategy(){}
    public static HardStrategy start()
    {
        if(hardstrategy==null)
            hardstrategy=new HardStrategy();
        return hardstrategy;
    }
    
    
    @Override
    public void setPriorties(Button[][] buttons) {
        XState xstate = XState.start();
        OState ostate = OState.start();
            for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                buttons[i][j].resetPriorty();
                    if(buttons[i][(j+1)%3].button.getIcon()==buttons[i][(j+2)%3].button.getIcon()&&buttons[i][(j+1)%3].getState()==xstate)
                        buttons[i][j].increasePriorty();
                    
                    if(buttons[(i+1)%3][j].button.getIcon()==buttons[(i+2)%3][j].button.getIcon()&&buttons[(i+1)%3][j].getState()==xstate)
                        buttons[i][j].increasePriorty();
                    
                    if(i==j)
                    {
                    if(buttons[(i+1)%3][(j+1)%3].button.getIcon()==buttons[(i+2)%3][(j+2)%3].button.getIcon()&&buttons[(i+1)%3][(j+1)%3].getState()==xstate)
                        buttons[i][j].increasePriorty();  
                    }
                    if(i==2-j)
                    {
                    if(buttons[(i+1)%3][(j+2)%3].button.getIcon()==buttons[(i+2)%3][(j+1)%3].button.getIcon()&&buttons[(i+1)%3][(j+2)%3].getState()==xstate)
                        buttons[i][j].increasePriorty();    
                    }
                    
                    if(buttons[i][(j+1)%3].button.getIcon()==buttons[i][(j+2)%3].button.getIcon()&&buttons[i][(j+1)%3].getState()==xstate)
                    {
                        buttons[i][j].increasePriorty();
                        buttons[i][j].increasePriorty();
                    }
                    
                    if(buttons[(i+1)%3][j].button.getIcon()==buttons[(i+2)%3][j].button.getIcon()&&buttons[(i+1)%3][j].getState()==ostate)
                    {
                        buttons[i][j].increasePriorty();
                        buttons[i][j].increasePriorty();
                    }
                    
                    if(i==j)
                    {
                    if(buttons[(i+1)%3][(j+1)%3].button.getIcon()==buttons[(i+2)%3][(j+2)%3].button.getIcon()&&buttons[(i+1)%3][(j+1)%3].getState()==ostate)
                    {
                        buttons[i][j].increasePriorty();
                        buttons[i][j].increasePriorty();
                    }  
                    }
                    if(i==2-j)
                    {
                    if(buttons[(i+1)%3][(j+2)%3].button.getIcon()==buttons[(i+2)%3][(j+1)%3].button.getIcon()&&buttons[(i+1)%3][(j+2)%3].getState()==ostate)
                    {
                        buttons[i][j].increasePriorty();
                        buttons[i][j].increasePriorty();
                    }    
                    }
                    
                  
            }
    }
    
}
