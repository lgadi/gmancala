package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController.PlayResult;

public interface IPlayerStrategy {
	public enum PlayerType {ONE, TWO};
	PlayResult play();

}
