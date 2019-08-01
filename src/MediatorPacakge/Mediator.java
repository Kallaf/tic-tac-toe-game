/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediatorPacakge;

import GamePackage.Button;

/**
 *
 * @author Mohamed Elkalaf
 */
public interface Mediator {
    public void Undo();
    public void addUndoAction(Button button,Boolean notRedo);
    public void addRedoAction(Button button);
    public void resetUndo();
    public void resetredo();
    public void Redo();
    void addUser(User user);
    
}
