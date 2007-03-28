package org.gadilif.gmancala.view;

import org.gadilif.gmancala.strategies.PlayerType;


public interface IBoardView {

	void draw();

	void debug(String message);
	
	int getPlay(PlayerType playerType);

}