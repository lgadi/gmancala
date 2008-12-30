package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.model.PlayerType;

/**
 * @author Gadi
 *
 */
public interface IPlayerStrategy {
	/**
	 * This is where the actual strategy implementaion goes..
	 * @return cell to play from
	 */
	int play();
	/**
	 * @return the player type
	 */
	PlayerType getPlayer();
}
