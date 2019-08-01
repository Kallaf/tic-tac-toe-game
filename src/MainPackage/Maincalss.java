/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import ViewPacakge.View;
import javax.swing.JFrame;


/**
 *
 * @author Mohamed Elkalaf
 */
public class Maincalss {
    
    public static void main(String[] args)
    {
        View frame = View.Open();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}
