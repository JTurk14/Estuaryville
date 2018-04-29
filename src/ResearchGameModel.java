import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;

public class ResearchGameModel{
	final int xIncr = 15;
    final int yIncr = 14; //originally 14
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final int startxPos = (int)screenSize.getWidth() / 8;
	final int startyPos = (int)screenSize.getHeight() / 3;
	final int playerFixedX = (int)screenSize.getWidth() / 8;
	final int playerFixedY = (int)screenSize.getHeight() / 3;
	final int startingLives = 3;
	final int imgWidth;
	final int imgHeight;
	final int width;
	final int height;
	final RDirection startDir = RDirection.IDLE;
	Researcher player;
	Crab crab1;
	Crab crab2;
	Crab crab3;
	Crab crab4;
	Crab crab5;
	public Crab[] crabs;

	public ResearchGameModel(int w, int h, int iw, int ih){
		player = new Researcher(startxPos, startyPos, startDir, startingLives);
		crab1 = new Crab(-700, -400);
		crab2 = new Crab(-2500, -800);
		crab3 = new Crab(-3500, -800);
		crab4 = new Crab(-2700, -900);
		crab5 = new Crab(-3900, -750);
		crabs = new Crab[5];
		crabs[0] = crab1;
		crabs[1] = crab2;
		crabs[2] = crab3;
		crabs[3] = crab4;
		crabs[4] = crab5;
		this.width = w;
		this.height = h;
		this.imgHeight = ih;
		this.imgWidth = iw;
	}
	
	public Crab[] getCrabs() {
		return crabs;
	}
	public int getCrabslength() {
		return crabs.length;
	}

	//crab x: -120,crab y: -372
	public void collisionChecker() {
		for (Crab c : crabs){
			if (player.playerRect.intersects(c.crabRect)) {
				player.setLives(player.getLives() - 1);
			}
		}
	}
	
	public void endCheck() {
		if (player.getxPos() >= 6000) {
			System.out.print("game over back to main");
			//add sending back to main screen
		}
	}
	
	public void updateLocationAndDirection(){	
		if (player.direction.getName().contains("forward-east-sheet")) {
			player.xPos += xIncr;
			
			crab1.setCrabXPos(crab1.getCrabXPos() + 25);
			crab1.setCrabRect(crab1.crabXPos, crab1.crabYPos);
			
			crab2.setCrabXPos(crab2.getCrabXPos() + 25);
			crab2.setCrabRect(crab2.crabXPos, crab2.crabYPos);
			
			crab3.setCrabXPos(crab3.getCrabXPos() + 20);
			crab3.setCrabRect(crab3.crabXPos, crab3.crabYPos);
			
			crab4.setCrabXPos(crab4.getCrabXPos() + 25);
			crab4.setCrabRect(crab4.crabXPos, crab4.crabYPos);
			
			crab5.setCrabXPos(crab5.getCrabXPos() + 25);
			crab5.setCrabRect(crab5.crabXPos, crab5.crabYPos);
		}

		
		if (player.direction.getName().contains("forward-southeast-sheet")) {
			player.yPos += yIncr;
			player.xPos += xIncr;
			
			crab1.crabYPos += yIncr;
			crab1.setCrabXPos(crab1.getCrabXPos() + 25);
			crab1.setCrabRect(crab1.getCrabXPos(), crab1.getCrabYPos());
			
			crab2.setCrabYPos(crab2.getCrabYPos() + yIncr);
			crab2.setCrabXPos(crab2.getCrabXPos() + 25);
			crab2.setCrabRect(crab2.crabXPos, crab2.crabYPos);
			
			crab3.setCrabYPos(crab3.getCrabYPos() + yIncr);
			crab3.setCrabXPos(crab3.getCrabXPos() + 20);
			crab3.setCrabRect(crab3.crabXPos, crab3.crabYPos);
			
			crab4.setCrabYPos(crab4.getCrabYPos() + yIncr);
			crab4.setCrabXPos(crab4.getCrabXPos() + 25);
			crab4.setCrabRect(crab4.crabXPos, crab4.crabYPos);
			
			crab5.setCrabYPos(crab5.getCrabYPos() + yIncr);
			crab5.setCrabXPos(crab5.getCrabXPos() + 28);
			crab5.setCrabRect(crab5.crabXPos, crab5.crabYPos);
		}
		if (player.direction.getName().contains("forward-northeast-sheet")) {

			player.yPos -= yIncr;
			player.xPos += xIncr;
			
			crab1.crabYPos -= yIncr;
			crab1.setCrabXPos(crab1.getCrabXPos() + 25);
			crab1.setCrabRect(crab1.getCrabXPos(), crab1.getCrabYPos());
			
			crab2.setCrabYPos(crab2.getCrabYPos() - yIncr);
			crab2.setCrabXPos(crab2.getCrabXPos() + 25);
			crab2.setCrabRect(crab2.crabXPos, crab2.crabYPos);
			
			crab3.setCrabYPos(crab3.getCrabYPos() - yIncr);
			crab3.setCrabXPos(crab3.getCrabXPos() + 20);
			crab3.setCrabRect(crab3.crabXPos, crab3.crabYPos);
			
			crab4.setCrabYPos(crab4.getCrabYPos() - yIncr);
			crab4.setCrabXPos(crab4.getCrabXPos() + 25);
			crab4.setCrabRect(crab4.crabXPos, crab4.crabYPos);
			
			crab5.setCrabYPos(crab5.getCrabYPos() - yIncr);
			crab5.setCrabXPos(crab5.getCrabXPos() + 28);
			crab5.setCrabRect(crab5.crabXPos, crab5.crabYPos);
		}
		
		if (player.direction.getName().contains("idle-sheet")) {
			
			
			player.yPos += 0;
			player.xPos += 0;
			
			crab1.setCrabXPos(crab1.getCrabXPos() + 10);
			crab1.setCrabRect(crab1.getCrabXPos(), crab1.getCrabYPos());
			
			crab2.setCrabXPos(crab2.getCrabXPos() + 10);
			crab2.setCrabRect(crab2.crabXPos, crab2.crabYPos);
			
			crab3.setCrabXPos(crab3.getCrabXPos() + 5);
			crab3.setCrabRect(crab3.crabXPos, crab3.crabYPos);
			
			crab4.setCrabXPos(crab4.getCrabXPos() + 10);
			crab4.setCrabRect(crab4.crabXPos, crab4.crabYPos);
			
			crab5.setCrabXPos(crab5.getCrabXPos() + 13);
			crab5.setCrabRect(crab5.crabXPos, crab5.crabYPos);
		}
		
	}
	
}
