/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategiesPackage;

import GamePackage.Button;
import javax.swing.Icon;

/**
 *
 * @author Mohamed Elkalaf
 */
public class EasyStrategy implements Strategy{

    private static EasyStrategy easystrategy;
    private EasyStrategy(){}
    public static EasyStrategy start()
    {
        if(easystrategy==null)
            easystrategy=new EasyStrategy();
        return easystrategy;
    }
    
    @Override
    public void setPriorties(Button[][] buttons) {
      
    }
    
}
