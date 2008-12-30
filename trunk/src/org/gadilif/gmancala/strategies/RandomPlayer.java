package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.PlayerType;

/**
 * @author Gadi
 *
 */
public final class RandomPlayer extends AbstractPlayer {

	/**
	 * @param player
	 * @param controller
	 */
	public RandomPlayer(final PlayerType player, final BoardController controller) {
		super(player, controller);
	}

	private int getRandomCell() {
		return (int)(Math.random()*(player.getEnd()-player.getStart()))+player.getStart();
	}
	@Override
	public int play() {
		return getRandomCell();
		
	}

}
