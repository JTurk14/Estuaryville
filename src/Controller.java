import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private FishingGameModel fishModel;
	private FishingGameView fishView;
	private Action drawAction;

	@SuppressWarnings("serial")
	public Controller() {
		fishView = new FishingGameView();
		fishView.getFrame().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {	
				switch(ke.getKeyCode()){
				case KeyEvent.VK_UP:
					fishModel.hook.setYSpeed(-15);
					break;
				case KeyEvent.VK_DOWN:
					fishModel.hook.setYSpeed(15);
					break;
				case KeyEvent.VK_LEFT:
					fishModel.hook.setXSpeed(-15);
					break;
				case KeyEvent.VK_RIGHT:
					fishModel.hook.setXSpeed(15);
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent ke) {
				switch(ke.getKeyCode()){
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					fishModel.hook.setYSpeed(0);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					fishModel.hook.setXSpeed(0);
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
		fishModel = new FishingGameModel(FishingGameView.getWidth(), FishingGameView.getHeight());
		
		//model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		Controller c = this;
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
		fishModel.update();
		fishView.update(fishModel.getFish(), fishModel.getHook());
	}

}
