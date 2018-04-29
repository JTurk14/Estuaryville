import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class ResearchGameController{

	private ResearchGameModel model;
	private ResearchGameView view;
	private Action drawAction;
	
	public ResearchGameController(){
		view = new ResearchGameView();

		view.getFrame().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				switch(ke.getKeyCode()) {
					case KeyEvent.VK_UP:
						model.player.setDirection(RDirection.NORTHEAST);
						break;
					case KeyEvent.VK_DOWN:
						model.player.setDirection(RDirection.SOUTHEAST);
						break;
					case KeyEvent.VK_LEFT:
						model.player.setDirection(RDirection.IDLE);
						break;
					case KeyEvent.VK_RIGHT:
						model.player.setDirection(RDirection.EAST);
						break;
				}
			}
			@Override
			public void keyReleased(KeyEvent ke) {

			}
			@Override
			public void keyTyped(KeyEvent ke) {

			}});
		
		model = new ResearchGameModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		ResearchGameController c = this;
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			c.redraw();
    		}
    	};
	}
	
	public void start(){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(30, drawAction);
				t.start();
			}
		});
	}
	public void redraw(){
		model.updateLocationAndDirection();
		model.collisionChecker();
		model.endCheck();
		view.update(model);
	}

}
