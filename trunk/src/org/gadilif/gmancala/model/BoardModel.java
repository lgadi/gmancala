package org.gadilif.gmancala.model;

public class BoardModel {

	private final static int LEFT_HOLE = 0;
	private final static int RIGHT_HOLE = 7;
	private final static int INITIAL_CELL_COUNT = 4;
	int[] cells = new int[14];
	private boolean initialized;
	
	public void init() {
		for (int i=0;i<14;i++) {
			cells[i] = INITIAL_CELL_COUNT;
		}
		cells[LEFT_HOLE] = 0;
		cells[RIGHT_HOLE] = 0;
		this.initialized = true;
	}

	public boolean isInitialized() {
		return this.initialized;
	}

	public int getCellValue(int i) {
		return cells[i];
	}

	public void play(int start) {
		int cell = start;
		while (cells[start] > 0) {
			cells[(++cell)%14]++;
			cells[start]--;
		}
	}
	
	
	
	
	
	public int getRightHoleValue() {
		return cells[RIGHT_HOLE];
	}

	public int getLeftHoleValue() {
		return cells[LEFT_HOLE];
	}

	public boolean canPlay(int i) {
		return cells[i] > 0;
	}
	
	public int getRightHole() {
		return RIGHT_HOLE;
	}
	
	public int getLeftHole() {
		return LEFT_HOLE;
	}

}
