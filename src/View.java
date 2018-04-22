import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * 
 */

public abstract class View {

	JFrame frame;
	
	public JFrame getFrame(){return frame;}
	public abstract BufferedImage[] getSprites();
	public abstract void update();
}
