package org.gadilif.gmancala.controller;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.strategies.HumanPlayer;
import org.gadilif.gmancala.view.BoardTextView;
import org.gadilif.gmancala.view.IBoardView;
import org.junit.Test;

public class BoardControllerTest {
	@Test
	public void playSimpleGame() {
		BoardModel model = new BoardModel();
		model.init();
		IBoardView view = new BoardTextView(model, System.out);
		BoardController controller = new BoardController(model, view);
		controller.setPlayer1(new HumanPlayer(PlayerType.ONE, controller));
		controller.setPlayer2(new HumanPlayer(PlayerType.TWO, controller));
		//controller.run();
	}
}
