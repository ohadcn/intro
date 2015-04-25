package intro.ex9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * The Panel used to display the game in the SpaceWars game.
 * @author oop
 */
class GamePanel extends JPanel{
    
    private static final long serialVersionUID = 1L;

    /** the lists of positions of items to be drawn on the screen and in the buffer.*/
    private ArrayList<Physics> _positions, _posBuffer;
    
    /** the lists of images to draw on the screen, and the images in the off-screen buffer.*/
    private ArrayList<Image> _images, _imgBuffer;
    
    /** the size of the panel */
    private int _displaySize;

    /**
     * Constructs a new panel of the given size. 
     */
    public GamePanel(int displaySize){
        super();    
        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        _displaySize = displaySize;
        _positions = new ArrayList<Physics>();
        _posBuffer = new ArrayList<Physics>();
        _images = new ArrayList<Image>();
        _imgBuffer = new ArrayList<Image>();
        setPreferredSize(new Dimension(_displaySize,_displaySize));
    }
    
    /**
     * Adds the given image to the buffer.
     */
    public synchronized void addToBuffer(Image img, Physics pos){
        _imgBuffer.add(img);
        _posBuffer.add(pos);
    }
    
    /**
     * Makes the buffer the current set of images that is painted to the screen.
     */
    public synchronized void postBuffer(){
        ArrayList<Physics> tempPos = _positions;
        ArrayList<Image> tempImg = _images;
        
        _positions = _posBuffer;
        _images = _imgBuffer;
        
        _imgBuffer = tempImg;
        _posBuffer = tempPos;
        
        _imgBuffer.clear();
        _posBuffer.clear();        
    }
    
    /**
     * Paints the contents of the frame.
     */
    public synchronized void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0; i<_images.size(); i++){
            drawGameObject(g2d,_images.get(i),_positions.get(i));
        }
        g2d.draw(new Rectangle(0,0,_displaySize,_displaySize));
    }
    
    /**
     * draws a single image at the given position 
     */
    private void drawGameObject(Graphics2D g2d, Image image,
            Physics pos) {
        AffineTransform saveTransform = g2d.getTransform();
        AffineTransform trans = new AffineTransform();

        //translate and rotate the coordinate system so the item is rotated and translated.
        trans.translate(pos.getX()*_displaySize, (1-pos.getY())*_displaySize);
        trans.rotate(-pos.getAngle()+Math.PI/2);
 
        //set the coordinate system and draw a rectangle at its center.
        g2d.setTransform(trans);
        g2d.drawImage(image,-image.getWidth(null)/2,-image.getHeight(null)/2,null);
        //put the old coordinate system back:
        g2d.setTransform(saveTransform);
    }
}
