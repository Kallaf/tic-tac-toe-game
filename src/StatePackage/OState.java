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
public class OState implements State {
    private static final BufferedImage Oimg=makeOImage();
    private static final Icon Oicon= new ImageIcon(Oimg);
    private static OState ostate;
    private OState(){}
    public static OState start()
    {
        if(ostate==null)
            ostate=new OState();
        
        return ostate;
    }
    @Override
    public void setIcon(JButton button) {
        button.setIcon(Oicon);
    }
    
    private static BufferedImage makeOImage()
    {
        try {
            return ImageIO.read(XState.class.getResourceAsStream("O.png"));
        } catch (IOException ex) {
            return null;
        }
    }
    
    
}
