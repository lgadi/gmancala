package org.gadilif.gmancala.controller;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.strategies.IPlayerStrategy;
import org.gadilif.gmancala.view.IBoardView;

public class BoardController {

	public enum PlayResult {
		INVALID, OK, ANOTHER
	}

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

	public int play(final int cell) {
		return model.play(cell);
	}

	private boolean isValidSelection(int cell, PlayerType player) {
		return (cell >= player.getStart() && cell < player.getEnd());
	}
	private boolean singleMove(IPlayerStrategy player) {
		int cell = player.play();
		while (!isValidSelection(cell, player.getPlayer())) {
			cell = player.play();
		}
		int target = play(cell);
		return ((target == -1) || (target == player.getPlayer().getHome()));
	}
	public void run() {
		while (!model.isGameOver()) {
			view.draw();
			boolean moveResult = singleMove(player1);
			view.draw();
			while (moveResult) {
				moveResult = singleMove(player1);
				view.draw();
			}
			if (!model.isGameOver()) {
				moveResult = singleMove(player2);
				view.draw();
				while (moveResult) {
					singleMove(player2);
					view.draw();
				}
			}
			
		}
		view.debug("Player 1 score: "
				+ model.getPlayerScore(PlayerType.ONE));
		view.debug("Player 2 score: "
				+ model.getPlayerScore(PlayerType.TWO));
		view.debug("Winner is: " + model.getWinner());

	}

	public BoardModel getModel() {
		return model;
	}

	public void setModel(BoardModel model) {
		this.model = model;
	}

	public IBoardView getView() {
		return view;
	}

	public void setView(IBoardView view) {
		this.view = view;
	}

}
