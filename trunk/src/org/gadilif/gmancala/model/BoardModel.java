package org.gadilif.gmancala.model;

public class BoardModel {

	private final static int LEFT_HOLE = 0;
	private final static int RIGHT_HOLE = 7;
	private final static int INITIAL_CELL_COUNT = 4;
	int[] cells = new int[14];
	private boolean initialized;
	public void init() {
		for (int i=0;i<14;i++) {
			cells[i] = 4;
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
	
	private String format3(int value) {
		String spaces = "";
		if (value < 10) {
			spaces = "  ";
		}
		else if (value < 100) {
			spaces = " ";
		}
		return spaces+value;
	}
	
	
	public String toString() {
		StringBuilder result = new StringBuilder("Board:\n");
		String separator = "+---+---+---+---+---+---+";
		result.append("   "+separator+"   \n");
		
		result.append("   |");
		for (int i=13;i>RIGHT_HOLE;i--) {
			result.append(format3(cells[i]));
			result.append("|");
		}
		result.append("\n");
		
		result.append(format3(cells[LEFT_HOLE]))
			  .append(separator)
			  .append(format3(cells[RIGHT_HOLE]))
			  .append("\n");
		
		result.append("   |");
		for (int i=1;i<RIGHT_HOLE;i++) {
			result.append(format3(cells[i]));
			result.append("|");
		}
		result.append("\n");
		result.append("   "+separator+"   \n");
		return result.toString();
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

}
