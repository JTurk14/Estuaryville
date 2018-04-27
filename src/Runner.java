public class Runner {
<<<<<<< HEAD
	public static void main(String [] args){	
		BirdWatchingGameController myC = new BirdWatchingGameController();
		myC.start();
=======
	public static void main(String args[]) {
		Controller control = new Controller();
		control.start();
		
		FishingGameController fcon = new FishingGameController();
		fcon.start();
		
		BirdWatchingGameController bcon = new BirdWatchingGameController();
		bcon.start();
		
		ResearchGameController rcon = new ResearchGameController();
		rcon.start();
>>>>>>> master
	}
}
