import java.awt.image.BufferedImage;

/**
 * 
 */

public abstract class Model {
	final int imgWidth;
	final int imgHeight;
	final int scrWidth;
	final int scrHeight;
	BufferedImage[][] sprites;
	
	public Model(int w, int h, int iw, int ih){
		this.scrWidth = w;
		this.scrHeight = h;
		this.imgHeight = ih;
		this.imgWidth = iw;
	}
	
	public abstract void update();
	public abstract BufferedImage[][] getSprites();
}
