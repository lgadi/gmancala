package org.gadilif.gmancala.strategies;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.PlayerType;

public class SingleTurnLookAheadPlayer extends AbstractPlayer {

	public SingleTurnLookAheadPlayer(PlayerType player, BoardController controller) {
		super(player, controller);
	}

	
	private int findCandidate(int end) {
		for (int i = end-1; i>=player.getStart();i--) {
			if (end-i == controller.getModel().getCellValue(i)) return i;
		}
		return -1;
	}
	
	@Override
	public int play() {
		int candidateToEnd = findCandidate(player.getEnd());
		if (candidateToEnd != -1) return candidateToEnd;
		int maxCellValue = -10;
		int targetCell = -1;
		
		for (int i = player.getEnd()-1; i>=player.getStart();i--) {
			if ((controller.getModel().getCellValue(i) == 0) &&
				(findCandidate(i) != -1) &&
				(controller.getModel().getCellValue(14-i) > maxCellValue)) {
				maxCellValue = controller.getModel().getCellValue(14-i);
				targetCell = findCandidate(i);
			}
		}
		if (targetCell != -1) return targetCell;
		maxCellValue = -10;
		targetCell = -1;
		for (int i = player.getEnd()-1; i>=player.getStart();i--) {
			maxCellValue++;
			if ((controller.getModel().getCellValue(i) > 0) && (controller.getModel().getCellValue(i) > maxCellValue)) {
				maxCellValue = controller.getModel().getCellValue(i);
				targetCell = i;
			}
		}
		return targetCell;
	}

}
