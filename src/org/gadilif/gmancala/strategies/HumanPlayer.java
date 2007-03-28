package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.controller.BoardController.PlayResult;

public class HumanPlayer implements IPlayerStrategy {

	private PlayerType playerType;
	private BoardController controller;
	
	
	public HumanPlayer(final PlayerType playerType, final BoardController controller) {
		this.playerType = playerType;
		this.controller = controller;
	}

	public BoardController.PlayResult play() {
		int cell = controller.getPlay(playerType);
		if (playerType == PlayerType.ONE) {
			if ((cell < 7) && (cell > 0)) {
				return controller.play(cell)?PlayResult.ANOTHER:PlayResult.OK;
			}
		}
		else {
			if ((cell > 7) && (cell < 14)) {
				return controller.play(cell)?PlayResult.ANOTHER:PlayResult.OK;
			}
		}
		return PlayResult.INVALID;
	}

}
