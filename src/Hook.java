/**
 * 
 */

public class Hook {
	private int hookXPos;
	private int hookYPos;
	private int hookXIncr;
	private int hookYIncr;
	private Direction dir;
	final int hookRadius = 10;
	
	public Direction getDirection(){return dir;}
	public void setDirection(Direction d){dir = d;}
	
	public int getXPos(){return hookXPos;}
	public void setXPos(int x){hookXPos = x;}
	
	public int getYPos(){return hookYPos;}
	public void setYPos(int y){hookYPos = y;}
	
	public int getXSpeed(){return hookXIncr;}
	public void setXSpeed(int x){hookXIncr = x;}
	
	public int getYSpeed(){return hookYIncr;}
	public void setYSpeed(int y){hookYIncr = y;}
	
	public Hook(){
		hookXPos = 500;
		hookYPos = 450;
	}
	
}
