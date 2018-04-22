
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;


public class BirdWatchingGameController {

	private BirdWatchingGameModel birdModel;
	private BirdWatchingGameView birdView;
	private Action drawAction;

	
	@SuppressWarnings("serial")
	public BirdWatchingGameController() {
		birdView = new BirdWatchingGameView();
		birdView.getFrame().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				switch (ke.getKeyCode()) {
				case KeyEvent.VK_UP:
					birdModel.camera.setYSpeed(-15);
					break;
				case KeyEvent.VK_DOWN:
					birdModel.camera.setYSpeed(15);
					break;
				case KeyEvent.VK_LEFT:
					birdModel.camera.setXSpeed(-15);
					break;
				case KeyEvent.VK_RIGHT:
					birdModel.camera.setXSpeed(15);
					break;
				}
				
			}

			@Override
			public void keyReleased(KeyEvent ke) {
				switch(ke.getKeyCode()){
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					birdModel.camera.setYSpeed(0);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					birdModel.camera.setXSpeed(0);
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent ke) {

			}
		});

		/*
		view.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getState() == OrcState.HALT){
					model.setState(OrcState.FORWARD);
				}else{
					model.setState(OrcState.HALT);
				}
			}
		});*/
		
		birdModel = new BirdWatchingGameModel(BirdWatchingGameView.getWidth(), BirdWatchingGameView.getHeight());
		
		//model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		BirdWatchingGameController c = this;
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				c.redraw();
			}
		};
	}

	// run the simulation
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(30, drawAction);
				t.start();
			}
		});
		// increment the x and y coordinates, alter direction if necessary
		// update the view
	}

	public void redraw() {
		birdModel.update();
		birdView.update(birdModel.getBirds(), birdModel.getCamera());
	}

}
