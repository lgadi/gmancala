package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.PlayerType;

public class RandomPlayer extends AbstractPlayer {

	public RandomPlayer(PlayerType player, BoardController controller) {
		super(player, controller);
	}

	private int getRandomCell() {
		return (int)(Math.random()*(player.getEnd()-player.getStart()-1))+player.getStart();
	}
	@Override
	public int play() {
		return getRandomCell();
		
	}

}
