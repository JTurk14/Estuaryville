:x
	private int cXPos;
	private int cYPos;
	private int cXIncr;
	private int cYIncr;
		
	public int getXPos(){return cXPos;}
	public void setXPos(int x){cXPos = x;}
	
	public int getYPos(){return cYPos;}
	public void setYPos(int y){cYPos = y;}
	
	public int getXSpeed(){return cXIncr;}
	public void setXSpeed(int x){cXIncr = x;}
	
	public int getYSpeed(){return cYIncr;}
	public void setYSpeed(int y){cYIncr = y;}
	
	public Camera(){
		cXPos = 500;
		cYPos = 450;
	}
}
