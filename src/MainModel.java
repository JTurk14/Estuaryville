import java.util.HashMap;

public class MainModel {
	private int pollution;
	private int money;
	private MapSpot[][] map;
	private HashMap<String,Building> buildingTypes;
	private int pollIncr = 1;
	private int moneyIncr = 1;
	private BuildState build = BuildState.NONE;
	private boolean[] placedBuildings = new boolean[BuildState.values().length];

	public HashMap<String,Building> getBuildingTypes() {
		return buildingTypes;
	}

	public int getPollIncr() {
		return pollIncr;
	}

	public void setPollIncr(int pollIncr) {
		this.pollIncr = pollIncr;
	}

	public int getMoneyIncr() {
		return moneyIncr;
	}

	public void setMoneyIncr(int moneyIncr) {
		this.moneyIncr = moneyIncr;
	}

	public BuildState getBuild() {
		return build;
	}

	public void setBuild(BuildState build) {
		this.build = build;
	}

	public int getPollution() {
		return pollution;
	}

	public void setPollution(int pollution) {
		this.pollution = pollution;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public MapSpot[][] getMap() {
		return map;
	}
	
	public void setMap(MapSpot[][] map) {
		this.map = map;
	}
	
	public MainModel() {
		pollution = 0;
		money = 100;
		buildingTypes = new HashMap<String,Building>();
		loadBuildingTypes(buildingTypes);
	}
	
	public void loadBuildingTypes(HashMap<String,Building> types) {//legal to import Images here?
		types.put("Bird",new Building(0,10000,"Bird Watching Tower","bird-tower"));
		types.put("Research",new Building(0,10000,"Research Center","research"));
		types.put("Fish",new Building(0,10000,"Fishing Pier","pier"));
		types.put("Factory",new Building(0,10000,"Factory","factory"));
		types.put("Port",new Building(0,10000,"Port","port"));
	}
	
	public void build(Building structure, int xPos, int yPos) {
		//System.out.println("BUILD: "+isConstructable(structure)+" isValid: "+isValidPlacement(xPos,yPos));//test
		if(!placedBuildings[build.ordinal()] && isValidPlacement(xPos, yPos) && isConstructable(structure)) {
			placedBuildings[build.ordinal()] = true;
			map[xPos][yPos].setB(structure);
			//map[xPos][yPos].getButton().setText(structure.getName());
			//map[xPos][yPos].getButton().setIcon(new ImageIcon(structure.getImage()));
			switch(build) {
			case FISH:
				//new FishGame();
				break;
			case BIRD:
				//new BirdGame();
				break;
			case RESEARCH:
				//new ResearchGame();
				break;
			default:
				break;
			}
		}
		build = BuildState.NONE;
	}
	
	public void removeBuilding(int xPos, int yPos) {
		map[xPos][yPos].setB(null);
		map[xPos][yPos].getButton().setText("");
		build = BuildState.NONE;
	}
	
	public boolean isConstructable(Building structure) {
		if ((structure.getCost() <= money) && (structure.getQualityNeeded() >= pollution)){
			money -= structure.getCost();
			return true;
		}
		return false;
	}
	
	public boolean isValidPlacement(int xPos, int yPos) {
		return map[xPos][yPos].isValid();
	}
	
	public boolean gameOver() {
		return false;
	}
	
	public void update() {
		pollution += pollIncr;
		money += moneyIncr;
	}
}
