package org.gadilif.gmancala.model;

import java.util.ArrayList;
import java.util.List;

import static org.gadilif.gmancala.model.PlayerType.*;

import org.gadilif.gmancala.view.listeners.ICellChangedListener;

public class BoardModel {

	private final static int INITIAL_CELL_COUNT = 4;
	private final static int MAX_CELLS = 14;
	int[] cells = new int[MAX_CELLS];
	private boolean initialized;
	private List<ICellChangedListener> cellChangedListeners;
	private List<IModelResetListener> modelResetListeners;
	public BoardModel() {
		cellChangedListeners = new ArrayList<ICellChangedListener>();
		modelResetListeners = new ArrayList<IModelResetListener>();
	}
	public void init() {
		resetBoard();
		this.initialized = true;
	}

	private void resetBoard() {
		for (int i=0;i<MAX_CELLS;i++) {
			setCellValue(i, INITIAL_CELL_COUNT);
		}
		setCellValue(TWO.getHome(), 0);
		setCellValue(ONE.getHome(), 0);
	}

	public boolean isInitialized() {
		return this.initialized;
	}

	public int getCellValue(final int i) {
		return cells[i];
	}

	private boolean isHole(final int cell) {
		return ((cell == TWO.getHome()) || (cell == ONE.getHome()));
	}
	
	private void capture(final int cell, final int hole) {
		if (cells[MAX_CELLS-cell] > 0) {
			setCellValue(hole, cells[hole]+cells[MAX_CELLS-cell]+1);
			setCellValue(cell,0);
			setCellValue(MAX_CELLS-cell,0);
		}
	}
	public int play(final int start) {
		int cell = start;
		if (cells[start] == 0) {
			return -1;
		}
		while (cells[start] > 0) {
			cell++;
			cell %= MAX_CELLS;
			if ((start < TWO.getStart()) && (cell == TWO.getHome())) {
				continue;
			}
			if ((start > TWO.getStart()) && (cell == ONE.getHome())) {
				continue;
			}
			incrementCell(cell);
			decrementCell(start);
		}
		cell %= MAX_CELLS;
		if (!isHole(cell) && (cells[cell] == 1)) {
			if (start < TWO.getStart()) {
				if (cell < TWO.getStart()) {
					capture(cell, ONE.getHome());
				}
			}
			else { //start > player TWO start point
				if (cell > TWO.getStart()) {
					capture(cell, TWO.getHome());
				} 
			}
				
		}
		return cell;
	}
	
	
	private void incrementCell(final int i) {
		setCellValue(i, cells[i]+1);
		
	}
	
	private void decrementCell(final int i) {
		setCellValue(i, cells[i]-1);
	}
	
	
	public boolean isGameOver() {
		return (getRowSum(ONE.getStart(),ONE.getEnd()) == 0) || (getRowSum(TWO.getStart(),TWO.getEnd()) == 0);
		
	}

	public void setCellValue(final int cell, final int value) {
		if (cells[cell] != value) {
			cells[cell] = value;
			for (ICellChangedListener listener : cellChangedListeners) {
				listener.cellChanged(cell, value);
			}
		}
	}
	
	
	public int getRightHoleValue() {
		return cells[ONE.getHome()];
	}

	public int getLeftHoleValue() {
		return cells[TWO.getHome()];
	}

	public boolean canPlay(final int i) {
		return cells[i] > 0;
	}
	
	public int getRightHole() {
		return ONE.getHome();
	}
	
	public int getLeftHole() {
		return TWO.getHome();
	}

	public void addCellChangedListener(final ICellChangedListener listener) {
		cellChangedListeners.add(listener);
	}
	
	public void addModelResetListener(final IModelResetListener listener) {
		
		modelResetListeners.add(listener);
	}

	private int getRowSum(final int start, final int end) {
		int sum = 0;
		for (int i=start;i<end;i++) {
			sum += cells[i%MAX_CELLS];
		}
		return sum;
	}
	private int getRowSum(final int start, final int end, final int home) {
		return getRowSum(start, end)+cells[home%MAX_CELLS];
	}
	
	public int getPlayerScore(PlayerType player) {
		return getRowSum(player.getStart(),player.getEnd(), player.getHome());
	}
	
	
	public PlayerType getWinner() {
		return (getPlayerScore(ONE) > getPlayerScore(TWO))?ONE:TWO;
	}

	public void reset() {
		resetBoard();
		for (IModelResetListener listener:modelResetListeners) {
			listener.modelReset();
		}
	}

}
