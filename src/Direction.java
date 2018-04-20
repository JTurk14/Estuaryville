/** 
 * This enum is a description of which direction an object is facing
 * and therefore, where it should move.
 */

public enum Direction {
	NORTH("_north"),
	NORTHEAST("_northeast"),
	EAST("_east"),
	SOUTHEAST("_southeast"),
	SOUTH("_south"),
	SOUTHWEST("_southwest"),
	WEST("_west"),
	NORTHWEST("_northwest");
	
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}


}
