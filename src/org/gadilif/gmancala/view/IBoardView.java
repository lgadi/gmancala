package org.gadilif.gmancala.view;

import org.gadilif.gmancala.strategies.IPlayerStrategy;


public interface IBoardView {

	void draw();

	void debug(String message);
	
	int getPlay(IPlayerStrategy.PlayerType playerType);

}