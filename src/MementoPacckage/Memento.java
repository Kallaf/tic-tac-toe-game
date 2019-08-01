package MementoPacckage;

import GamePackage.Button;
import StatePackage.State;
import StrategiesPackage.Strategy;
import java.util.ArrayList;

public class Memento {
    private final ArrayList<Button> mementoButtons;
    private final ArrayList<State> mementoButtonsState;
    private final Strategy strategy;
    private final Boolean onePlayer;
    private final int xCounter;
    private final int oCounter;
    
    public Memento(ArrayList<Button> mementoButtons,Strategy strategy,Boolean onePlayer,int xCounter,int oCounter)
    {
        this.mementoButtons=mementoButtons;
        this.mementoButtonsState=new ArrayList<>();
        mementoButtons.forEach((button) -> {
           this.mementoButtonsState.add(button.getState());
        });
        this.strategy=strategy;
        this.onePlayer=onePlayer;
        this.xCounter=xCounter;
        this.oCounter=oCounter;
    }
    
    
    public ArrayList<Button> getMementoButtons()
    {
        return this.mementoButtons;
    }
    
    
    public ArrayList<State> getMementoButtonsState()
    {
        return this.mementoButtonsState;
    }
    
    public Strategy getStrategy()
    {
        return this.strategy;
    }
    
    public Boolean onePlayer()
    {
        return this.onePlayer;
    }
    
    public int getXCounter()
    {
        return this.xCounter;
    }
    
    public int getOCounter()
    {
        return this.oCounter;
    }
    
}
