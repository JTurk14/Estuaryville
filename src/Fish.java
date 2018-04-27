/**
 * Joel Turk
 */

public class Fish {
	int xSpeed;
	int ySpeed;
	Direction direction;
	public enum Species{
		AMERICAN_SHAD("american-shad"),
		STURGEON("sturgeon"),
		SUMMER_FLOUNDER("summer-flounder");
		
		private String name = null;
		
		private Species(String s){
			name = s;
		}
		public String getName() {
			return name;
		}
	}
	Species species;
	int xPos;
	int yPos;
	
	/** GETTERS & SETTERS */
	public int getXSpeed(){return xSpeed;}
	public void setXSpeed(int xs){xSpeed = xs;}
	
	public int getYSpeed(){return ySpeed;}
	public void setYSpeed(int ys){ySpeed = ys;}
	
	public Direction getDirection(){return direction;}
	public void setDirection(Direction dir){direction = dir;}
	
	public Species getSpecies(){return species;}
	public void setSpecies(Species sp){species = sp;}
	
	public int getXPos(){return xPos;}
	public void setXPos(int xp){xPos = xp;}
	
	public int getYPos(){return yPos;}
	public void setYPos(int yp){yPos = yp;}
	
	
	public Fish(Species s, int xS, int yS, Direction dir){
		species = s;
		xSpeed = xS;
		ySpeed = yS;
		direction = dir;
		xPos = (int) (Math.random()*FishingGameView.getWidth()/2 + FishingGameView.getWidth()/4);
		yPos = (int) (Math.random()*FishingGameView.getHeight()/2 + 300);
	}
}
