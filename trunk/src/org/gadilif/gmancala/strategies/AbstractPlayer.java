package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.PlayerType;

/**
 * @author Gadi
 *
 */
public abstract class AbstractPlayer implements IPlayerStrategy {

	protected BoardController controller;
	protected PlayerType player;
	public AbstractPlayer(final PlayerType player, final BoardController controller) {
		this.controller = controller;
		this.player = player;
	}
	
	public final PlayerType getPlayer() {
		return player;
	}
	/* (non-Javadoc)
	 * @see org.gadilif.gmancala.strategies.IPlayerStrategy#play()
	 */
	public abstract int play();

}
