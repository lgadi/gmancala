package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.PlayerType;

public abstract class AbstractPlayer implements IPlayerStrategy {

	protected BoardController controller;
	protected PlayerType player;
	public AbstractPlayer(PlayerType player, BoardController controller) {
		this.controller = controller;
		this.player = player;
	}
	
	public PlayerType getPlayer() {
		return player;
	}
	public abstract int play();

}
