package org.gadilif.gmancala.controller;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.IModelResetListener;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.strategies.IPlayerStrategy;
import org.gadilif.gmancala.view.IBoardView;

public class BoardController implements IModelResetListener {

	
	private BoardModel model;

	private IBoardView view;

	private IPlayerStrategy[] players;
	
	private int play = 0;

	public BoardController(final BoardModel model, final IBoardView view) {
		this.model = model;
		this.view = view;
		players = new IPlayerStrategy[2];
		model.addModelResetListener(this);
	}

	public void setPlayer1(final IPlayerStrategy player) {
		this.players[0] = player;
	}

	public void setPlayer2(final IPlayerStrategy player) {
		this.players[1] = player;
	}

	public int play(final int cell) {
		return model.play(cell);
	}

	private boolean isValidSelection(int cell, PlayerType player) {
		return (cell >= player.getStart() && cell < player.getEnd());
	}
	private boolean singleMove(IPlayerStrategy player) {
		view.debug("Player "+player.getPlayer()+": ");
		int cell = -1;
		while (!isValidSelection(cell, player.getPlayer())) {
			cell = player.play();
			if (play < 0) {
				play = 0;
				return false;
			}
		}
		view.debug("Moved from cell "+cell);
		int target = play(cell);
		return ((target == -1) || (target == player.getPlayer().getHome()));
	}
	
	private void doPlay(IPlayerStrategy player) {
		view.refresh();
		boolean moveResult = singleMove(player);
		view.refresh();
		while (moveResult && !model.isGameOver()) {
			moveResult = singleMove(player);
			view.refresh();
		}
	}
	public void run() {
		while (!model.isGameOver()) {
			doPlay(players[Math.abs((play++)%2)]);
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

	public void modelReset() {
		play = -2;
		view.refresh();
	}

}
