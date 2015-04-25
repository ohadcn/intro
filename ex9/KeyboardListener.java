package intro.ex9;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/**
 * A key listener class that keeps track of the keys that are currently pressed
 * on the keyboard. 
 */
class KeyboardListener implements KeyListener,FocusListener{
    
    /** The set of keys that are pressed.*/
    private HashSet<Integer> keys;
    
    /** constructs a new key listener.*/
    KeyboardListener(){
        this.keys = new HashSet<Integer>();
    }
    
    /** resets the listener and removes all keys currently pressed from the list.*/
    private void reset() {
        this.keys.clear();
    }

    /** check if a certain key is pressed. */
    public boolean isKeyPressed(int key) {
        return this.keys.contains(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    

    @Override
    public void focusGained(FocusEvent e) {}


    @Override
    public void focusLost(FocusEvent e) {
        reset();
    }
}
