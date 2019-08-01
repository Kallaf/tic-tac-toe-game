package GamePackage;

import MediatorPacakge.Mediator;
import MediatorPacakge.User;
import MementoPacckage.Memento;
import ObserverPackage.Observer;
import ObserverPackage.Subject;
import StatePackage.*;
import StrategiesPackage.Strategy;
import java.awt.Color;
import java.util.ArrayList;

public final class Model implements Subject,User {
    
    private State Winner;
    private boolean SomeOneWin=false;
    private boolean gameEndedTie=false;
    private State XOState;
    private final XState xstate;
    private final OState ostate;
    private final EmptyState emptystate;
    private final Button[][] buttons;
    private final ArrayList<Button> canBeSelectedarray;
    private Strategy strategy; 
    private static Model model;
    private final ArrayList<Observer> Observers;
    private Mediator mediator;
   
    
    private Model(Button[][] buttons,Strategy strategy)
    {
        this.buttons=buttons;
        Observers = new ArrayList<>();
        canBeSelectedarray = new ArrayList<>();
        this.strategy=strategy;
        xstate=XState.start();
        ostate=OState.start();
        emptystate=EmptyState.start();
        XOState=xstate;
        reset();
    }
    
    public ArrayList<Button> getSelectedButtons()
    {
        ArrayList<Button> SelectedButtons = new ArrayList<>();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                if(!emptystate.equals(buttons[i][j].getState()))
                    SelectedButtons.add(buttons[i][j]);
            }
        return SelectedButtons;
    }
    
      public static Model starNewModel(Button[][] buttons,Strategy strategy)
      {
          if(model==null)
              model=new Model(buttons, strategy);
          return model;
      }
    

      public void LoadGame(Memento memento)
      {
          for(int i=0;i<memento.getMementoButtons().size();i++)
          {
              this.setSelectedLoadedButton(memento.getMementoButtons().get(i),memento.getMementoButtonsState().get(i));
          }
              
      }
      
       
        public void reset()
    {
        XOState=xstate;
        gameEndedTie=false;
        SomeOneWin=false;
        canBeSelectedarray.clear();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                canBeSelectedarray.add(buttons[i][j]);
                buttons[i][j].setState(emptystate);
                buttons[i][j].button.setBackground(Color.WHITE);
                buttons[i][j].resetPriorty();
            }     
        
       if(mediator!=null)
       {
        this.getMediator().resetUndo();
        this.getMediator().resetredo();
       }
        notifyObservers();
    }
    

    
    
    public Boolean getSomeOneWin()
    {
        return this.SomeOneWin;
    }
    
    public State getWinner()
    {
        return this.Winner;
    }
    
     public  void CheckWinningGame()
    {
        int tempcounter=0;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                if(buttons[i][j].button.getIcon()!=null)
                {
                   tempcounter++; 
                }
            }
        if(tempcounter==9)
            gameEndedTie=true;
        
        for(int i=0;i<3;i++)
       {for(int j=0;j<3;j++)
       {
        if(buttons[i][j].button.getIcon()==buttons[i][(j+1)%3].button.getIcon()&&buttons[i][j].button.getIcon()==buttons[i][(j+2)%3].button.getIcon()&&buttons[i][j].button.getIcon()!=null)
        {
            Winner=buttons[i][j].getState();
            buttons[i][j].button.setBackground(Color.YELLOW);
            buttons[i][(j+1)%3].button.setBackground(Color.YELLOW);
            buttons[i][(j+2)%3].button.setBackground(Color.YELLOW);
            SomeOneWin=true;
            break;
        }
       
       if(buttons[i][j].button.getIcon()==buttons[(i+1)%3][j].button.getIcon()&&buttons[i][j].button.getIcon()==buttons[(i+2)%3][j].button.getIcon()&&buttons[i][j].button.getIcon()!=null)
        {
            Winner=buttons[i][j].getState();
            buttons[i][j].button.setBackground(Color.YELLOW);
            buttons[(i+1)%3][j].button.setBackground(Color.YELLOW);
            buttons[(i+2)%3][j].button.setBackground(Color.YELLOW);
            SomeOneWin=true;
            break;
        }
       
       if(i==j)
       {
           if(buttons[i][j].button.getIcon()==buttons[(i+1)%3][(j+1)%3].button.getIcon()&&buttons[i][j].button.getIcon()==buttons[(i+2)%3][(j+2)%3].button.getIcon()&&buttons[i][j].button.getIcon()!=null)
        {
            Winner=buttons[i][j].getState();
            buttons[i][j].button.setBackground(Color.YELLOW);
            buttons[(i+1)%3][(j+1)%3].button.setBackground(Color.YELLOW);
            buttons[(i+2)%3][(j+2)%3].button.setBackground(Color.YELLOW);
            SomeOneWin=true;
            break;
        }
       }
       
       if(i==2-j)
       {
           if(buttons[i][j].button.getIcon()==buttons[(i+1)%3][(j+2)%3].button.getIcon()&&buttons[i][j].button.getIcon()==buttons[(i+2)%3][(j+1)%3].button.getIcon()&&buttons[i][j].button.getIcon()!=null)
        {
            Winner=buttons[i][j].getState();
            buttons[i][j].button.setBackground(Color.YELLOW);
            buttons[(i+1)%3][(j+2)%3].button.setBackground(Color.YELLOW);
            buttons[(i+2)%3][(j+1)%3].button.setBackground(Color.YELLOW);
            SomeOneWin=true;
            break;
        }
       }
       
       
       } 
        if(SomeOneWin)
            break;
        }  
        
        notifyObservers();
        
    }
    
     
     public void setStrategy(Strategy strategy)
     {
         this.strategy = strategy;
     }
    
     public void setSelectedButton(Button button)
    {
        if(!gameEndedTie&&!SomeOneWin)
        {button.setState(XOState);    
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(button==buttons[i][j])
                addtoSelected(buttons[i][j]);   
        strategy.setPriorties(buttons);
        setPlayerState();
        this.getMediator().resetredo();
        this.getMediator().addUndoAction(button,true);
        CheckWinningGame();
          
        }
    }
     
     
     private void setSelectedLoadedButton(Button button,State state)
    {
        if(!gameEndedTie&&!SomeOneWin)
        {button.setState(state);    
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(button==buttons[i][j])
                addtoSelected(buttons[i][j]);   
        strategy.setPriorties(buttons);
        setPlayerState();
        CheckWinningGame();
          
        }
    }
     
     private void setPlayerState()
    {
        if(XOState.equals(xstate))
        {
            XOState = ostate;
        }
        else
        {
            XOState = xstate;
        }
    }
     
     private void addtoSelected(Button button)
    {
        canBeSelectedarray.remove(button);
    }
    
    private ArrayList<Button> maxPriorty()
    {
        int max=0;
            for(int i=0;i<canBeSelectedarray.size();i++)
            {
               if(canBeSelectedarray.get(i).getPriorty()>max)
                   max=canBeSelectedarray.get(i).getPriorty();
            }
       ArrayList<Button> barray = new ArrayList<>();
       for(int i=0;i<canBeSelectedarray.size();i++)
            {
               if(canBeSelectedarray.get(i).getPriorty()==max)
                   barray.add(canBeSelectedarray.get(i));
            }
       return barray;
    }
    
    public void computerSelection()
    {
        
        Button button;
        int Selection;
        Selection = (int) (Math.random()*maxPriorty().size());
        button= maxPriorty().get(Selection);
        setSelectedButton(button);
    }

    @Override
    public void registerObserver(Observer observer) {
        Observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        Observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Observers.forEach((o) -> {
            o.update(SomeOneWin, gameEndedTie);
        });
    }

    @Override
    public void Undo(Boolean Empty) {
        setPlayerState();
    }

    @Override
    public boolean UndoEnabled() {
        return !SomeOneWin&&!gameEndedTie;
    }


 

    @Override
    public void Redo(Boolean Empty) {
       setPlayerState();
    }

    @Override
    public void setMediator(Mediator mediator) {
     this.mediator=mediator;
    }

    @Override
    public Mediator getMediator() {
         return this.mediator;
    }

    @Override
    public void addUndoAction(Boolean notRedo) {
       
    }

    @Override
    public void addRedoAction(Boolean Empty) {
       
    }
         
}
