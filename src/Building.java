import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Building {
	private int cost;
	private BufferedImage image;
	private int qualityNeeded;
	private String name;
	private String filename;
	
	public Building(int cost, /*BufferedImage image,*/ int quality, String name, String filename) {
		this.cost = cost;
		//this.image = image;
		this.qualityNeeded = quality;
		this.name = name;
		this.filename = filename;
		this.image = loadImage();
	}
	
	public BufferedImage loadImage() {
		BufferedImage img = null;
		//System.out.println("assets/"+this.filename+".png");
		try {
			img = ImageIO.read(new File("assets/"+this.filename+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public int getQualityNeeded() {
		return qualityNeeded;
	}
	public void setQualityNeeded(int qualityNeeded) {
		this.qualityNeeded = qualityNeeded;
	}
}
