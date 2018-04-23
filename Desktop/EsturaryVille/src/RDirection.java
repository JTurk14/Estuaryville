public enum RDirection {

	IDEL("idel-sheet"),
	NORTHEAST("forward-northeast-sheet"),
	EAST("forward-east-sheet"),
	SOUTHEAST("forward-southeast-sheet");
	
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}


}
