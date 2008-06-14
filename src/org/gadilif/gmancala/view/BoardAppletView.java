package org.gadilif.gmancala.view;

import javax.swing.JApplet;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.strategies.HumanPlayer;
import org.gadilif.gmancala.strategies.SingleTurnLookAheadPlayer;

public class BoardAppletView extends JApplet implements IBoardView {

	private static final long serialVersionUID = 1L;
	ViewDelegate viewDelegate;
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroy");
	}

	BoardModel model = null;
	@Override
	public void init() {
		super.init();
		System.out.println("init");
		
		model = new BoardModel();
		model.init();
		viewDelegate = new ViewDelegate(model);
		viewDelegate.createComponents(this.getContentPane());
		setSize(700,300);
		setVisible(true);
		BoardController controller = new BoardController(model, this);
		controller.setPlayer1(new HumanPlayer(PlayerType.ONE, controller));
		controller.setPlayer2(new SingleTurnLookAheadPlayer(PlayerType.TWO, controller));
		controller.run();
	}

	@Override
	public void start() {
		super.start();
		System.out.println("start");
	}

	@Override
	public void stop() {
		super.stop();
		System.out.println("stop");
	}
	

	public void debug(String message) {
		viewDelegate.debug(message);
	}

	public int getPlay(PlayerType playerType) {	
		return viewDelegate.waitForPlay(playerType);
	}

	public void refresh() {
		viewDelegate.refresh();
	}

}
