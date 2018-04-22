

public class BirdWatchingGameModel extends Model {
	
	static int score;
	static int cameraXPos;
	static int cameraYPos;
	Camera camera;
	Bird[] birds;
	
	
	public BirdWatchingGameModel(int w, int h) {
		super(w, h);
		birds = new Bird[3];
		for (int i = 0; i < birds.length; i++) {
			birds[i] = new Bird(Bird.Species.values()[i]);
		}
		camera = new Camera();
		camera.setXSpeed(0);
		camera.setYSpeed(0);
	}
	
	public Bird[] getBirds(){
		return birds;
	}
	public Camera getCamera(){
		return camera;
	}
	
	@Override
	public void update() {
		Bird b;
		for (int i = 0; i < birds.length; i++) {
			b = birds[i];
			checkBounds(b);
		}
		checkBounds(camera);
	}
	public void checkBounds(Bird b){
		switch(b.getDirection()){
		case NORTH:
			if(b.getYPos() - b.getYSpeed() < - 50)
				b.setYPos(BirdWatchingGameView.getHeight() + 50);
			else
				b.setYPos(b.getYPos() - b.getYSpeed());
			break;
		case EAST:
			if(b.getXPos() + b.getXSpeed() > BirdWatchingGameView.getWidth() - 50)
				b.setXPos(0);
			else
				b.setXPos(b.getXPos() + b.getXSpeed());
			break;
		case WEST:
			if(b.getXPos() - b.getXSpeed() < -50)
				b.setXPos(BirdWatchingGameView.getWidth() - 50);
			else
				b.setXPos(b.getXPos() - b.getXSpeed());
			break;
		case SOUTH:
			if(b.getYPos() + b.getYSpeed() > BirdWatchingGameView.getHeight() - 50)
				b.setYPos(0);
			else
				b.setYPos(b.getYPos() + b.getYSpeed());
			break;
		case NORTHEAST:
			if(b.getYPos() - b.getYSpeed() < - 50)
				b.setYPos(BirdWatchingGameView.getHeight() + 50);
			else
				b.setYPos(b.getYPos() - b.getYSpeed());
			if(b.getXPos() + b.getXSpeed() > BirdWatchingGameView.getWidth() - 50)
				b.setXPos(0);
			else
				b.setXPos(b.getXPos() + b.getXSpeed());
			break;
		case NORTHWEST:
			if(b.getYPos() - b.getYSpeed() < - 50)
				b.setYPos(BirdWatchingGameView.getHeight() + 50);
			else
				b.setYPos(b.getYPos() - b.getYSpeed());
			if(b.getXPos() - b.getXSpeed() < -50)
				b.setXPos(BirdWatchingGameView.getWidth() - 50);
			else
				b.setXPos(b.getXPos() - b.getXSpeed());
			break;
		case SOUTHEAST:
			if(b.getYPos() + b.getYSpeed() > BirdWatchingGameView.getHeight() - 50)
				b.setYPos(0);
			else
				b.setYPos(b.getYPos() + b.getYSpeed());
			if(b.getXPos() + b.getXSpeed() > BirdWatchingGameView.getWidth() - 50)
				b.setXPos(0);
			else
				b.setXPos(b.getXPos() + b.getXSpeed());
			break;
		case SOUTHWEST:
			if(b.getYPos() + b.getYSpeed() > BirdWatchingGameView.getHeight() - 50)
				b.setYPos(0);
			else
				b.setYPos(b.getYPos() + b.getYSpeed());
			if(b.getXPos() - b.getXSpeed() < -50)
				b.setXPos(BirdWatchingGameView.getWidth() - 50);
			else
				b.setXPos(b.getXPos() - b.getXSpeed());
			break;
		default:
			break;
		}
	}
public void checkBounds(Camera c){
		if(c.getYPos() + c.getYSpeed() < -60 || c.getYPos() + c.getYSpeed() > BirdWatchingGameView.getHeight() - 330){
			//don't change y
		}else{
			c.setYPos(c.getYPos() + c.getYSpeed());
		}
		if(c.getXPos() + c.getXSpeed() < 0 || c.getXPos() + c.getXSpeed() > BirdWatchingGameView.getWidth() - 360) {
			//don't change x
		}else{
				c.setXPos(c.getXPos() + c.getXSpeed());
		}
	}

	//Get camera x position
	public static int getCameraXPos() {
		return cameraXPos;
	}
	
	//get camera y position
	public static int getCameraYPos() {
		return cameraYPos;
	}
	
	public static void takePicture() {
		
	}
	
	public static boolean isOnTarget() {
		return true;
	}
	
	public static void displayInfo() {
		
	}
	
}
