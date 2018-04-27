/**
 * Joel Turk
 */

public class FishingGameModel extends Model{
	
	public Hook hook;
	public Fish[] fishes;
	
	public FishingGameModel(int w, int h){
		super(w, h);
		fishes = new Fish[3];
		for(int i = 0; i < 3; i++){
			fishes[i] = new Fish(Fish.Species.values()[i], 9, 2, (i % 2 == 0 ? Direction.EAST : Direction.WEST));
		}
		hook = new Hook();
		hook.setXSpeed(0);
		hook.setYSpeed(0);
	}
	
	public Fish[] getFish(){return fishes;}
	public Hook getHook(){return hook;}
	
	@Override
	public void update(){
		Fish f;
		for(int i = 0; i < fishes.length; i++){
			f = fishes[i];
			checkBounds(f);
		}
		checkBounds(hook);
	}
	public void checkBounds(Fish f){
		switch(f.getDirection()){
		case NORTH:
			if(f.getYPos() - f.getYSpeed() < 300)
				f.setDirection(Direction.SOUTH);
			else
				f.setYPos(f.getYPos() - f.getYSpeed());
			break;
		case EAST:
			if(f.getXPos() + f.getXSpeed() > FishingGameView.getWidth() - 50)
				f.setDirection(Direction.WEST);
			else
				f.setXPos(f.getXPos() + f.getXSpeed());
			break;
		case WEST:
			if(f.getXPos() - f.getXSpeed() < -50)
				f.setDirection(Direction.EAST);
			else
				f.setXPos(f.getXPos() - f.getXSpeed());
			break;
		case SOUTH:
			if(f.getYPos() + f.getYSpeed() > FishingGameView.getHeight() - 100)
				f.setDirection(Direction.NORTH);
			else
				f.setYPos(f.getYPos() + f.getYSpeed());
			break;
		default:
			break;
		}
	}
public void checkBounds(Hook h){
		if(h.getYPos() + h.getYSpeed() < 300 || h.getYPos() + h.getYSpeed() > FishingGameView.getHeight() - 100){
			//don't change y
		}else{
			h.setYPos(h.getYPos() + h.getYSpeed());
		}
		if(h.getXPos() + h.getXSpeed() < -50 || h.getXPos() + h.getXSpeed() > FishingGameView.getWidth() - 50){
			//don't change x
		}else{
				h.setXPos(h.getXPos() + h.getXSpeed());
		}
	}
}
