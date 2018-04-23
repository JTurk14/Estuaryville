
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BirdWatchingGameView extends View{

	int frameCount = 0;
	public static BufferedImage background;
	Camera camera;
	public static BufferedImage camera_sprite;
	public BufferedImage[] all_sprites;
	public BufferedImage[][] bird_sprites;
	public int imgWidths[];
	public int imgHeights[];
	public static Bird[] birds;
	JFrame frame;
	DrawPanel panel = new DrawPanel();
	
	//constructor
	public BirdWatchingGameView(){
		frame = new JFrame();
		loadSprites();
		camera = new Camera();
		birds = new Bird[1
		                 ];
		birds[0] = new Bird(Bird.Species.BLUE_HERON);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(background.getWidth(), background.getHeight());
		frame.setVisible(true);	
	}
	
	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(background, 0, 0, Color.gray, this);
			g.drawImage(camera_sprite, camera.getXPos(), camera.getYPos(), this);
			for (int i = 0; i < birds.length; i++) {
				Bird b = birds[i];
				//if(b.getDirection().equals(Direction.WEST))
					g.drawImage(bird_sprites[b.species.ordinal()][frameCount], b.getXPos(), b.getYPos(), this);
				//else
					//g.drawImage(bird_sprites[b.species.ordinal()*2+1][frameCount], b.getXPos(), b.getYPos(), this);
			}
		}
	}
	
	private void loadSprites() {
		int numSpecies = Bird.Species.values().length;
		int numDirections = 2; //only east or west //ADD EAST DIRECTIONS LATER
		try {
			background = ImageIO.read(new File("assets/bird-game/birdwatching-background.png"));
			camera_sprite = ImageIO.read(new File("assets/bird-game/camera.png"));
			Scanner scan = new Scanner(new File("assets/bird-game/info.txt"));
			imgHeights = new int[numSpecies];
			imgWidths = new int[numSpecies];
			bird_sprites = new BufferedImage[numSpecies][]; //MULTIPLY NUMSPECIES BY NUMDIR
			String nextLine = "";
			for (int i = 0; i < numSpecies; i++) {
				while (!nextLine.contains(Bird.Species.values()[i].name)) {
					nextLine = scan.next();
					if (nextLine.contains(Bird.Species.values()[i].name)) {
						imgWidths[i] = scan.nextInt();
						imgHeights[i] = scan.nextInt();
					}
				}
				loadBirdSheet(Bird.Species.values()[i], i);
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadBirdSheet(Bird.Species species, int index) throws IOException{
		BufferedImage birdSheet = ImageIO.read(new File("assets/bird-game/birds/"
					+species.name+"-sheet.png"));
		int numSubImages = 0;
		switch(index) {
		case 0:
			numSubImages = 18;
			break;
		case 1:
			numSubImages = 4;
			break;
		case 2: 
			numSubImages = 16;
			break;
		}
			
		bird_sprites[index] = new BufferedImage[numSubImages];
		for(int i = 0; i < numSubImages; i++){
			for (int j = 0; j < imgWidths.length; j++) {
			}
			bird_sprites[index][i] = birdSheet.getSubimage(imgWidths[index], 0, imgWidths[index], imgHeights[index]);
		}
	}
	
	public BufferedImage[] getSprites(){
		return all_sprites;		
	}
	
	public void update(Bird[] birdArray, Camera c) {
		birds = birdArray;
		camera = c;
		frameCount = (frameCount + 1) % 4;
		panel.repaint();
		try {
			Thread.sleep(80);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static int getWidth(){
		return background.getWidth();
	}
	public static int getHeight(){
		return background.getHeight();
	}
	public JFrame getFrame() {
		return frame;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
