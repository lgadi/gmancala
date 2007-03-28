package org.gadilif.gmancala.controller;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.strategies.IPlayerStrategy;
import org.gadilif.gmancala.view.IBoardView;

public class BoardController {

	public enum PlayResult {INVALID, OK, ANOTHER}
	private BoardModel model;
	private IBoardView view;
	private IPlayerStrategy player1;
	private IPlayerStrategy player2;
	
	public BoardController(final BoardModel model, final IBoardView view) {
		this.model = model;
		this.view = view;
	}
	public void setPlayer1(final IPlayerStrategy player) {
		this.player1 = player;
	}
	public void setPlayer2(final IPlayerStrategy player) {
		this.player2 = player;	
	}
	public boolean play(final int cell) {
		return model.play(cell);
	}
	
	public int getPlay(final PlayerType playerType) {
		return view.getPlay(playerType);
	} 
	public void run() {
		
		while (!model.isGameOver()) {
			view.draw();
			PlayResult result = player1.play();
			while ((result != PlayResult.OK) && !model.isGameOver()) {
				view.debug("Result: "+result);
				view.draw();
				result = player1.play();
			}
			view.draw();
			if (!model.isGameOver()) {
				result = player2.play();
				while ((result != PlayResult.OK) && !model.isGameOver()) {
					view.debug("Result: "+result);
					view.draw();
					result = player2.play();
				}
			}
			view.debug("Player 1 score: "+model.getPlayerScore(PlayerType.ONE));
			view.debug("Player 2 score: "+model.getPlayerScore(PlayerType.TWO));
			view.debug("Winner is: "+model.getWinner());
		}
		
		
	}
	
}
