package org.gadilif.gmancala.view;

import org.gadilif.gmancala.model.PlayerType;


public interface IBoardView {

	void refresh();

	void debug(String message);
	
	int getPlay(PlayerType playerType);

	void setVisible(boolean b);

	void init();

}