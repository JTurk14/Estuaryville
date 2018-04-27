import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class ResearchGameView{
	int frameCount;
	int picNum = 0;
	BufferedImage[] pics, images;
	BufferedImage bg;
	BufferedImage crabIm1;
	BufferedImage crabIm2;
	BufferedImage crabIm3;
	BufferedImage crabIm4;
	BufferedImage crabIm5;
	BufferedImage[][] animation;
	
	final int frameWidth = 10000;
	final int frameHeight = 2500;
	final static int imgWidth = 180;
	final static int imgHeight = 180;
	JFrame frame;
	JButton button;
	
	DrawPanel panel = new DrawPanel();
	int xloc;
	int yloc;
	int c1xloc;
	int c1yloc;
	int c2xloc;
	int c2yloc;
	int c3xloc;
	int c3yloc;
	int c4xloc;
	int c4yloc;
	int c5xloc;
	int c5yloc;
	int pLives;
	
	public int getWidth(){
		return frameWidth;
	}
	public int getHeight(){
		return frameHeight;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JButton getButton() {
		return button;
	}
	
	public ResearchGameView(){
		frame = new JFrame();
		loadImages();		
		frame.getContentPane().add(panel);	
		frame.setBackground(Color.white);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1440, 900);
		frame.setVisible(true);	
	}
	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			if (xloc <= 5100) {
			g.drawImage(bg, -xloc, -yloc, 7000, 3000, this); //7000 3000
			g.drawImage(crabIm1, (-c1xloc), (-c1yloc), this);
			g.drawImage(crabIm2, (-c2xloc), (-c2yloc), this);
			g.drawImage(crabIm3, (-c3xloc), (-c3yloc), this);
			g.drawImage(crabIm4, (-c4xloc), (-c4yloc), this);
			g.drawImage(crabIm5, (-c5xloc), (-c5yloc), this);
			g.drawImage(pics[picNum], 100, 300, this);
			}
			else {
				g.drawImage(bg, -5100, -1050, 7000, 3000, this); //7000 3000
				g.drawImage(crabIm1, (-c1xloc), (-c1yloc), this);
				g.drawImage(crabIm2, (-c2xloc), (-c2yloc), this);
				g.drawImage(crabIm3, (-c3xloc), (-c3yloc), this);
				g.drawImage(crabIm4, (-c4xloc), (-c4yloc), this);
				g.drawImage(crabIm5, (-c5xloc), (-c5yloc), this);
				g.drawImage(pics[picNum], xloc - 4950, yloc - 860, this);
			}

			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString("Lives: " + pLives, 100, 100);
		}
	}
	private void loadImages(){
		images = new BufferedImage[RDirection.values().length];
		animation = new BufferedImage[RDirection.values().length][0];
		for(int i = 0; i < RDirection.values().length; i++) {
			images[i] = createImage(RDirection.values()[i]);
			animation[i] = new BufferedImage[images[i].getWidth()/imgWidth];
			loadImg(images[i],animation[i],animation[i].length);
		}
		pics = animation[RDirection.IDEL.ordinal()];
	}
	private void loadImg(BufferedImage img, BufferedImage[] an ,int frames){
		for(int i = 0; i < frames; i++){
			an[i] = img.getSubimage(imgWidth * i, 0, imgWidth, imgHeight);
		}
	}
	private BufferedImage createImage(RDirection direction) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("assets/research-game/female-scientist-" + direction.getName() + ".png"));
			bg = ImageIO.read(new File("assets/research-game/research-background.png"));
			crabIm1 = ImageIO.read(new File("assets/research-game/crab-sheet.png"));
			crabIm2 = ImageIO.read(new File("assets/research-game/crab-sheet.png"));
			crabIm3 = ImageIO.read(new File("assets/research-game/crab-sheet.png"));
			crabIm4 = ImageIO.read(new File("assets/research-game/crab-sheet.png"));
			crabIm5 = ImageIO.read(new File("assets/research-game/crab-sheet.png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// TODO: Change this method so you can load other orc animation bitmaps
	}
	public void update(ResearchGameModel m){
		xloc = m.player.getxPos();
		yloc = m.player.getyPos();
		c1xloc = m.crab1.getCrabXPos();
		c1yloc = m.crab1.getCrabYPos();
		c2xloc = m.crab2.getCrabXPos();
		c2yloc = m.crab2.getCrabYPos();
		c3xloc = m.crab3.getCrabXPos();
		c3yloc = m.crab3.getCrabYPos();
		c4xloc = m.crab4.getCrabXPos();
		c4yloc = m.crab4.getCrabYPos();
		c5xloc = m.crab5.getCrabXPos();
		c5yloc = m.crab5.getCrabYPos();
		pLives = m.player.getLives();
		switch(m.player.getDirection()){
			case IDEL:	
				frameCount = animation[RDirection.IDEL.ordinal()].length;	
				pics = animation[RDirection.IDEL.ordinal()];
				break;
			case EAST:
				frameCount = animation[RDirection.EAST.ordinal()].length;	
				pics = animation[RDirection.EAST.ordinal()];
				break;
			case NORTHEAST:
				frameCount = animation[RDirection.NORTHEAST.ordinal()].length;	
				pics = animation[RDirection.NORTHEAST.ordinal()];
				break;
			case SOUTHEAST:
				frameCount = animation[RDirection.SOUTHEAST.ordinal()].length;	
				pics = animation[RDirection.SOUTHEAST.ordinal()];
				break;

		}
		picNum = (picNum + 1) % frameCount;
		panel.repaint();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
