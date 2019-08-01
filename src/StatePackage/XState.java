/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatePackage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Mohamed Elkalaf
 */
public class XState implements State {

    private static final BufferedImage Ximg=makeXImage();
    private static final Icon Xicon= new ImageIcon(Ximg);
    private static XState xstate;
    private XState(){}
    public static XState start()
    {
        if(xstate==null)
            xstate=new XState();
        
        return xstate;
    }
    
    @Override
    public void setIcon(JButton button) {
        button.setIcon(Xicon);
    }
    
    private static BufferedImage makeXImage()
    {
        try {
            return ImageIO.read(XState.class.getResourceAsStream("X.png"));
        } catch (IOException ex) {
            return null;
        }
    }

    
}
