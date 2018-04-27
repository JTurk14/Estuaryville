import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MapSpot {
	JButton button;
	Building b;
	ImageIcon background;
	Icon showImage;
	TerrainState tState;
	
	public Icon getShowImage() {
		return showImage;
	}

	public void setShowImage(Icon showImage) {
		this.showImage = showImage;
		button.setIcon(showImage);
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public Building getB() {
		return b;
	}

	public void setB(Building b) {
		this.b = b;
	}

	public ImageIcon getBackground() {
		return background;
	}

	public void setBackground(ImageIcon background) {
		this.background = background;
	}

	public TerrainState gettState() {
		return tState;
	}

	public void settState(TerrainState tState) {
		this.tState = tState;
	}

	public MapSpot(JButton button, TerrainState ts, ImageIcon img) {
		this.button = button;
		this.tState = ts;
		this.background = img;
		this.showImage = img;
		button.setIcon(showImage);
	}
	
	public boolean isValid() {
		return (tState == TerrainState.NORMAL && b == null);
	}
	
}
