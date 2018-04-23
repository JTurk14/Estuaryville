import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
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

	final static int WIDTH = 1100;
	final static int HEIGHT = 700;
	public static BufferedImage bg; //background image
	public static BufferedImage hook_sprite; //the fishing hook
	public BufferedImage[][] fish_sprites;
	public BufferedImage[] object_sprites; //trash and fish
	public int imgWidths[];
	public int imgHeights[];
	private Fish[] fishes;
	private Hook hook;
	private int frameCount = 0;
	DrawPanel panel = new DrawPanel();
	
	public FishingGameView(){
		fishes = new Fish[1];
		fishes[0] = new Fish(Fish.Species.STURGEON, 1, 1, Direction.EAST);
		hook = new Hook();
		loadSprites();
		frame = new JFrame();
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
	}
	
	private class DrawPanel extends JPanel{
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(WIDTH, HEIGHT);
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(bg, 0, 0, Color.GRAY, this);
			g.drawImage(hook_sprite, hook.getXPos(), hook.getYPos(), this);
			for(int i = 0; i < fishes.length; i++){
				Fish f = fishes[i];
				if(f.getDirection().equals(Direction.WEST))
					g.drawImage(fish_sprites[f.getSpecies().ordinal()*2][frameCount], f.getXPos(), f.getYPos(), this);
				else
					g.drawImage(fish_sprites[f.getSpecies().ordinal()*2+1][frameCount], f.getXPos(), f.getYPos(), this);
			}
			//g.drawImage(pics[picNum], xloc, yloc, Color.gray, this);
		}
	}
	
	/*private static BufferedImage createFlipped(BufferedImage image)
    {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }
	
	private static BufferedImage createTransformed(
	        BufferedImage image, AffineTransform at)
	    {
	        BufferedImage newImage = new BufferedImage(
	            image.getWidth(), image.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g = newImage.createGraphics();
	        g.transform(at);
	        g.drawImage(image, 0, 0, null);
	        g.dispose();
	        return newImage;
	    }*/
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	public static int getWidth(){return WIDTH;}
	public static int getHeight(){return HEIGHT;}
	
	private void loadSprites() {
		int speciesNum = Fish.Species.values().length;
		int dirNum = 2; //Fish can only be East or West
		try {
			bg = ImageIO.read(new File("assets/fishing-game/fishing-background.png"));
			bg = resize(bg, WIDTH, HEIGHT);
			hook_sprite = ImageIO.read(new File("assets/fishing-game/hook.png"));
			hook_sprite = resize(hook_sprite, 100, 100);
			Scanner infoScanner = new Scanner(new File("assets/fishing-game/info.txt"));
			imgWidths = new int[speciesNum];
			imgHeights = new int[speciesNum];
			fish_sprites = new BufferedImage[speciesNum*dirNum][4];
			String nextInfo = "";
			for(int i = 0; i < speciesNum; i++){
				while(!nextInfo.contains(Fish.Species.values()[i].getName())){
					nextInfo = infoScanner.next();
					if(nextInfo.contains(Fish.Species.values()[i].getName())){						
						imgWidths[i] = Integer.parseInt(infoScanner.next());
						imgHeights[i] = Integer.parseInt(infoScanner.next());
					}
				}
				for(int j = 0; j < dirNum; j++){
					loadFishSheet(Fish.Species.values()[i], i, j);
					int counter = 0;
					for(BufferedImage img : fish_sprites[i*dirNum + j]){
						fish_sprites[i*dirNum+j][counter] = resize(img, 100, 100);
						counter++;
					}
				}
			}
			infoScanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void loadFishSheet(Fish.Species species, int index, int eastFlag) throws IOException{
		String filepath = "assets/fishing-game/fish/"+species.getName();
		if(eastFlag > 0)
			filepath+="-east";
		filepath+="-sheet.png";
		BufferedImage fishSheet = ImageIO.read(new File(filepath));
		fish_sprites[index*2+eastFlag] = new BufferedImage[4];
		for(int i = 0; i < 4; i++){
			fish_sprites[index*2+eastFlag][i] = fishSheet.getSubimage(imgWidths[index] * i, 0, imgWidths[index], imgHeights[index]);
		}
	}
	
	
	public void update(Fish[] fArr, Hook h) {
		fishes = fArr;
		hook = h;
		frameCount = (frameCount + 1) % 4;
		panel.repaint();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public BufferedImage[] getSprites(){
		return object_sprites;
		
	}
	
	@Override
	public void update() {
	}

}
