package org.gadilif.gmancala.view;

import org.gadilif.gmancala.model.PlayerType;


/**
 * @author Gadi
 *
 */
public interface IBoardView {

	/**
	 * asks to redraw the view.
	 */
	void refresh();

	/**
	 * emit a debug message .
	 * @param message message to emit
	 */
	void debug(String message);
	
	/**
	 * Ask player to play.
	 * @param playerType player type
	 * @return which cell to play
	 */
	int getPlay(PlayerType playerType);

	/**
	 * initialize the game view.
	 */
	void init();
}
