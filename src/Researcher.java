public class Researcher {
	int xPos;
	int yPos;
	RDirection direction;
	int lives;
 
	public Researcher(int x, int y, RDirection dir, int lives) {
		this.xPos = x;
		this.yPos = y;
		this.direction = dir;
		this.lives = lives;
	}

	public void setDirection(RDirection d) {
		direction = d;
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

	public RDirection getDirection() {
		return direction;
	}

}
