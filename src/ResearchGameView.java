import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int frameCount;
	int picNum = 0;
	int CpicNum = 0;
	Crab[] crabs;
	BufferedImage[] pics, images;
	BufferedImage[] Cpics, Cimages;
	BufferedImage bg;
	BufferedImage crabIm1;
	BufferedImage crabIm2;
	BufferedImage crabIm3;
	BufferedImage crabIm4;
	BufferedImage crabIm5;
	BufferedImage[][] animation;
	BufferedImage[][] Canimation;
	
	final int playerFixedX = (int)screenSize.getWidth() / 8;
	final int playerFixedY = (int)screenSize.getHeight() / 3;
	
	final int frameWidth = 10000;
	final int frameHeight = 2500;
	final static int imgWidth = 180;
	final static int imgHeight = 180;
	JFrame frame;
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
	Rectangle cr1;
	Rectangle cr2;
	Rectangle cr3;
	Rectangle cr4;
	Rectangle cr5;
	Rectangle pRect;
	
	/**
	 * @author mattstack
	 * gets the width of the frame
	 * @return the width of frame
	 */
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
	
	public ResearchGameView(){
		frame = new JFrame();
		loadImages();		
		frame.getContentPane().add(panel);	
		frame.setBackground(Color.white);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize.getWidth();
		screenSize.getHeight();
		frame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
//		frame.setSize(1440, 900);
		frame.setVisible(true);	
	}
	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			if (xloc <= 5100) {
			g.drawImage(bg, -xloc, -yloc, 7000, 2500, this); //7000 3000
			g.drawImage(Cpics[CpicNum], (-c1xloc), (-c1yloc), this);
			g.drawImage(Cpics[CpicNum], (-c2xloc), (-c2yloc), this);
			g.drawImage(Cpics[CpicNum], (-c3xloc), (-c3yloc), this);
			g.drawImage(Cpics[CpicNum], (-c4xloc), (-c4yloc), this);
			g.drawImage(Cpics[CpicNum], (-c5xloc), (-c5yloc), this);
			g.drawImage(pics[picNum], playerFixedX, playerFixedY, this);
			g.drawRect((int)cr1.getX(), (int)cr1.getY(), (int)cr1.getWidth(), (int)cr1.getHeight());
			g.drawRect((int)cr2.getX(), (int)cr2.getY(), (int)cr2.getWidth(), (int)cr2.getHeight());
			g.drawRect((int)cr3.getX(), (int)cr3.getY(), (int)cr3.getWidth(), (int)cr3.getHeight());
			g.drawRect((int)cr4.getX(), (int)cr4.getY(), (int)cr4.getWidth(), (int)cr4.getHeight());
			g.drawRect((int)cr5.getX(), (int)cr5.getY(), (int)cr5.getWidth(), (int)cr5.getHeight());
			g.drawRect((int)pRect.getX(), (int)pRect.getY(), (int)pRect.getWidth(), (int)pRect.getHeight());
			}
			else {
				g.drawImage(bg, -5100, -1050, 7000, 3000, this); //7000 3000
				g.drawImage(Cpics[CpicNum], (-c1xloc), (-c1yloc), this);
				g.drawImage(Cpics[CpicNum], (-c2xloc), (-c2yloc), this);
				g.drawImage(Cpics[CpicNum], (-c3xloc), (-c3yloc), this);
				g.drawImage(Cpics[CpicNum], (-c4xloc), (-c4yloc), this);
				g.drawImage(Cpics[CpicNum], (-c5xloc), (-c5yloc), this);
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
		Cimages = new BufferedImage[5];
		Canimation = new BufferedImage[5][0];
		for(int i = 0; i < 5; i++) {
			Cimages[i] = createCrabImage();
			Canimation[i] = new BufferedImage[Cimages[i].getWidth()/imgWidth];
			loadImg(Cimages[i],Canimation[i],2);
		}
		Cpics = Canimation[0];
		pics = animation[RDirection.IDLE.ordinal()];
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
			bg = ImageIO.read(new File("assets/research-game/research-background" + ".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private BufferedImage createCrabImage() {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("assets/research-game/crab-sheet.png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
		crabs = m.getCrabs();
		pLives = m.player.getLives();
		pRect = m.player.getPlayerRect();
		cr1 = m.crab1.getCrabRect();
		cr2 = m.crab2.getCrabRect();
		cr3 = m.crab3.getCrabRect();
		cr4 = m.crab4.getCrabRect();
		cr5 = m.crab5.getCrabRect();
		switch(m.player.getDirection()){
			case IDLE:	
				frameCount = animation[RDirection.IDLE.ordinal()].length;	
				pics = animation[RDirection.IDLE.ordinal()];
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
		CpicNum = (CpicNum + 1) % 2;
		panel.repaint();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
