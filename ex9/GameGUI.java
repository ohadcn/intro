package intro.ex9;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class controls the graphical user interface of the spaceship game.
 * It allows images of the spaceships to be drawn on the screen, and also checks 
 * if keyboard keys are pressed (to be used for input from the user).
 * @author avivz
 */
public class GameGUI {
    
    /** The image of the human player's spaceship*/
    public static final Image SPACESHIP_IMAGE = createImageIcon("spaceship3.gif","");
    
    /** The image of the human player's spaceship with an active shield */
    public static final Image SPACESHIP_IMAGE_SHIELD = createImageIcon("spaceship3_shield.gif","");
    
    /** The image of a spaceship controlled by the computer. */
    public static final Image ENEMY_SPACESHIP_IMAGE = createImageIcon("spaceship2.gif","");
    
    /** The image of a spaceship controlled by the computer, with an active shield. */
    public static final Image ENEMY_SPACESHIP_IMAGE_SHIELD = createImageIcon("spaceship2_shield.gif","");
    
    /** The image of a shot. */
    public static final Image SHOT_IMAGE = createImageIcon("shot.gif","");

    /** The number of frames per second */
    private static final int FRAMES_PER_SEC = 50;
    
    /** The size of the area the game occurs in. */
    private static final int DISPLAY_SIZE = 700;
    
    /** The frame in the game.*/
    private JFrame _frame;
    
    /** The panel the game is drawn in. */
    private GamePanel _panel;
    
    /** The text label the death count appears in. */
    private JLabel _label;
    
    /** The keyboard listener that keeps track of pressed keys.*/
    private KeyboardListener _listener;
    
    /** The time by which the next frame should be drawn.*/
    private long _nextFrameTime;
    
    
    /** 
     * Creates a new frame with a GUI and displays it. (This is used
     * automatically by the SpaceWars object. You do not need to use it
     * yourselves)
     */
    public GameGUI(){
        _listener = new KeyboardListener();
        _frame = new JFrame();
        _frame.setIconImage(SPACESHIP_IMAGE);

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _panel = new GamePanel(DISPLAY_SIZE);
        _label = new JLabel();
        _label.setText("Test");

        _frame.getContentPane().add(_panel);
        _frame.getContentPane().add(_label,"South");
        _frame.addKeyListener(_listener);
        _frame.addFocusListener(_listener);
        
        _frame.setResizable(false);
        _frame.pack();
        _frame.setVisible(true);
                
        new Thread(new AutoDisposal(Thread.currentThread(),_frame)).start();
        
        _nextFrameTime = System.currentTimeMillis();
    }
    
    /** 
     * draws the images in the buffer on the screen.  (This is used
     * automatically by the SpaceWars object. You do not need to use it
     * yourselves)
     */
    public void drawBufferToScreen(){
        _panel.postBuffer();
        waitForNextFrame();  
        _panel.repaint();

    }
        
    /** 
     * Sleeps until the time the next frame should be drawn.
     */
    private void waitForNextFrame() {
        long curTime = System.currentTimeMillis();
        while(curTime<_nextFrameTime){
            try {
                Thread.sleep(_nextFrameTime-curTime);
            } catch (InterruptedException e) {}
            curTime = System.currentTimeMillis();
        }
        _nextFrameTime += 1000/FRAMES_PER_SEC;        
    }

    /**
     * Checks if the left arrow key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isLeftPressed(){
        return (_listener.isKeyPressed(KeyEvent.VK_LEFT) && 
            !_listener.isKeyPressed(KeyEvent.VK_RIGHT));
    }

    /**
     * Checks if the right arrow key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isRightPressed(){
        return (_listener.isKeyPressed(KeyEvent.VK_RIGHT) && 
                !_listener.isKeyPressed(KeyEvent.VK_LEFT));
    }
    
    /**
     * Checks if the up arrow key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isUpPressed(){
        return _listener.isKeyPressed(KeyEvent.VK_UP);
    }
    
    /**
     * Checks if the 's' key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isSPressed(){
        return _listener.isKeyPressed(KeyEvent.VK_S);
    }
    
    /**
     * Checks if the 't' key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isTPressed(){
        return _listener.isKeyPressed(KeyEvent.VK_T);
    }

    /**
     * Checks if the 'd' key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isDPressed() {
        return _listener.isKeyPressed(KeyEvent.VK_D);
    }

    /**
     * Checks if the escape key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isEscPressed() {
        return _listener.isKeyPressed(KeyEvent.VK_ESCAPE);
    }

    /**
     * Adds another image to be displayed on the GUI to the buffer. (This is
     * used automatically by the SpaceWars object. You do not need to use it
     * yourselves)
     * @param img the image to add.
     * @param position the position in which the image is to be drawn.
     */
    public void addImageToBuffer(Image img, Physics position){
        _panel.addToBuffer(img, position);
    }
    
    /**
     * Get the Image Icon from the given path (relative to the source code)
     * @param path the relative path to the image file.
     * @param description A description of the file.
     * @return the icon with the image.
     */
    private static Image createImageIcon(String path, String description) {
            java.net.URL imgURL = GamePanel.class.getResource(path);
            if (imgURL != null) {
                    return new ImageIcon(imgURL, description).getImage();
            } else {
                    System.err.println("Couldn't find file: " + path);
                    return null;
            }
    }

    /**
     * Sets the text in the label at the bottom of the GUI.  (This is used
     * automatically by the SpaceWars object to display the number of times the
     * ships have died. You do not need to use it yourselves)
     * @param text the text to place in the frame.
     */
    public void setText(String text) {
        _label.setText(text);
    }
}
