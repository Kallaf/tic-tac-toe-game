package ViewPacakge;

import GamePackage.Button;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class View extends JFrame {
    
    public SelectPanel selectPanel;
    public DifficaltyPanel difficaltyPanel;
    public GamePanel gamePanel;
    public LoadPanel loadPanel;
    private CardLayout cardLayout;
    
    private static View view;
    private View()
    {
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("XOGame");
        
        selectPanel=SelectPanel.OpenSelectPanel();
        difficaltyPanel=DifficaltyPanel.OpenDifficaltyPanel();
        gamePanel=GamePanel.OpenGamePanel();
        loadPanel=LoadPanel.OpenLoadPanel();
        
        cardLayout= new CardLayout();
        this.setLayout(cardLayout);
        this.add(selectPanel);
        this.add(difficaltyPanel);
        this.add(gamePanel);
        this.add(loadPanel);
        selectPanel.setVisible(true);
        difficaltyPanel.setVisible(false);
        gamePanel.setVisible(false);
        loadPanel.setVisible(false);        

    }
    
    public static View Open()
    {
        if(view==null)
            view = new View();
        return view;
    }
    
    
            public Button[][] getButtons()
    {
        return gamePanel.getButtons();
    }
    
    
    public JButton UndoBTN()
    {
        return gamePanel.UndoBTN();
    }
    
    public JButton RedoBTN()
    {
        return gamePanel.RedoBTN();
    }
    
    public void addResetAction(ActionListener action)
    {
        gamePanel.addResetAction(action);
    }
    
    public void addUndoAction(ActionListener action)
    {
        gamePanel.addUndoAction(action);
    }
    
    
    public void addRedoAction(ActionListener action)
    {
        gamePanel.addRedoAction(action);
    }
    
    public void addAction1(ActionListener action)
    {
        gamePanel.addAction1(action);
    }
    
    public void addAction2(ActionListener action)
    {
       gamePanel.addAction2(action);
    }
    
    public void addAction3(ActionListener action)
    {
        gamePanel.addAction3(action);
    }
    
    public void addAction4(ActionListener action)
    {
        gamePanel.addAction4(action);
    }
    
    public void addAction5(ActionListener action)
    {
        gamePanel.addAction5(action);
    }
    
    public void addAction6(ActionListener action)
    {
        gamePanel.addAction6(action);
    }
    
    public void addAction7(ActionListener action)
    {
        gamePanel.addAction7(action);
    }
    
    public void addAction8(ActionListener action)
    {
        gamePanel.addAction8(action);
    }
    
    public void addAction9(ActionListener action)
    {
        gamePanel.addAction9(action);
    }
    public void addActionBack(ActionListener action)
    {
        gamePanel.addActionBack(action);
    }
    
    public void setXCounter(int XCounter)
    {
        gamePanel.setXCounter(XCounter);
    }
    
    public int getXCounter()
    {
        return gamePanel.getXCounter();
    }
    
    public void setOCounter(int OCounter)
    {
        gamePanel.setOCounter(OCounter);
    }
    
    public int getOCounter()
    {
        return gamePanel.getOCounter();
    }
    
    
}
