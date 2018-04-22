
import java.awt.Image;
import java.util.Random;

public class Bird {
	int xSpd, ySpd;
	int xPos, yPos;
	Direction direction;
	Species species;
	Image im;
	
	public Bird(Species sp) {
		this.species = sp;
		Random rand = new Random();
		this.xSpd = rand.nextInt(50);
		this.ySpd = rand.nextInt(50);
		this.xPos = rand.nextInt(BirdWatchingGameView.getWidth());
		this.yPos = rand.nextInt(BirdWatchingGameView.getHeight());
		this.direction = Direction.values()[rand.nextInt(8)];
	}
	
	public enum Species{		
		BLUE_HERON("great-blue-heron"),
		SANDPIPER("least-sandpiper"),
		OSPREY("osprey");
		
		String name;
		private Species(String n) {
			this.name = n;
		}
	}
	
	/** GETTERS & SETTERS */
	public int getXSpeed(){return xSpd;}
	public void setXSpeed(int xs){xSpd = xs;}
	
	public int getYSpeed(){return ySpd;}
	public void setYSpeed(int ys){ySpd = ys;}
	
	public Direction getDirection(){return direction;}
	public void setDirection(Direction dir){direction = dir;}
	
	public Species getspecies(){return species;}
	public void setSpecies(Species sp){species = sp;}
	
	public int getXPos(){return xPos;}
	public void setXPos(int xp){xPos = xp;}
	
	public int getYPos(){return yPos;}
	public void setYPos(int yp){yPos = yp;}
}
