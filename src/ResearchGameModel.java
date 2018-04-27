import java.util.ArrayList;
import java.util.Collection;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class ResearchGameModel{
	final int xIncr = 15;
    final int yIncr = 14; //originally 14
	final int startxPos = 75;
	final int startyPos = 400;
	final int startingLives = 3;
	final int imgWidth;
	final int imgHeight;
	final int width;
	final int height;
	final RDirection startDir = RDirection.IDEL;
	Researcher player;
	Crab crab1;
	Crab crab2;
	Crab crab3;
	Crab crab4;
	Crab crab5;

	public ResearchGameModel(int w, int h, int iw, int ih){
		player = new Researcher(startxPos, startyPos, startDir, startingLives);
		crab1 = new Crab(-700, -400);
		crab2 = new Crab(-2500, -1000);
		crab3 = new Crab(-3500, -900);
		crab4 = new Crab(-2700, -1050);
		crab5 = new Crab(-3900, -950);
		this.width = w;
		this.height = h;
		this.imgHeight = ih;
		this.imgWidth = iw;
	}
//	public Researcher getPlayer() {
//		return this.player;
//	}

	public void updateLocationAndDirection(){
	if ((crab1.getCrabXPos() <= -80 && crab1.getCrabXPos() >= -120) && (crab1.getCrabYPos() <= -350 && crab1.getCrabYPos() >= -400)) {
		System.out.println("LOSE");
		player.lives -= 1;
	}
	
	if ((crab2.getCrabXPos() <= -80 && crab2.getCrabXPos() >= -120) && (crab2.getCrabYPos() <= -350 && crab2.getCrabYPos() >= -400)) {
		System.out.println("LOSE");
		player.lives -= 1;
	}
	
	if ((crab3.getCrabXPos() <= -80 && crab3.getCrabXPos() >= -120) && (crab3.getCrabYPos() <= -350 && crab3.getCrabYPos() >= -400)) {
		System.out.println("LOSE");
		player.lives -= 1;
	}
	
	if ((crab4.getCrabXPos() <= -80 && crab4.getCrabXPos() >= -120) && (crab4.getCrabYPos() <= -350 && crab4.getCrabYPos() >= -400)) {
		System.out.println("LOSE");
		player.lives -= 1;
	}
	
	if ((crab5.getCrabXPos() <= -80 && crab5.getCrabXPos() >= -120) && (crab5.getCrabYPos() <= -350 && crab5.getCrabYPos() >= -400)) {
		System.out.println("LOSE");
		player.lives -= 1;
	}
	
	if (player.getxPos() >= 6000) {
		System.out.print("game over back to main");
//		System.exit(1);
	}

		
		if (player.direction.getName().contains("forward-east-sheet")) {
			player.xPos += xIncr;
			
			crab1.crabXPos += 25;
			
			crab2.crabXPos += 25;
			
			crab3.crabXPos += 20;
			
			crab4.crabXPos += 25;
			
			crab5.crabXPos += 20;
		}

		
		if (player.direction.getName().contains("forward-southeast-sheet")) {
			player.yPos += yIncr;
			player.xPos += xIncr;
			
			crab1.crabYPos += yIncr;
			crab1.crabXPos += 25;
			
			crab2.crabYPos += yIncr;
			crab2.crabXPos += 25;
			
			crab3.crabYPos += yIncr;
			crab3.crabXPos += 20;
			
			crab4.crabYPos += yIncr;
			crab4.crabXPos += 25;
			
			crab5.crabYPos += yIncr;
			crab5.crabXPos += 28;
		}
		else if (player.direction.getName().contains("forward-northeast-sheet")) {
			player.yPos -= yIncr;
			player.xPos += xIncr;
			
			crab1.crabYPos -= yIncr;
			crab1.crabXPos += 25;
			
			crab2.crabYPos -= yIncr;
			crab2.crabXPos += 25;
			
			crab3.crabYPos -= yIncr;
			crab3.crabXPos += 20;
			
			crab4.crabYPos -= yIncr;
			crab4.crabXPos += 25;
			
			crab5.crabYPos -= yIncr;
			crab5.crabXPos += 28;
		}
		
		if (player.direction.getName().contains("idle-sheet")) {
			player.yPos = player.yPos;
			player.xPos = player.xPos;
			
			crab1.crabXPos += 25;
			
			crab2.crabXPos += 25;
			
			crab3.crabXPos += 20;
			
			crab4.crabXPos += 25;
			
			crab5.crabXPos += 28;
		}
		
	}
	
}
