package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.PlayerType;

public class HumanPlayer extends AbstractPlayer {

	
	public HumanPlayer(PlayerType player, BoardController controller) {
		super(player, controller);
	}

	@Override
	public int play() {
		return controller.getView().getPlay(player);
		
	}

}
