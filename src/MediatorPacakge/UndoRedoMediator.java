/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediatorPacakge;

import GamePackage.Button;
import StatePackage.EmptyState;
import StatePackage.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



/**
 *
 * @author Mohamed Elkalaf
 */
public class UndoRedoMediator implements Mediator {

    private final List<User> users;
    private final Stack<Button> UndoStack;
    private final Stack<Button> RedoStack;
    private final Stack<State> RedoStateStack;
    public UndoRedoMediator()
    {
         users=new ArrayList<>();
         this.UndoStack=new Stack<>();
         this.RedoStack=new Stack<>();
         this.RedoStateStack=new Stack<>();
    }
    
    @Override
    public void Undo() {
        Boolean gameNotEnded = null;
        for(User u : this.users)
        {
            gameNotEnded=u.UndoEnabled();
        }
        
        if((!UndoStack.isEmpty())&&gameNotEnded)
         {
             Button b=UndoStack.pop();
             addRedoAction(b);
             b.setState(EmptyState.start());
             
        
             this.users.forEach((u) -> {
                 u.Undo(UndoStack.isEmpty());
            });

         }
    }

    @Override
    public void Redo() {
        Button b=RedoStack.pop();
     State s=RedoStateStack.pop();
             b.setState(s);
        
             addUndoAction(b,false);
             
             this.users.forEach((u) -> {
                 u.Redo(RedoStack.isEmpty());
        });
    }

    @Override
    public void addUser(User user) {
       users.add(user);
       user.setMediator(this);
    }

    @Override
    public void addUndoAction(Button button,Boolean notRedo)
    {
         Boolean gameNotEnded = null;
        for(User u : this.users)
        {
            gameNotEnded=u.UndoEnabled();
        }
        
        if(gameNotEnded)
        {UndoStack.push(button);
        
        this.users.forEach((u) -> {
            u.addUndoAction(notRedo);
             });
        
        }
    }

    
    @Override
    public void resetUndo()
    {
        if(!UndoStack.isEmpty())
        UndoStack.removeAllElements();
    }
    
    @Override
    public void resetredo()
    {
        if(!RedoStack.isEmpty())
        {RedoStack.removeAllElements();
        RedoStateStack.removeAllElements();}
    }
   
    @Override
    public void addRedoAction(Button button) {
      RedoStack.push(button);
      RedoStateStack.push(button.getState());
      
      this.users.forEach((u) -> {
          u.addRedoAction(RedoStack.isEmpty());
        });
    }
    
}
