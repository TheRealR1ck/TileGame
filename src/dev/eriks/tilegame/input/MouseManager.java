/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.input;

import dev.eriks.tilegame.ui.uiMan;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author eriks
 */
public class MouseManager implements MouseListener, MouseMotionListener{

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private uiMan uiMan, uiMan2;
    
    //Implemented Methods
    
    //Getters

    public void setuiMan(uiMan uiMan) {
        this.uiMan2 = this.uiMan;
        this.uiMan = uiMan;
    }
    public void revertuiMan(){
        this.uiMan = this.uiMan2;
    }

    public uiMan getuiMan() {
        return uiMan;
    }
  
    public boolean isLeftPressed(){
        return leftPressed;
    }
    
    public boolean isRightPressed(){
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }  
    //Abs methods
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else if(e.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else if(e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;
        
        if(uiMan != null)
            uiMan.onMouseRelease(e);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        if(uiMan != null)
            uiMan.onMouseMove(e);
    }
    //OTHER METHODS
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
       
}
