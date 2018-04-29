import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Researcher {
	int xPos;
	int yPos;
	RDirection direction;
	int lives;
	Rectangle playerRect;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int playerFixedX = (int)screenSize.getWidth() / 8;
	int playerFixedY = (int)screenSize.getHeight() / 3;
	
	public Researcher(int x, int y, RDirection dir, int lives) {
		this.xPos = x;
		this.yPos = y;
		this.direction = dir;
		this.lives = lives;
		this.playerRect = new Rectangle(playerFixedX + 40, playerFixedY + 160, 100, 25);
	}

	public void setDirection(RDirection d) {
		direction = d;
	}	

	public RDirection getDirection() {
		return direction;
	}
	
	public void setLives(int i) {
		this.lives = i;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	
	public Rectangle getPlayerRect() {
		return playerRect;
	}

}
