public enum RDirection {

	IDLE("idle-sheet"),
	NORTHEAST("forward-northeast-sheet"),
	EAST("forward-east-sheet"),
	SOUTHEAST("forward-southeast-sheet");
	
	private String name = null;
	
	private RDirection(String s){
		name = s;
	}
	public String getName() {
		return name;
	}


}
