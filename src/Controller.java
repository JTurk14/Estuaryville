import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class Controller {
	private static MainModel model;
	private static MainView view;
	private static Action drawAction;
	@SuppressWarnings("serial")
	public Controller() {
		model = new MainModel();
		view = new MainView();
		model.setMap(view.getBoard());
		view.getFrame().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				switch(ke.getKeyCode()) {

				}
			}
			@Override
			public void keyReleased(KeyEvent ke) {

			}
			@Override
			public void keyTyped(KeyEvent ke) {

			}
		});
		generateMapListeners();
		generateSidebarListeners();
		
		Controller c = this;
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				c.redraw();
			}
		};
	}
	
	public static void generateMapListeners() {
		for(int i = 0; i < view.getBoard().length; i++) {
			for(int j = 0; j < view.getBoard()[0].length; j++) {
				final int x = i;
				final int y = j;
				view.getBoard()[i][j].getButton().addActionListener(new ActionListener() {
					//Way to simplify?? using enum string etc?
					@Override
					public void actionPerformed(ActionEvent e) {
						switch(model.getBuild()) {
						case PORT:
							model.build(model.getBuildingTypes().get("Port"), x, y);
							break;
						case BIRD:
							model.build(model.getBuildingTypes().get("Bird"), x, y);
							break;
						case FACTORY:
							model.build(model.getBuildingTypes().get("Factory"), x, y);
							break;
						case RESEARCH:
							model.build(model.getBuildingTypes().get("Research"), x, y);
							break;
						case FISH:
							model.build(model.getBuildingTypes().get("Fish"), x, y);
							break;
						case REMOVE:
							model.removeBuilding(x, y);
						default:
							break;
						}
					}
				});
			}
		}
	}
	
	public static void generateSidebarListeners() {//
		view.getSidebarButtons().get("Port").setMnemonic(KeyEvent.VK_P);
		view.getSidebarButtons().get("Port").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.PORT);
			}
		});
		
		view.getSidebarButtons().get("Bird Watching Tower").setMnemonic(KeyEvent.VK_B);
		view.getSidebarButtons().get("Bird Watching Tower").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.BIRD);
			}
		});
		
		view.getSidebarButtons().get("Factory").setMnemonic(KeyEvent.VK_F);
		view.getSidebarButtons().get("Factory").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.FACTORY);
			}
		});
		
		view.getSidebarButtons().get("Research Station").setMnemonic(KeyEvent.VK_S);
		view.getSidebarButtons().get("Research Station").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.RESEARCH);
			}
		});
		
		view.getSidebarButtons().get("Fishing Pier").setMnemonic(KeyEvent.VK_I);
		view.getSidebarButtons().get("Fishing Pier").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.FISH);
			}
		});
		
		view.getSidebarButtons().get("Remove").setMnemonic(KeyEvent.VK_R);
		view.getSidebarButtons().get("Remove").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.REMOVE);
			}
		});
	}
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(30, drawAction);
				t.start();
			}
		});
	}

	public void redraw() {
		model.update();
		view.update((double) (model.getMoney())/(double) (model.MONEY_MAX), (double) (model.getPollution()) /(double) (model.POLLUTION_MAX), model.getMap());
		//System.out.println("MONEY: "+model.getMoney()+" MAX: "+model.MONEY_MAX+" RATIO: "+model.getMoney()/model.MONEY_MAX+"| POLLUTION: "+model.getPollution()+" MAX: "+model.POLLUTION_MAX+" RATIO: "+model.getPollution()/model.POLLUTION_MAX);
	}
}
