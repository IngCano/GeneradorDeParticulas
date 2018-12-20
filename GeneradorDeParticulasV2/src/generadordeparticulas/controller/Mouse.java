
package generadordeparticulas.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author jhonedercano@gmail.com
 */
public class Mouse implements MouseListener{

    public boolean clicked;
    public int clickCounter;
    public int x;
    public int y;
    
    public Mouse(){
        clicked = false;
        clickCounter = 0;
        x = 0;
        y = 0;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        clicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        clicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
