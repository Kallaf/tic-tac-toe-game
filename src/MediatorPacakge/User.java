/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediatorPacakge;


/**
 *
 * @author Mohamed Elkalaf
 */
public interface User {
    public void Undo(Boolean Empty);
    public boolean UndoEnabled();
    public void addUndoAction(Boolean notRedo);
    public void addRedoAction(Boolean Empty);
    public void Redo(Boolean Empty);
    public void setMediator(Mediator mediator);
    public Mediator getMediator();
}
