import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainView {
	//width/height constants
	final static int FRAME_WIDTH = 1900;
	final static int FRAME_HEIGHT = 1000;
	final static int SIDEBAR_WIDTH = (int) (FRAME_WIDTH*0.2);
	final static int SIDEBAR_HEIGHT = FRAME_HEIGHT;
	final static int MAP_WIDTH = FRAME_WIDTH-SIDEBAR_WIDTH;
	final static int MAP_HEIGHT = FRAME_HEIGHT;
	final static int BAR_WIDTH = (int) (FRAME_WIDTH*0.01);
	final static int BAR_HEIGHT = (int) (FRAME_HEIGHT*0.35);
	//sidebar button constants
	final static int BUILDING_BUTTON_HEIGHT = (int) (SIDEBAR_HEIGHT*0.1);
	final static int BUILDING_BUTTON_WIDTH = (int) (SIDEBAR_WIDTH*0.5);
	final static int BUILDING_BUTTON_X = (int) (FRAME_WIDTH-SIDEBAR_WIDTH+(BUILDING_BUTTON_WIDTH*0.75));
	final static int BUILDING_BUTTON_Y = (int) (SIDEBAR_HEIGHT-(SIDEBAR_HEIGHT*0.95));
	final static int BUILDING_BUTTON_Y_OFFSET = (int) (BUILDING_BUTTON_HEIGHT * 1.25);
	final static int BUILDING_ICON_WIDTH = (int) (SIDEBAR_HEIGHT*0.05);
	final static int BUILDING_ICON_HEIGHT = BUILDING_ICON_WIDTH;
	//map button constants
	final static int NUM_MAP_BUTTONS_X = 10;
	final static int NUM_MAP_BUTTONS_Y_CALC = 11;//FOR WIDTH/HEIGHT CALCULATIONS ONLY
	final static int NUM_MAP_BUTTONS_Y = 10;
	final static int MAP_BUTTON_WIDTH = MAP_WIDTH/NUM_MAP_BUTTONS_X;
	final static int MAP_BUTTON_HEIGHT = MAP_HEIGHT/NUM_MAP_BUTTONS_Y_CALC;
	final static int BORDER_WIDTH_X = MAP_BUTTON_WIDTH/2;
	final static int BORDER_WIDTH_Y = MAP_BUTTON_HEIGHT/4;
	final static int MAP_BUTTON_X = BORDER_WIDTH_X;
	final static int MAP_BUTTON_Y = BORDER_WIDTH_Y;
	final static int MAP_BUTTON_X_OFFSET = MAP_BUTTON_WIDTH;
	final static int MAP_BUTTON_Y_OFFSET = MAP_BUTTON_HEIGHT;
	//Pollution/Money Bar constants
	final static int BAR_X = (int) (FRAME_WIDTH*0.01);
	final static int BAR_Y = (int) (FRAME_HEIGHT*0.075);
	final static int BAR_Y_OFFSET = (int) (BAR_HEIGHT+FRAME_HEIGHT*0.1);
	final static int BAR_ROUND = 20;
	//other constants
	//globals
	private JFrame frame;
	private DrawPanel panel;
	final String[] buildingNames = {"Port","Bird Watching Tower","Factory","Research Station","Fishing Pier"};
	private MapSpot[][] board = new MapSpot[NUM_MAP_BUTTONS_X][NUM_MAP_BUTTONS_Y];
	private HashMap<String,JButton> sidebarButtons = new HashMap<String,JButton>();
	
	//setters/getters
	public JFrame getFrame() {
		return frame;
	}

	public DrawPanel getPanel() {
		return panel;
	}

	public String[] getBuildingNames() {
		return buildingNames;
	}

	public MapSpot[][] getBoard() {
		return board;
	}

	public HashMap<String, JButton> getSidebarButtons() {
		return sidebarButtons;
	}

	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel{
		private int xPos;
		private int yPos;
		private int yOffset;
		
		private double moneyRatio = 0;
		private double pollutionRatio = 0;
		
		public void setMoneyRatio(double moneyRatio) {
			if(moneyRatio <= 1)
				this.moneyRatio = moneyRatio;
		}
		
		public void setPollutionRatio(double pollutionRatio) {
			if(pollutionRatio <= 1)
				this.pollutionRatio = pollutionRatio;
		}
		
		public DrawPanel(int x, int y, int yOffset) {
			xPos = x;
			yPos = y;
			this.yOffset = yOffset;
		}
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(100, 153, 239));
			
			Graphics2D g2d = (Graphics2D) g;
			float thickness = 2;
			g2d.setStroke(new BasicStroke(thickness));
			g2d.setColor(Color.BLACK);
			g2d.drawRoundRect(xPos, yPos, BAR_WIDTH, BAR_HEIGHT,BAR_ROUND,BAR_ROUND);
			g2d.drawRoundRect(xPos, yPos+yOffset, BAR_WIDTH, BAR_HEIGHT,BAR_ROUND,BAR_ROUND);
			g2d.setColor(Color.YELLOW);
			g2d.fillRoundRect(xPos, (int) (yPos+(BAR_HEIGHT*(1-moneyRatio))), BAR_WIDTH, (int) (BAR_HEIGHT - (BAR_HEIGHT*(1-moneyRatio))),BAR_ROUND,BAR_ROUND);
			g2d.setColor(Color.GREEN);
			g2d.fillRoundRect(xPos, (int) (yPos+yOffset+(BAR_HEIGHT*(1-pollutionRatio))), BAR_WIDTH, (int) (BAR_HEIGHT - (BAR_HEIGHT*(1-pollutionRatio))),BAR_ROUND,BAR_ROUND);
			
			g2d.setColor(Color.BLACK);
			g2d.drawString("$"+moneyRatio*1000+"0", BAR_X, BAR_Y-25);
			g2d.drawString("Pollution:\n", BAR_X, BAR_Y+BAR_Y_OFFSET-25);
			g2d.drawString(""+pollutionRatio*10000, BAR_X, BAR_Y+BAR_Y_OFFSET-10);
		}
	}
	
	public MainView(){
		frame = new JFrame();		
		panel = new DrawPanel(BAR_X, BAR_Y, BAR_Y_OFFSET);
		panel.setLayout(null);
		
		addSidebar();
		addMainMap();
		
		frame.setTitle("EstuaryVille");
		frame.getContentPane().add(panel);
		frame.setBackground(new Color(100, 153, 239));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
	}
	
	public void addSidebar() {
		ImageIcon[] sidebarImages = loadSidebarImages();
		for(int i = 0; i < buildingNames.length; i++) {
			JButton button = new JButton(buildingNames[i]);//new JButton(buildingNames[i]);
			button.setBounds(BUILDING_BUTTON_X,BUILDING_BUTTON_Y+(i*BUILDING_BUTTON_Y_OFFSET), BUILDING_BUTTON_WIDTH, BUILDING_BUTTON_HEIGHT);

			button.setIcon(sidebarImages[i]);
			button.setVerticalTextPosition(SwingConstants.BOTTOM);
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			panel.add(button);
			sidebarButtons.put(buildingNames[i], button);
		}
		JButton button = new JButton("Remove");
		button.setBounds(BUILDING_BUTTON_X,BUILDING_BUTTON_Y+(buildingNames.length*BUILDING_BUTTON_Y_OFFSET)+BUILDING_BUTTON_Y_OFFSET, BUILDING_BUTTON_WIDTH, BUILDING_BUTTON_HEIGHT);
		panel.add(button);
		sidebarButtons.put("Remove", button);
	}
	
	public ImageIcon[] loadSidebarImages() {
		BufferedImage[] bImgs = new BufferedImage[buildingNames.length];
		ImageIcon[] imgs = new ImageIcon[buildingNames.length];
				
		try {
			bImgs[0] = ImageIO.read(new File("assets/main-screen/buildings/port.png"));
			bImgs[1] = ImageIO.read(new File("assets/main-screen/buildings/bird-tower.png"));
			bImgs[2] = ImageIO.read(new File("assets/main-screen/buildings/factory.png"));
			bImgs[3] = ImageIO.read(new File("assets/main-screen/buildings/research.png"));
			bImgs[4] = ImageIO.read(new File("assets/main-screen/buildings/pier.png"));
			
			imgs[0] = new ImageIcon(resize(bImgs[0],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[1] = new ImageIcon(resize(bImgs[1],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[2] = new ImageIcon(resize(bImgs[2],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[3] = new ImageIcon(resize(bImgs[3],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[4] = new ImageIcon(resize(bImgs[4],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgs;
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	public void addMainMap() {
		ImageIcon[][] backgroundImages = loadBackgroundImages();
		for(int i = 0; i < NUM_MAP_BUTTONS_X; i++) {
			for(int j = 0; j < NUM_MAP_BUTTONS_Y; j++) {
				JButton button = new JButton();
				button.setBounds(MAP_BUTTON_X+(i*MAP_BUTTON_X_OFFSET), MAP_BUTTON_Y+(j*MAP_BUTTON_Y_OFFSET), MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT);
				button.setHorizontalTextPosition(SwingConstants.CENTER);
				panel.add(button);
				board[i][j] = new MapSpot(button, TerrainState.NORMAL, backgroundImages[i][j]);
			}
		}
	}
	
	public ImageIcon[][] loadBackgroundImages() {
		ImageIcon[][] bgImages = new ImageIcon[NUM_MAP_BUTTONS_X][NUM_MAP_BUTTONS_Y];
		BufferedImage[][] bgBImages = new BufferedImage[NUM_MAP_BUTTONS_X][NUM_MAP_BUTTONS_Y];
		try {
			BufferedImage background = ImageIO.read(new File("assets/main-screen/mainscreen.png"));
			for(int i = 0; i < NUM_MAP_BUTTONS_X; i++) {
				for(int j = 0; j < NUM_MAP_BUTTONS_Y; j++) {
					bgBImages[i][j] = background.getSubimage(i*(background.getWidth()/NUM_MAP_BUTTONS_X), j*(background.getHeight()/NUM_MAP_BUTTONS_Y), background.getWidth()/NUM_MAP_BUTTONS_X, background.getHeight()/NUM_MAP_BUTTONS_Y);
					bgImages[i][j] = new ImageIcon(resize(bgBImages[i][j],MAP_BUTTON_WIDTH,MAP_BUTTON_HEIGHT));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bgImages;
	}
	
	public void update(double money, double poll, MapSpot[][] map) {
		updateBoard();
		updateBars(money, poll);
		this.board = map;
		panel.repaint();
	}
	
	public void updateBoard() {
		for(MapSpot[] ms_arr : board) {
			for(MapSpot ms : ms_arr) {
				if(ms.getB() != null) {//optimize with boolean?
					BufferedImage bImage = resize(ms.getB().getImage(),MAP_BUTTON_HEIGHT,MAP_BUTTON_HEIGHT);
					BuildingImage bi = new BuildingImage(ms.getBackground(),new ImageIcon(bImage));
					ms.setShowImage(bi);
				}
				else {
					ms.setShowImage(ms.getBackground());
				}
			}
		}
	}
	
	public void updateBars(double moneyRatio, double pollutionRatio) {
		panel.setMoneyRatio(moneyRatio);
		panel.setPollutionRatio(pollutionRatio);
	}
}