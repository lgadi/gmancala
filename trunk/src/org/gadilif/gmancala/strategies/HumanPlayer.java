package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.PlayerType;

/**
 * @author Gadi
 *
 */
public final class HumanPlayer extends AbstractPlayer {

	
	/**
	 * @param player the player strategy
	 * @param controller the board controller
	 */
	public HumanPlayer(final PlayerType player, final BoardController controller) {
		super(player, controller);
	}

	/* (non-Javadoc)
	 * @see org.gadilif.gmancala.strategies.AbstractPlayer#play()
	 */
	@Override
	public int play() {
		return controller.getView().getPlay(player);
	}

}
