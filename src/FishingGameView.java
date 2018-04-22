import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/** 
 * Joel Turk
 */

public class FishingGameView extends View{

	public BufferedImage bg; //background image
	public BufferedImage[][] fish;
	public BufferedImage[] objects; //trash and fish
	public int imgWidths[];
	public int imgHeights[];
	DrawPanel panel = new DrawPanel();
	
	public FishingGameView(){
		loadSprites();
		frame = new JFrame();
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(bg.getWidth(), bg.getHeight());
		frame.setVisible(true);
	}
	
	private class DrawPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(bg, 0, 0, Color.GRAY, this);
			//g.drawImage(pics[picNum], xloc, yloc, Color.gray, this);
		}
	}
	
	
	public int getWidth(){return bg.getWidth();}
	public int getHeight(){return bg.getHeight();}
	private void loadSprites() {
		try {
			bg = ImageIO.read(new File("assets/fishing-game/fishing-background.png"));
			Scanner infoScanner = new Scanner(new File("assets/fishing-game/info.txt"));
			imgWidths = new int[Fish.Species.values().length];
			imgHeights = new int[Fish.Species.values().length];
			fish = new BufferedImage[Fish.Species.values().length][4];
			String nextInfo = "";
			for(int i = 0; i < Fish.Species.values().length; i++){
				while(!nextInfo.contains(Fish.Species.values()[i].getName())){
					nextInfo = infoScanner.next();
					if(nextInfo.contains(Fish.Species.values()[i].getName())){						
						imgWidths[i] = Integer.parseInt(infoScanner.next());
						imgHeights[i] = Integer.parseInt(infoScanner.next());
					}
				}
				loadFishSheet(Fish.Species.values()[i], i);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void loadFishSheet(Fish.Species species, int index) throws IOException{
		BufferedImage fishSheet = ImageIO.read(new File("assets/fishing-game/fish/"
					+species.getName()+"-sheet.png"));
		fish[index] = new BufferedImage[4];
		for(int i = 0; i < 4; i++){
			fish[index][i] = fishSheet.getSubimage(imgWidths[index] * i, 0, imgWidths[index], imgHeights[index]);
		}
	}
	
	@Override
	public void update() {
		panel.repaint();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public BufferedImage[] getSprites(){
		return objects;
		
	}

}
