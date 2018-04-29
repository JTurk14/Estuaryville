import java.awt.Rectangle;

public class Crab{
	int crabXPos;
	int crabYPos;
	Rectangle crabRect;
	
	public Crab(int x, int y) {
		this.crabXPos = x;
		this.crabYPos = y;
		this.crabRect = new Rectangle(-(x - 50), -(y - 50), 80, 50);
	}
	
	public int getCrabXPos() {
		return crabXPos;
	}

	public void setCrabXPos(int crabXPos) {
		this.crabXPos = crabXPos;
	}

	public int getCrabYPos() {
		return crabYPos;
	}

	public void setCrabYPos(int crabYPos) {
		this.crabYPos = crabYPos;
	}
	
	public Rectangle getCrabRect() {
		return crabRect;
	}
	
	public void setCrabRect(int x, int y) {
		crabRect = new Rectangle(-(x - 50), -(y - 50), 80, 50);
	}
	
}