package org.gadilif.gmancala.controller;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.strategies.HumanPlayer;
import org.gadilif.gmancala.view.BoardSwingView;
import org.gadilif.gmancala.view.IBoardView;
import org.junit.Test;

public class BoardControllerTest {
	@Test
	public void playSimpleGame() {
		BoardModel model = new BoardModel();
		model.init();
	
		IBoardView view = new BoardSwingView(model);
		view.setVisible(true);
		BoardController controller = new BoardController(model, view);
		//controller.setPlayer1(new SingleTurnLookAheadPlayer(PlayerType.ONE, controller));
		controller.setPlayer1(new HumanPlayer(PlayerType.ONE, controller));
		controller.setPlayer2(new HumanPlayer(PlayerType.TWO, controller));
		controller.run();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
