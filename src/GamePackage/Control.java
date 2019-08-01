/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePackage;

import MediatorPacakge.Mediator;
import MediatorPacakge.UndoRedoMediator;
import MediatorPacakge.User;
import MementoPacckage.Memento;
import ObserverPackage.Observer;
import StatePackage.EmptyState;
import StatePackage.XState;
import StrategiesPackage.Strategy;
import ViewPacakge.LoadPanel;
import ViewPacakge.SelectPanel;
import ViewPacakge.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Mohamed Elkalaf
 */
public class Control implements Observer,User{
    private final Model  model;
    private final View view;
    private Boolean onePlayer;
    private static Control control;
    private Boolean gameNotEnded=true;
    private Strategy strategy;
    private Boolean boardChanged=false;
    private Boolean undoStacknotempty=true;
    
    private final Mediator mediator;
    
    private Control(Boolean onePlayer,Strategy strategy)
    {
        this.view = View.Open();
        this.model=Model.starNewModel(view.getButtons(), strategy);
        this.onePlayer=onePlayer;
        
        this.model.registerObserver(this);
        
        this.mediator= new UndoRedoMediator();
        mediator.addUser(this);
        mediator.addUser(this.model);
        
        this.strategy=strategy;
        
        this.view.addAction1(new action1());
        this.view.addAction2(new action2());
        this.view.addAction3(new action3());
        this.view.addAction4(new action4());
        this.view.addAction5(new action5());
        this.view.addAction6(new action6());
        this.view.addAction7(new action7());
        this.view.addAction8(new action8());
        this.view.addAction9(new action9());
        this.view.addResetAction(new resetAction());
        this.view.addUndoAction(new undoAction());
        this.view.addRedoAction(new redoAction());
        this.view.addActionBack(new BackAction());
        this.view.gamePanel.setVisible(true);
    }
    
    
    public static Control StartControl(Boolean onePlayer,Strategy strategy,Boolean loadedGame,Memento memento)
    {
        if(control==null)
            control=new Control(onePlayer,strategy);
        else 
        {
            control.onePlayer=onePlayer;
            control.model.setStrategy(strategy);
            control.strategy=strategy;
            control.model.reset();
            control.view.setXCounter(0);
            control.view.setOCounter(0);
            if(loadedGame)
            {
                control.model.LoadGame(memento);
                control.view.setXCounter(memento.getXCounter());
                control.view.setOCounter(memento.getOCounter());
            }
            control.boardChanged=false;
            control.view.gamePanel.setVisible(true);
        }
        return control;
    }

    @Override
    public void update(Boolean someOneWin, Boolean GameEndedTie) {
        gameNotEnded = (!someOneWin)&&(!GameEndedTie);
        if(!gameNotEnded||onePlayer)
        {view.UndoBTN().setEnabled(false);
           view.RedoBTN().setEnabled(false);}
    }
    
    
    
    private void ButtonClicked(Button button)
    {
        EmptyState emptystate=EmptyState.start();
        if(emptystate.equals(button.getState())&&gameNotEnded)
     {model.setSelectedButton(button);
     if(onePlayer&&gameNotEnded)
            model.computerSelection();
            
     if(model.getSomeOneWin())
     {
         XState xstate = XState.start();
         if(xstate.equals(model.getWinner()))
         {
             view.setXCounter(view.getXCounter()+1);
         }
         else
         {
            view.setOCounter(view.getOCounter()+1); 
         }
     }
     boardChanged=true;
     }
        
        
    }

    @Override
    public void Undo(Boolean Empty) {
            if(Empty)
                view.UndoBTN().setEnabled(false);
            undoStacknotempty=!Empty;
    }

    @Override
    public void addUndoAction(Boolean notRedo) {
        if(notRedo)
        view.RedoBTN().setEnabled(false);
       if(gameNotEnded){
        view.UndoBTN().setEnabled(true);}
         else
                view.UndoBTN().setEnabled(false);
       undoStacknotempty=true;
    }

    @Override
    public void Redo(Boolean Empty) {
      if(Empty)
                view.RedoBTN().setEnabled(false);
    }

    @Override
    public boolean UndoEnabled() {
        return this.gameNotEnded;
    }


    @Override
    public void setMediator(Mediator mediator) {
       
    }

    @Override
    public Mediator getMediator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addRedoAction(Boolean Empty) {
         if(Empty){
        view.RedoBTN().setEnabled(false);}
         else
                view.RedoBTN().setEnabled(true);
    }
    
    
    class action1  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[0][0]);
        }
        
    }
    
    class action2  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
          ButtonClicked(view.getButtons()[0][1]);
        }
        
    }
    
    class action3  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[0][2]);
        }
        
    }
    
    
    class action4  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[1][0]);
        }
        
    }
    
    
    class action5  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[1][1]);
        }
        
    }
    
    
    class action6  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[1][2]);
        }
        
    }
    
    
    class action7  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[2][0]);
        }
        
    }
    
    
    class action8  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[2][1]);
        }
        
    }
    
    
    class action9  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ButtonClicked(view.getButtons()[2][2]);
        }
        
    }
    
        class resetAction  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           model.reset();
           view.UndoBTN().setEnabled(false);
           view.RedoBTN().setEnabled(false); 
           boardChanged=false;
        }
        
    }
        
        
    class undoAction  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           if(!onePlayer)
            mediator.Undo();
        }
        
    }
    
    
    class redoAction  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!onePlayer)
            mediator.Redo();
        }
        
    }
    
    
    class BackAction  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.UndoBTN().setEnabled(false);
           view.RedoBTN().setEnabled(false);
           view.selectPanel= SelectPanel.OpenSelectPanel();
            if(gameNotEnded&&boardChanged&&undoStacknotempty)
             {
                 view.selectPanel.getLoadBtn().setEnabled(true);
                 Memento memento = new Memento(model.getSelectedButtons(),strategy,onePlayer,view.getXCounter(),view.getOCounter());
                 view.loadPanel=LoadPanel.OpenLoadPanel();
                 view.loadPanel.addNewMemento(memento);
             }
           
             view.selectPanel.setVisible(true);
            
             view.gamePanel.setVisible(false);
        }
        
    }
    
}
