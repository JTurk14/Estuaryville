import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * 
 */

public abstract class View {

	JFrame frame;
	
	public JFrame getFrame(){return frame;}
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract BufferedImage[] getSprites();
	public abstract void update();
}
