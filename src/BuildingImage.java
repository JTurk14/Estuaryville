import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class BuildingImage implements Icon {
	Icon building;
	Icon background;
	
	public BuildingImage(Icon building, Icon background) {
		this.building = building;
		this.background = background;
	}
	
	@Override
	public int getIconHeight() {
		return Math.max(building.getIconHeight(), background.getIconHeight());
	}
	@Override
	public int getIconWidth() {
		return Math.max(building.getIconWidth(), background.getIconWidth());
	}
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		//System.out.println("Paint Icon: C:"+c+" G: "+g+" X: "+x+" Y: "+y);
		building.paintIcon(c, g, x, y);
		background.paintIcon(c, g, x, y);
	}
	
	
}
