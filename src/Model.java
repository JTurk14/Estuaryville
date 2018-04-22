
public abstract class Model {
	final int scrWidth;
	final int scrHeight;
	
	public Model(int w, int h){
		this.scrWidth = w;
		this.scrHeight = h;
	}
	
	public abstract void update();
}
